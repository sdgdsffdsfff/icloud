package com.icloud.insurance.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.user.pojo.UserInfo;
import com.icloud.insurance.model.User;
import com.icloud.user.dict.UserConstants;
import com.icloud.user.util.UserUtils;

public class UserInfoPo extends UserInfo {
	private String status;
	private Integer statusId;
	private String statusOp;
	private String userType;
	private String qq;
	private String sex;
	private Date createTime;
	private String chineseName;

	public static UserInfoPo convertUser(User user) {
		if (ICloudUtils.isNotNull(user)) {
			UserInfoPo po = new UserInfoPo();
			po.setUserId(user.getId());
			po.setUserName(user.getUserName());
			po.setEmail(user.getUserEmail());
			po.setLevel(user.getLevel());
			if (user.getOpen() == 1) {
				po.setStatus("正常");
				po.setStatusId(1);
				po.setStatusOp("暂停运行");
			} else {
				po.setStatus("暂停服务");
				po.setStatusId(0);
				po.setStatusOp("启用帐号");
			}
			po.setUserType(UserConstants.UserType.getById(user.getLevel())
					.getUserType());
			po.setQq(user.getQq());
			po.setCreateTime(user.getCreateTime());
			po.setSex(UserUtils.getUserSex(user.getUserSex()));
			po.setChineseName(user.getChinaName());
			return po;
		}
		return null;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getStatusOp() {
		return statusOp;
	}

	public void setStatusOp(String statusOp) {
		this.statusOp = statusOp;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public static List<UserInfoPo> converUser(List<User> users) {
		if (ICloudUtils.isEmpty(users)) {
			return null;
		}
		List<UserInfoPo> list = new ArrayList<UserInfoPo>();
		UserInfoPo po = null;
		for (User user : users) {
			po = convertUser(user);
			list.add(po);
		}
		return list;
	}

}
