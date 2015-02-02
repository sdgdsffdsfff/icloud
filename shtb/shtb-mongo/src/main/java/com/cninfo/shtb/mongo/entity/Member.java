package com.cninfo.shtb.mongo.entity;

import com.github.jmkgreen.morphia.annotations.Entity;
import com.github.jmkgreen.morphia.annotations.Indexed;
import com.icloud.mongo.entity.BaseEntity;

@Entity("shtb.Member2")
public class Member extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4943923252437643187L;
	@Indexed
	private String key = Double.toString(Math.random());
	@Indexed
	private String userName;
	@Indexed
	private String passWord;
	private String chineseName;

	private int old;

	public int getOld() {
		return old;
	}

	public void setOld(int old) {
		this.old = old;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

}
