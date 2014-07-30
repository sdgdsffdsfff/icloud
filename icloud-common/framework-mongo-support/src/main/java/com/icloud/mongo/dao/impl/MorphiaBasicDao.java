package com.icloud.mongo.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jmkgreen.morphia.Datastore;
import com.github.jmkgreen.morphia.Key;
import com.github.jmkgreen.morphia.mapping.Mapper;
import com.github.jmkgreen.morphia.query.Query;
import com.github.jmkgreen.morphia.query.UpdateOperations;
import com.icloud.framework.core.wrapper.Pagination;
import com.icloud.mongo.dao.IBasicDao;
import com.icloud.mongo.entity.MorphiaEntity;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public abstract class MorphiaBasicDao<E extends MorphiaEntity<I>, I> implements
		IBasicDao<E, I> {
	Logger logger = LoggerFactory.getLogger(MorphiaBasicDao.class);

	/**
	 * 该field当作缓存，不建议直接使用。 请使用 {@link getEntityClass()})
	 */
	@Deprecated
	private Class<E> entityClass;

	@Resource
	protected Datastore datastore;

	protected Datastore getDatastore() {
		return this.datastore;
	}

	@Override
	public void setDatastore(Datastore ds) {
		this.datastore = ds;
	}

	@Override
	public I create(E entity) {
		if (entity.getId() != null) {
			Key<?> k = datastore.exists(entity);
			if (k != null) {
				throw new IllegalArgumentException("id already exists.");
			}
		}
		datastore.save(entity);
		return entity.getId();
	}

	@Override
	public I createOrReplace(E entity) {
		datastore.save(entity);
		return entity.getId();
	}

	@Override
	public WriteResult deleteById(I id) {
		return datastore.delete(getEntityClass(), id);
	}

	@Override
	public E getById(I id) {
		return datastore.get(getEntityClass(), id);
	}

	@Override
	public boolean isExists(I id) {
		return datastore.exists(new Key<E>(getEntityClass(), id)) != null;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	protected Class<E> getEntityClass() {
		if (entityClass != null) {
			return entityClass;
		}
		Type superclass = this.getClass().getGenericSuperclass();
		if (superclass instanceof Class) {
			throw new RuntimeException("Missing type parameter.");
		}
		ParameterizedType parameterized = (ParameterizedType) superclass;
		entityClass = (Class<E>) parameterized.getActualTypeArguments()[0];
		return entityClass;
	}

	protected Query<E> createQuery() {
		return datastore.createQuery(getEntityClass());
	}

	protected UpdateOperations<E> createUpdateOptions() {
		return datastore.createUpdateOperations(getEntityClass());
	}

	protected Key<E> createKey(I id) {
		return new Key<E>(this.getEntityClass(), id);
	}

	// protected E getByPropertyFirst(String property, Object value) {
	// return datastore.find(getEntityClass(), property, value).get();
	// }

	protected List<E> findByProperty(String property, Object value, int offset,
			int limit) {
		return datastore.find(getEntityClass(), property, value).offset(offset)
				.limit(limit).asList();
	}

	protected void update(I id, UpdateOperations<E> ops) {
		datastore
				.update(this.createQuery().field(Mapper.ID_KEY).equal(id), ops);
	}

	protected void update(Query<E> qry, UpdateOperations<E> ops) {
		datastore.update(qry, ops);
	}

	/**
	 * 更新数据库中的现有字段，并添加entity中的非null字段（如果数据库中没有这个字段）。 值是null的字段不会被更新。
	 */
	@Override
	public void updateAndAppend(E entity) {
		datastore.merge(entity);
	}

	@Override
	public List<E> getByIds(Collection<I> ids) {
		if (ids == null || ids.size() == 0) {
			return null;
		}
		List<Key<E>> keys = new ArrayList<Key<E>>();
		for (I id : ids) {
			keys.add(new Key<E>(getEntityClass(), id));
		}
		return datastore.getByKeys(keys);
	}

	@SuppressWarnings("unchecked")
	protected Map<I, Object> queryProperty(Collection<I> ids, String property) {
		DBObject qry = new BasicDBObject(Mapper.ID_KEY, new BasicDBObject(
				"$in", ids));
		DBObject retrieve = new BasicDBObject(property, true);
		DBCursor cursor = this.getDatastore().getCollection(getEntityClass())
				.find(qry, retrieve);
		Map<I, Object> result = new HashMap<I, Object>();
		while (cursor.hasNext()) {
			DBObject dbo = cursor.next();
			Object key = dbo.get(Mapper.ID_KEY);
			Object value = dbo.get(property);
			result.put((I) key, value);
		}
		return result;
	}

	protected Object queryProperty(I id, String property) {
		DBObject qry = new BasicDBObject(Mapper.ID_KEY, id);
		DBObject retrieve = new BasicDBObject(property, true);
		DBObject found = this.getDatastore().getCollection(getEntityClass())
				.findOne(qry, retrieve);
		if (found == null) {
			return null;
		}
		return found.get(property);
	}

	/**
	 * 分组统计个数。相当于SQL中的 select count(*) from <b>Collection</b> group by
	 * <b>groupBy</b> where ...
	 *
	 * @param groupBy
	 *            分组子段，相当于SQL中的 group by
	 * @param conditions
	 *            查询条件，如：{ "name" : "=someName" }
	 * @return 分组个数统计结果：{ property : count }
	 */
	protected Map<Object, Integer> countGroup(String groupBy,
			Map<String, Object> conditions) {
		DBObject keys = new BasicDBObject(groupBy, true);
		DBObject cond = new BasicDBObject();
		if (conditions != null && conditions.size() > 0) {
			for (Entry<String, Object> ety : conditions.entrySet()) {
				cond.put(ety.getKey(), ety.getValue());
			}
		}
		DBObject initial = new BasicDBObject("count", 0);
		String reduce = "function(cur,result){ result.count += 1; }";
		DBObject group = getDatastore().getCollection(getEntityClass()).group(
				keys, cond, initial, reduce);

		BasicDBList dbList = (BasicDBList) group;
		Map<Object, Integer> result = new HashMap<Object, Integer>();
		for (Object e : dbList) {
			DBObject dbo = (DBObject) e;
			result.put(dbo.get(groupBy),
					Double.valueOf(dbo.get("count").toString()).intValue());
		}
		return result;
	}

	@Override
	public long getCount(Query<E> query) {
		return query.countAll();
	}

	/** ----------查询--------- */
	@Override
	public List<E> findByProperty(Map<String, Object> conditions, int page,
			int pageSize) {
		if (page < 1) {
			page = 1;
		}

		if (pageSize < 1) {
			pageSize = 10;
		}

		Query<E> lvQuery = getDatastore().createQuery(getEntityClass());
		for (Entry<String, Object> entry : conditions.entrySet()) {
			lvQuery.field(entry.getKey()).contains(entry.getValue().toString());
		}
		int offset = (page - 1) * pageSize;
		lvQuery.offset(offset);
		lvQuery.limit(pageSize);
		List<E> lvResult = lvQuery.asList();
		return lvResult;
	}

	@Override
	public List<E> findByProperty(Map<String, Object> properties) {
		return buildQuery(properties, null, null).asList();
	}

	@Override
	public List<E> findFieldsByProperty(Map<String, Object> properties,
			String... fields) {
		return buildQuery(properties, null, null).retrievedFields(true, fields)
				.asList();
	}

	@Override
	public List<E> findByProperty(Pagination<E> paging) {
		return buildQuery(null, paging, null).asList();
	}

	@Override
	public List<E> findByProperty(Map<String, Object> properties, String orderBy) {
		return buildQuery(properties, null, orderBy).asList();
	}

	@Override
	public List<E> findByProperty(Map<String, Object> properties,
			Pagination<E> paging, String orderBy) {
		return buildQuery(properties, paging, orderBy).asList();
	}

	@Override
	public List<E> findByProperty(String property, Object value) {
		Query<E> query = buildQuery(property, value, null, null);
		return query.asList();
	}

	@Override
	public List<E> findByProperty(String property, Object value, String orderBy) {
		return buildQuery(property, value, null, orderBy).asList();
	}

	@Override
	public List<E> findByProperty(String property, Object value,
			Pagination<E> paging) {
		return buildQuery(property, value, paging, null).asList();
	}

	@Override
	public List<E> findByProperty(String property, Object value,
			Pagination<E> paging, String orderBy) {
		return buildQuery(property, value, paging, orderBy).asList();
	}

	protected Query<E> buildQuery(String property, Object value,
			Pagination<E> paging, String orderBy) {
		Query<E> query = createQuery();
		if (value != null) {
			query.filter(property, value);
		}
		if (paging != null) {
			query.offset(paging.getStart()).limit(paging.getPageSize());
		}
		if (StringUtils.isNotBlank(orderBy)) {
			query.order(orderBy);
		}
		return query;
	}

	protected Query<E> buildQuery(Map<String, Object> properties,
			Pagination<E> paging, String orderBy) {
		Query<E> query = createQuery();
		if (properties != null && !properties.isEmpty()) {
			Iterator<Entry<String, Object>> it = properties.entrySet()
					.iterator();
			while (it.hasNext()) {
				Entry<String, Object> entry = it.next();
				query.filter(entry.getKey(), entry.getValue());
			}
		}
		if (paging != null) {
			query.offset(paging.getStart()).limit(paging.getPageSize());
		}
		if (StringUtils.isNotBlank(orderBy)) {
			query.order(orderBy);
		}
		return query;
	}

	protected Query<E> buildQuery(Map<String, Object> filterProperties,
			Map<String, Object> containProperties, Pagination<E> paging,
			String orderBy) {
		Query<E> query = createQuery();
		if (filterProperties != null && !filterProperties.isEmpty()) {
			Iterator<Entry<String, Object>> fit = filterProperties.entrySet()
					.iterator();
			while (fit.hasNext()) {
				Entry<String, Object> entry = fit.next();
				query.filter(entry.getKey(), entry.getValue());
			}
		}

		if (containProperties != null && !containProperties.isEmpty()) {
			Iterator<Entry<String, Object>> cit = containProperties.entrySet()
					.iterator();
			while (cit.hasNext()) {
				Entry<String, Object> entry = cit.next();
				query.field(entry.getKey()).contains(
						entry.getValue().toString());
			}
		}
		if (paging != null) {
			query.offset(paging.getStart()).limit(paging.getPageSize());
		}
		if (StringUtils.isNotBlank(orderBy)) {
			query.order(orderBy);
		}
		return query;
	}

	@Override
	public long getTotalCount() {
		return this.getDatastore().getCount(getEntityClass());
	}

	@Override
	public List<E> findAll() {
		return this.getDatastore().createQuery(getEntityClass()).asList();
	}

	@Override
	public E getById(String id) {
		return getDatastore().get(getEntityClass(), buildObjectId(id));
	}

	@Override
	public List<E> findByProperty(Map<String, Object> properties,
			Pagination<E> paging) {
		return findByProperty(properties, paging, null);
	}

	@Override
	public E findOneByProperty(Map<String, Object> properties) {
		List<E> result = findByProperty(properties, null, null);
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}

	@Override
	public E findOneByProperty(String property, Object value) {
		List<E> result = findByProperty(property, value, null, null);
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}

	private Pagination<E> buildPagingResult(Pagination<E> paging, List<E> list,
			long totalCount) {
		paging.setTotalItemCount(totalCount);
		paging.setData(list);
		return paging;
	}

	@Override
	public Pagination<E> getPagingResult(String property, Object value,
			Pagination<E> paging) {
		Query<E> query = buildQuery(property, value, paging, null);
		return buildPagingResult(paging, query.asList(), query.countAll());
	}

	@Override
	public Pagination<E> getPagingResult(String property, Object value,
			Pagination<E> paging, String orderBy) {
		Query<E> query = buildQuery(property, value, paging, orderBy);
		return buildPagingResult(paging, query.asList(), query.countAll());
	}

	@Override
	public Pagination<E> getPagingResult(Map<String, Object> properties,
			Pagination<E> paging) {
		Query<E> query = buildQuery(properties, paging, null);
		return buildPagingResult(paging, query.asList(), query.countAll());
	}

	@Override
	public Pagination<E> getPagingResult(Map<String, Object> properties,
			Pagination<E> paging, String orderBy) {
		Query<E> query = buildQuery(properties, paging, orderBy);
		return buildPagingResult(paging, query.asList(), query.countAll());
	}

	@Override
	public boolean exist(Map<String, Object> Properties) {
		E e = findOneByProperty(Properties);
		return e == null ? false : true;
	}

	@Override
	public boolean exist(String property, Object value) {
		E e = findOneByProperty(property, value);
		return e == null ? false : true;
	}

	@Override
	public boolean exist(String id) {
		E e = findOneByProperty("id", buildObjectId(id));
		return e == null ? false : true;
	}

	/** ----------保存--------- */
	/**
	 * 保存或更新
	 */
	@Override
	public boolean saveEntity(List<E> list) {
		try {
			this.getDatastore().save(list);
		} catch (RuntimeException ex) {
			logger.error("数据库[保存]失败,当前时间:{},保存数据信息:{},错误详细信息:{}",
					new DateTime(), list.toString(), ex.getMessage());
			throw ex;
		}
		return true;
	}

	/**
	 * 只能插入新的数据，不能更新
	 */
	@Override
	public String createEntity(E e) {
		try {
			return create(e).toString();
		} catch (RuntimeException ex) {
			logger.error("数据库[保存]失败,当前时间:{},保存数据信息:{},错误详细信息:{}",
					new DateTime(), e.toString(), ex.getMessage());
			throw ex;
		}
	}

	/**
	 * 保存或更新
	 */
	@Override
	public String saveEntity(E e) {
		try {
			return createOrReplace(e).toString();
		} catch (RuntimeException ex) {
			logger.error("数据库[保存]失败,当前时间:{},保存数据信息:{},错误详细信息:{}",
					new DateTime(), e.toString(), ex.getMessage());
			throw ex;
		}

	}

	/** ----------更新--------- */
	@Override
	public boolean updateEntity(E e, UpdateOperations<E> ops) {
		try {
			this.getDatastore().update(e, ops);
		} catch (RuntimeException ex) {
			logger.error("数据库[更新]失败,当前时间:{},更新数据信息:{},错误详细信息:{}",
					new DateTime(), e.toString(), ex.getMessage());
			throw ex;
		}
		return true;
	}

	@Override
	public boolean updateEntity(Query<E> e, UpdateOperations<E> ops) {
		try {
			this.getDatastore().update(e, ops);
		} catch (RuntimeException ex) {
			logger.error("数据库[更新]失败,当前时间:{},更新数据信息:{},错误详细信息:{}",
					new DateTime(), e.toString(), ex.getMessage());
			throw ex;
		}
		return true;
	}

	public boolean mergeEntity(E e) {
		try {
			this.getDatastore().merge(e);
		} catch (RuntimeException ex) {
			logger.error("数据库[更新]失败,当前时间:{},更新数据信息:{},错误详细信息:{}",
					new DateTime(), e.toString(), ex.getMessage());
			throw ex;
		}
		return true;
	}

	/** ----------删除--------- */
	@Override
	public boolean deleteAll() {
		try {
			this.getDatastore().delete(createQuery());
		} catch (RuntimeException ex) {
			logger.error("数据库[删除]失败,当前时间:{},错误详细信息:{}", new DateTime(),
					ex.getMessage());
			throw ex;
		}
		return true;
	}

	@Override
	public boolean deleteById(String id) {
		try {
			this.getDatastore().delete(getEntityClass(), buildObjectId(id));
		} catch (RuntimeException ex) {
			logger.error("数据库[删除]失败,当前时间:{},删除数据Id:{},错误详细信息:{}",
					new DateTime(), id, ex.getMessage());
			throw ex;
		}
		return true;
	}

	@Override
	public boolean delete(Query<E> query) {
		try {
			this.getDatastore().delete(query);
		} catch (RuntimeException ex) {
			logger.error("数据库[删除]失败,当前时间:{},数据条件信息:{},错误详细信息:{}",
					new DateTime(), query.toString(), ex.getMessage());
			throw ex;
		}
		return true;
	}

	/**
	 * idStr为monogodb的id字符串，24位 如果id不合法会抛出运行时异常
	 * 
	 * @param idStr
	 * @return
	 */
	public ObjectId buildObjectId(String idStr) {
		return new ObjectId(idStr);
	}

	@Override
	public Pagination<E> getPagingResult(Map<String, Object> filterMap,
			Map<String, Object> containMap, Pagination<E> pag, String orderBy) {
		Query<E> query = buildQuery(filterMap, containMap, pag, orderBy);
		return buildPagingResult(pag, query.asList(), query.countAll());
	}
}
