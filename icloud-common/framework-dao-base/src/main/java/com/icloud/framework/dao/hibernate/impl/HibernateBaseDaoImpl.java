package com.icloud.framework.dao.hibernate.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.icloud.framework.dao.hibernate.HiberanateEnum.OperationEnum;
import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.hibernate.util.GenericsUtils;
import com.icloud.framework.util.ICloudUtils;

public class HibernateBaseDaoImpl<T> extends HibernateDaoSupport implements
		IHibernateBaseDao<T> {
	protected Class<T> domainClass;

	public HibernateBaseDaoImpl() {
		this.domainClass = GenericsUtils.getSuperClassGenricType(getClass());
	}

	@Override
	public Class<T> getDomainClass() {
		return this.domainClass;
	}

	@Override
	public T getById(Integer id) {
		// return (T) getHibernateTemplate().load(domainClass, id);
		List<T> list = (getHibernateTemplate().find(
				"from " + domainClass.getName()
						+ " as model where model.id = ? ", id));
		if (ICloudUtils.isEmpty(list))
			return null;
		return list.get(0);
	}

	@Override
	public void update(T t) {
		getHibernateTemplate().saveOrUpdate(t);
	}

	@Override
	public void save(T t) {
		getHibernateTemplate().save(t);
	}

	@Override
	public void delete(T t) {
		getHibernateTemplate().delete(t);
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return (getHibernateTemplate().find("from " + domainClass.getName()
				+ " x"));

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		Object obj = getById(id);
		getHibernateTemplate().delete(obj);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from " + domainClass.getName() + " x";
		return count(hql);
	}

	public long count(String hql) {
		List list = getHibernateTemplate().find(hql);
		Long count = (Long) list.get(0);
		return count.longValue();
	}

	public long count(String hql, Object[] values) {
		List list = getHibernateTemplate().find(hql, values);
		Long count = (Long) list.get(0);
		return count.longValue();
	}

	@Override
	public void deteleteAll() {
		// TODO Auto-generated method stub
		getHibernateTemplate().execute(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				// TODO Auto-generated method stub
				String hqlDelete = "delete " + domainClass.getName();
				session.createQuery(hqlDelete).executeUpdate();
				return null;
			}

		});

	}

	@Override
	public List<T> findByProperty(String paramName, Object value) {
		return getHibernateTemplate().find(
				"from " + domainClass.getName() + " as model where model."
						+ paramName + "=?", value);
	}

	public long countByProperty(String paramname, Object value) {
		String hql = "select count(*) from " + domainClass.getName()
				+ " as model where model." + paramname + "=" + value;
		return count(hql);
	}

	public List<T> findByProperty(final String hql, final int start,
			final int limit) {
		// List<T> list =
		// this.getSession().createQuery(hql).setFirstResult(start)
		// .setMaxResults(limit).list();
		// return list;
		List<T> list = (List<T>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query queryObject = session.createQuery(hql);
						queryObject.setFirstResult(start);
						queryObject.setMaxResults(limit);
						return queryObject.list();
					}
				});
		return list;
		// }
	}

	public List<T> findByProperty(final String hql, final Object[] values,
			final int start, final int limit) {
		List<T> list = (List<T>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query queryObject = session.createQuery(hql);
						if (values != null) {
							for (int i = 0; i < values.length; i++) {
								queryObject.setParameter(i, values[i]);
							}
						}
						queryObject.setFirstResult(start);
						queryObject.setMaxResults(limit);
						return queryObject.list();
					}
				});
		return list;
		// Query queryObject = this.getSession().createQuery(hql);
		// if (values != null) {
		// for (int i = 0; i < values.length; i++) {
		// queryObject.setParameter(i, values[i]);
		// }
		// }
		// return queryObject.setFirstResult(start).setMaxResults(limit).list();
	}

	@Override
	public List<T> findByProperty(String paramName, Object value, int start,
			int limit) {
		String queryString = "from " + domainClass.getName()
				+ " as model where model." + paramName + " = ? ";
		Object[] values = { value };
		return findByProperty(queryString, values, start, limit);
	}

	@Override
	public List<T> findAll(int start, int limit) {
		String queryString = "from " + domainClass.getName() + " as model";
		return findByProperty(queryString, start, limit);
	}

	@Override
	public List<T> findByProperty(String hql) {
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<T> findByProperty(String hql, Object[] values) {
		return getHibernateTemplate().find(hql, values);
	}

	private String buildQuery(String[] paramNames, OperationEnum[] operations,
			Object[] values, String sortParam, boolean isAsc) {

		if (ICloudUtils.isNotNull(paramNames) && ICloudUtils.isNotNull(values)
				&& paramNames.length == values.length) {
			StringBuffer sb = new StringBuffer();
			sb.append("from " + domainClass.getName() + " as model where ");

			int len = paramNames.length;
			for (int i = 0; i < len; i++) {
				sb.append("model." + paramNames[i] + " "
						+ operations[i].getOpeationValue() + " ?");
				if (i != (len - 1)) {
					sb.append(" and ");
				}
			}

			if (ICloudUtils.isNotNull(sortParam)) {
				String asc = "asc";
				if (!isAsc) {
					asc = "desc";
				}
				sb.append(" order by model." + sortParam + " " + asc);
			}
			return sb.toString();
		}

		return null;

	}

	private String buildQuery(String[] paramNames, Object[] values,
			String sortParam, boolean isAsc) {
		if (ICloudUtils.isNotNull(paramNames) && ICloudUtils.isNotNull(values)
				&& paramNames.length == values.length) {
			StringBuffer sb = new StringBuffer();
			sb.append("from " + domainClass.getName() + " as model where ");

			int len = paramNames.length;
			for (int i = 0; i < len; i++) {
				sb.append("model." + paramNames[i] + " = ?");
				if (i != (len - 1)) {
					sb.append(" and ");
				}
			}

			if (ICloudUtils.isNotNull(sortParam)) {
				String asc = "asc";
				if (!isAsc) {
					asc = "desc";
				}
				sb.append(" order by model." + sortParam + " " + asc);
			}
			return sb.toString();
		}
		return null;
	}

	@Override
	public List<T> findByProperty(String[] paramNames, Object[] values,
			String sortParam, boolean isAsc, int start, int limit) {
		String queryString = buildQuery(paramNames, values, sortParam, isAsc);
		if (ICloudUtils.isNotNull(queryString)) {
			return findByProperty(queryString, values, start, limit);
		}
		return null;
	}

	@Override
	public List<T> findByProperty(String[] paramNames,
			OperationEnum[] operations, Object[] values, String sortParam,
			boolean isAsc, int start, int limit) {
		String queryString = buildQuery(paramNames, operations, values,
				sortParam, isAsc);
		if (ICloudUtils.isNotNull(queryString)) {
			return findByProperty(queryString, values, start, limit);
		}
		return null;
	}

	@Override
	public long countByProperty(String[] paramNames,
			OperationEnum[] operations, Object[] values) {
		String queryString = buildQuery(paramNames, operations, values, null,
				false);
		if (ICloudUtils.isNotNull(queryString)) {
			queryString = "select count(*) " + queryString;
			return count(queryString, values);
		}
		return 0;
	}

	@Override
	public List<T> findByProperty(String[] paramNames, Object[] values,
			String sortParam, boolean isAsc) {
		String queryString = buildQuery(paramNames, values, sortParam, isAsc);
		if (ICloudUtils.isNotNull(queryString)) {
			return findByProperty(queryString, values);
		}
		return null;
	}

	@Override
	public long countByProperty(String[] paramNames, Object[] values) {
		String queryString = buildQuery(paramNames, values, null, false);
		if (ICloudUtils.isNotNull(queryString)) {
			queryString = "select count(*) " + queryString;
			return count(queryString, values);
		}
		return 0;
	}

	@Override
	public void deleteByProperty(final String param, final Object value) {

		getHibernateTemplate().execute(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				String hqlDelete = "delete " + domainClass.getName()
						+ " where param = " + value;

				session.createQuery(hqlDelete).executeUpdate();
				return null;
			}
		});
	}

}