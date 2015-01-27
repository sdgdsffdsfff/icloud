package com.icloud.framework.mongo.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import com.icloud.framework.mongo.service.IMongoBaseService;
import com.icloud.mongo.dao.IBasicDao;
import com.icloud.mongo.entity.BaseEntity;

public abstract class MongoBaseService<E extends BaseEntity, I> implements
		IMongoBaseService<E, I> {
	private IBasicDao<E, I> baseDao;

	protected abstract IBasicDao<E, I> getDao();

	@PostConstruct
	protected void initBaseDao() {
		this.baseDao = getDao();
	}

	@Override
	public String create(E e) {
		return baseDao.createEntity(e);
	}

	@Override
	public String save(E e) {
		return baseDao.saveEntity(e);
	}

	@Override
	public boolean update(E e) {
		return baseDao.mergeEntity(e);
	}

	@Override
	public E findById(I id) {
		return this.baseDao.getById(id);
	}

	@Override
	public boolean delete(I id) {
		return this.baseDao.deleteById(id).isUpdateOfExisting();
	}

	@Override
	public List<E> getAll() {
		return this.baseDao.findAll();
	}

	@Override
	public List<E> getByIds(List<I> ids) {
		// return this.baseDao.getByIds(ids);
		return this.baseDao.getByIds(ids);

	}

}
