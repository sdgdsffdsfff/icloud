package com.icloud.mongo.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.github.jmkgreen.morphia.Datastore;
import com.github.jmkgreen.morphia.query.Query;
import com.github.jmkgreen.morphia.query.UpdateOperations;
import com.icloud.framework.core.wrapper.Pagination;
import com.mongodb.WriteResult;

public interface IBasicDao<E, I> {

	void setDatastore(Datastore ds);

	I create(E entity);

	I createOrReplace(E entity);

	WriteResult deleteById(I id);

	E getById(I id);

	boolean isExists(I id);

	void updateAndAppend(E entity);

	List<E> getByIds(Collection<I> ids);

	long getCount(Query<E> query);

	/** --------查询-------- */

	/**
	 * 分页查询
	 * 
	 * @param conditions
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List<E> findByProperty(Map<String, Object> conditions, int page,
			int pageSize);

	long getTotalCount();

	List<E> findAll();

	/**
	 * 查询条件 eg. findByProperty(new HashMap(),null) 查找所有
	 * 
	 * @param properties
	 *            可以为空
	 * @param paging
	 *            可以为空
	 * @return
	 */
	List<E> findByProperty(Map<String, Object> properties);

	/**
	 * 分页查找
	 * 
	 * @param paging
	 * @return
	 */
	List<E> findByProperty(Pagination<E> paging);

	/**
	 * eg. findByProperty(map,"-age,name") age降序,name升序
	 * 
	 * @param properties
	 *            可以为空
	 * @param orderBy
	 *            可以为空
	 * @return
	 */
	List<E> findByProperty(Map<String, Object> properties, String orderBy);

	/**
	 * 分页查询
	 * 
	 * @param properties
	 * @param paging
	 * @return
	 */
	List<E> findByProperty(Map<String, Object> properties, Pagination<E> paging);

	/**
	 * 分页+排序
	 * 
	 * @param properties
	 * @param paging
	 * @param orderBy
	 * @return
	 */
	List<E> findByProperty(Map<String, Object> properties,
			Pagination<E> paging, String orderBy);

	/**
	 * 根据条件，只查询部分fields,不全部查出整个对象
	 * 
	 * @param properties
	 * @param fields
	 * @return
	 */
	List<E> findFieldsByProperty(Map<String, Object> properties,
			String... fields);

	/**
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	List<E> findByProperty(String property, Object value);

	List<E> findByProperty(String property, Object value, String orderBy);

	List<E> findByProperty(String property, Object value, Pagination<E> paging);

	List<E> findByProperty(String property, Object value, Pagination<E> paging,
			String orderBy);

	E findOneByProperty(Map<String, Object> properties);

	E findOneByProperty(String property, Object value);

	E getById(String id);

	/**
	 * 为空的情况下也返回PagingResult，不返回null,里面的属性为空
	 * 
	 * @param property
	 * @param value
	 * @param paging
	 * @return
	 */
	Pagination<E> getPagingResult(String property, Object value,
			Pagination<E> paging);

	Pagination<E> getPagingResult(String property, Object value,
			Pagination<E> paging, String orderBy);

	Pagination<E> getPagingResult(Map<String, Object> properties,
			Pagination<E> paging);

	Pagination<E> getPagingResult(Map<String, Object> properties,
			Pagination<E> paging, String orderBy);

	Pagination<E> getPagingResult(Map<String, Object> filterMap,
			Map<String, Object> containMap, Pagination<E> pag, String orderBy);

	/** -------保存------- */
	boolean saveEntity(List<E> list);

	String createEntity(E e);

	String saveEntity(E e);

	/** -------更新------- */
	boolean updateEntity(E e, UpdateOperations<E> option);

	boolean updateEntity(Query<E> query, UpdateOperations<E> option);

	boolean mergeEntity(E entity);

	/** --------删除-------- */
	boolean deleteAll();

	boolean deleteById(String id);

	boolean delete(Query<E> query);

	/** 其他 */
	boolean exist(Map<String, Object> Properties);

	boolean exist(String property, Object value);

	boolean exist(String id);

	ObjectId buildObjectId(String id);

}
