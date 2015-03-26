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
	private String upGrade;
	private int upGradeId;
	private String downGrade;
	private int downGradeId;
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
			po.setUserType(UserConstants.UserType.getById(user.getLevel())
					.getUserType());
			if (po.isSuper()) {
				po.setUpGrade("转中介");
				po.setUpGradeId(UserConstants.UserType.POXY_USER.getId());
				po.setDownGrade("转普通");
				po.setDownGradeId(UserConstants.UserType.NORMAL_USER.getId());
			} else if (po.isProxy()) {
				po.setUpGrade("升管理员");
				po.setUpGradeId(UserConstants.UserType.SUPER_USER.getId());
				po.setDownGrade("转普通");
				po.setDownGradeId(UserConstants.UserType.NORMAL_USER.getId());
			} else {
				po.setUpGrade("升管理员");
				po.setUpGradeId(UserConstants.UserType.SUPER_USER.getId());
				po.setDownGrade("转中介");
				po.setDownGradeId(UserConstants.UserType.POXY_USER.getId());
			}
			po.setQq(user.getQq());
			po.setCreateTime(user.getCreateTime());
			po.setSex(UserUtils.getUserSex(user.getUserSex()));
			po.setChineseName(user.getChinaName());
			return po;
		}
		return null;
	}

	public String getUpGrade() {
		return upGrade;
	}

	public void setUpGrade(String upGrade) {
		this.upGrade = upGrade;
	}

	public String getDownGrade() {
		return downGrade;
	}

	public int getUpGradeId() {
		return upGradeId;
	}

	public void setUpGradeId(int upGradeId) {
		this.upGradeId = upGradeId;
	}

	public int getDownGradeId() {
		return downGradeId;
	}

	public void setDownGradeId(int downGradeId) {
		this.downGradeId = downGradeId;
	}

	public void setDownGrade(String downGrade) {
		this.downGrade = downGrade;
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
