package com.icloud.user.bussiness.po;

import java.util.ArrayList;
import java.util.List;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.user.pojo.UserInfo;
import com.icloud.stock.model.User;
import com.icloud.user.dict.UserConstants;

public class UserInfoPo extends UserInfo {
	private String level;
	private String status;
	private String promotion;
	private String fatherName;
	private Integer fatherId;

	public void setAddUser(User user) {
		if (user.getLevel() == UserConstants.USER_LEVEL_LIMIT
				|| user.getPromotion() == 0) {
			this.setAddUser(false);
		} else {
			this.setAddUser(true);
		}
	}

	public static List<UserInfoPo> converUser(List<User> users) {
		if (ICloudUtils.isEmpty(users)) {
			return null;
		}
		List<UserInfoPo> list = new ArrayList<UserInfoPo>();
		UserInfoPo po = null;
		for (User user : users) {
			po = convertUser(user);
			if (ICloudUtils.isNotNull(po)) {
				list.add(po);
			}
		}
		return list;
	}

	public static UserInfoPo convertUser(User user) {
		if (ICloudUtils.isNotNull(user)) {
			UserInfoPo po = new UserInfoPo();
			po.setUserId(user.getId());
			po.setUserName(user.getUserName());
			po.setEmail(user.getUserEmail());
			po.setAddUser(user);
			po.setLevel(user.getLevel() + "级代理");
			if (user.getOpen() == 1) {
				po.setStatus("正常");
			} else {
				po.setStatus("暂停服务");
			}
			if (po.isAddUser()) {
				po.setPromotion("正常代理");
			} else {
				po.setPromotion("不能代理");
			}
			po.setFatherId(user.getFatherId());
			po.setFatherName(user.getFatherName());
			return po;
		}
		return null;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public Integer getFatherId() {
		return fatherId;
	}

	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}

}
