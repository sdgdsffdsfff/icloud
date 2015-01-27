package com.icloud.framework.mongo.service;

import java.util.List;

public interface IMongoBaseService<E, I> {
	/** 只插入 */
	public String create(E e);

	/** 创建或者替换 */
	public String save(E e);

	/** 更新有的属性 */
	public boolean update(E e);

	public E findById(I id);

	public boolean delete(I id);

	public List<E> getAll();

	public List<E> getByIds(List<I> ids);

}