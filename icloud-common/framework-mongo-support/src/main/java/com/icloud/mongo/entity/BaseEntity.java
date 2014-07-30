package com.icloud.mongo.entity;

import java.io.Serializable;

import org.bson.types.ObjectId;

import com.github.jmkgreen.morphia.annotations.Id;

public class BaseEntity implements MorphiaEntity<ObjectId>, Serializable {
	private static final long serialVersionUID = 1921943548153755929L;
	@Id
	protected ObjectId id;

	public String getKey() {
		if (id != null) {
			return id.toString();
		}
		return "";
	}

	@Override
	public void setId(String id) {
		this.id = new ObjectId(id);
	}

	@Override
	public ObjectId getId() {
		return id;
	}

	@Override
	public void setId(ObjectId id) {
		this.id = id;
	}

}
