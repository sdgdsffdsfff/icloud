package com.icloud.framework.service;

import java.util.List;

import com.icloud.framework.dao.hibernate.HiberanateEnum.OperationEnum;

public interface ISqlBaseService<T> {
	public T getById(Integer id);

	public void update(T t);

	public T save(T t);

	public void delete(T t);

	public List<T> findAll();

	public void deleteById(Integer id);

	public long count(String hql);

	public List<T> findByProperty(String hql, int start, int limit);

	public List<T> findByProperty(String hql);

	public long count();

	public void deteleteAll();

	public List<T> findByProperies(String property, Object value);

	public long countByProperties(String property, Object value);

	public List<T> findByProperties(String property, Object value, int start,
			int limit);

	public List<T> findAll(int start, int limit);

	public long countByProperty(String[] paramNames, Object[] values);

	public List<T> findByProperty(String[] paramNames, Object[] values,
			String sortParam, boolean isAsc, int start, int limit);

	/**
	 * @param params
	 * @param operations
	 * @param values
	 * @return long
	 * @throws
	 */
	long countByProperty(String[] params, OperationEnum[] operations,
			Object[] values);

	/**
	 * @param params
	 * @param operations
	 * @param values
	 * @param createtime
	 * @param b
	 * @param i
	 * @param pageSize
	 * @return List<JuhuasuanDetail>
	 * @throws
	 */
	List<T> findByProperty(String[] params, OperationEnum[] operations,
			Object[] values, String createtime, boolean b, int i, int pageSize);

	List<T> findByPropertyNoLazy(String[] paramNames,
			OperationEnum[] operations, Object[] values, String sortParam,
			boolean isAsc, int start, int limit);

}
