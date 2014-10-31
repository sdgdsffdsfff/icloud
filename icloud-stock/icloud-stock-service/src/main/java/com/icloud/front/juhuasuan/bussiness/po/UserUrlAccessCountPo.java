package com.icloud.front.juhuasuan.bussiness.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.stock.model.User;
import com.icloud.stock.model.UserUrlAccessCount;
import com.icloud.user.bussiness.po.UserInfoPo;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年10月28日 上午11:29:30
 */
public class UserUrlAccessCountPo {
	private Integer id;
	private Integer userId;
	private Integer count;
	private Integer allCount;
	private Integer validCount;
	private Integer allValidCount;
	private Date createTime;
	private String userName;

	public static UserUrlAccessCountPo convertUserUrlAccessCount(
			UserUrlAccessCount userUrlAccessCount, UserInfoPo user) {
		UserUrlAccessCountPo po = new UserUrlAccessCountPo();
		po.setId(userUrlAccessCount.getId());
		po.setUserId(userUrlAccessCount.getUserId());
		po.setCount(userUrlAccessCount.getCount());
		po.setAllCount(userUrlAccessCount.getAllCount());
		po.setCreateTime(userUrlAccessCount.getCreateTime());
		po.setValidCount(userUrlAccessCount.getValidCount());
		po.setAllValidCount(userUrlAccessCount.getValidAllCount());
		if (ICloudUtils.isNotNull(user))
			po.setUserName(user.getUserName());
		return po;
	}

	public static UserUrlAccessCountPo convertUserUrlAccessCount(
			UserUrlAccessCount userUrlAccessCount, User user) {
		UserUrlAccessCountPo po = new UserUrlAccessCountPo();
		po.setId(userUrlAccessCount.getId());
		po.setUserId(userUrlAccessCount.getUserId());
		po.setCount(userUrlAccessCount.getCount());
		po.setAllCount(userUrlAccessCount.getAllCount());
		po.setCreateTime(userUrlAccessCount.getCreateTime());
		po.setValidCount(userUrlAccessCount.getValidCount());
		po.setAllValidCount(userUrlAccessCount.getValidAllCount());
		if (ICloudUtils.isNotNull(user))
			po.setUserName(user.getUserName());
		return po;
	}

	public static List<UserUrlAccessCountPo> convertUserUrlAccessCount(
			List<UserUrlAccessCount> userUrlAccessCountList, User user) {
		List<UserUrlAccessCountPo> list = new ArrayList<UserUrlAccessCountPo>();
		for (UserUrlAccessCount userUrlAccessCount : userUrlAccessCountList) {
			list.add(convertUserUrlAccessCount(userUrlAccessCount, user));
		}
		return list;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getAllCount() {
		return allCount;
	}

	public void setAllCount(Integer allCount) {
		this.allCount = allCount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getValidCount() {
		return validCount;
	}

	public void setValidCount(Integer validCount) {
		this.validCount = validCount;
	}

	public Integer getAllValidCount() {
		return allValidCount;
	}

	public void setAllValidCount(Integer allValidCount) {
		this.allValidCount = allValidCount;
	}

}
