package com.icloud.framework.service.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.transaction.annotation.Transactional;

import com.icloud.framework.dao.hibernate.HiberanateEnum.OperationEnum;
import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.service.ISqlBaseService;

public abstract class SqlBaseService<T> implements ISqlBaseService<T> {

	protected IHibernateBaseDao<T> baseDao;

	protected abstract IHibernateBaseDao<T> getDao();

	@PostConstruct
	protected void initBaseDao() {
		this.baseDao = getDao();
	}

	@Override
	public T getById(Integer id) {
		// TODO Auto-generated method stub
		return this.baseDao.getById(id);
	}

	@Override
	public void update(T t) {
		this.baseDao.update(t);
	}

	@Override
	public T save(T t) {
		this.baseDao.save(t);
		return t;
	}

	@Transactional
	public void save(Collection entities) {
		this.baseDao.save(entities);
	}

	@Override
	@Transactional
	public void delete(T t) {
		// TODO Auto-generated method stub
		this.baseDao.delete(t);
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return this.baseDao.findAll();
	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		this.baseDao.deleteById(id);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return this.baseDao.count();
	}

	@Override
	@Transactional
	public void deteleteAll() {
		// TODO Auto-generated method stub
		this.baseDao.deteleteAll();
	}

	@Override
	public List<T> findByProperies(String property, Object value) {
		return this.baseDao.findByProperty(property, value);
	}

	@Override
	public long countByProperties(String property, Object value) {
		// TODO Auto-generated method stub
		return this.baseDao.countByProperty(property, value);
	}

	@Override
	public List<T> findByProperties(String property, Object value, int start,
			int limit) {
		return this.baseDao.findByProperty(property, value, start, limit);
	}

	@Override
	public List<T> findAll(int start, int limit) {
		return this.baseDao.findAll(start, limit);
	}

	public long countByProperty(String[] paramNames, Object[] values) {
		return this.baseDao.countByProperty(paramNames, values);
	}

	public List<T> findByProperty(String[] paramNames, Object[] values,
			String sortParam, boolean isAsc, int start, int limit) {
		return this.baseDao.findByProperty(paramNames, values, sortParam,
				isAsc, start, limit);
	}

	public long countByProperty(String[] paramNames,
			OperationEnum[] operations, Object[] values) {
		return this.baseDao.countByProperty(paramNames, operations, values);
	}

	public List<T> findByProperty(String[] paramNames,
			OperationEnum[] operations, Object[] values, String sortParam,
			boolean isAsc, int start, int limit) {
		return this.baseDao.findByProperty(paramNames, operations, values,
				sortParam, isAsc, start, limit);
	}

	public List<T> findByPropertyNoLazy(String[] paramNames,
			OperationEnum[] operations, Object[] values, String sortParam,
			boolean isAsc, int start, int limit) {
		return this.baseDao.findByPropertyNoLazy(paramNames, operations,
				values, sortParam, isAsc, start, limit);
	}

	public long count(String hql) {
		return this.baseDao.count(hql);
	}

	public List<T> findByProperty(String hql, int start, int limit) {
		return this.baseDao.findByProperty(hql, start, limit);
	}

	public List<T> findByProperty(String hql) {
		return this.baseDao.findByProperty(hql);
	}
}
