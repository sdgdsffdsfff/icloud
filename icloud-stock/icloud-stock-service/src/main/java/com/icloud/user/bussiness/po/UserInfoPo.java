package com.icloud.user.bussiness.po;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.juhuasuan.bussiness.JuhuasuanBussiness;
import com.icloud.front.user.pojo.UserInfo;
import com.icloud.stock.model.User;
import com.icloud.user.dict.UserConstants;

public class UserInfoPo extends UserInfo {
	private String level;
	private String status;
	private String promotion;
	private String fatherName;
	private Integer fatherId;
	private Integer promotionId;
	private Integer statusId;
	private String statusOp;
	private String promotionOp;
	private long lastDayCount;
	private long currentDayCount;
	private long currentDayValidCount;
	private long lastDayValidCount;

	public void setAddUser(User user) {
		if (user.getLevel() == UserConstants.USER_LEVEL_LIMIT
				|| user.getPromotion() == 0) {
			this.setAddUser(false);
		} else {
			this.setAddUser(true);
		}
	}

	public static List<Integer> getUserIds(Collection<UserInfoPo> list) {
		if (ICloudUtils.isEmpty(list)) {
			return null;
		}
		List<Integer> ins = new ArrayList<Integer>();
		for (UserInfoPo po : list) {
			ins.add(po.getUserId());
		}
		return ins;
	}

	public static List<UserInfoPo> converUser(List<User> users,
			JuhuasuanBussiness juhuasuanBussiness) {
		if (ICloudUtils.isEmpty(users)) {
			return null;
		}
		List<UserInfoPo> list = new ArrayList<UserInfoPo>();
		UserInfoPo po = null;
		for (User user : users) {
			po = convertUser(user);

			if (ICloudUtils.isNotNull(po)) {
				long currentDayCount = juhuasuanBussiness
						.getCountOfJuhusuanDetailInCurrentDay(po.getUserId());
				long lastDayCount = juhuasuanBussiness
						.getCountOfJuhusuanDetailInLastDay(po.getUserId());
				long currentDayValidCount = juhuasuanBussiness
						.getValidCountOfJuhusuanDetailInCurrentDay(po
								.getUserId());
				long lastDayValidCount = juhuasuanBussiness
						.getValidCountOfJuhusuanDetailInLastDay(po.getUserId());
				po.setLastDayCount(lastDayCount);
				po.setCurrentDayCount(currentDayCount);
				po.setCurrentDayValidCount(currentDayValidCount);
				po.setLastDayValidCount(lastDayValidCount);
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
				po.setStatusId(1);
				po.setStatusOp("暂停运行");
			} else {
				po.setStatus("暂停服务");
				po.setStatusId(0);
				po.setStatusOp("启用帐号");
			}
			if (user.getPromotion() == 1
					&& user.getLevel() < UserConstants.USER_LEVEL_LIMIT) {
				po.setPromotion("正常代理");
				po.setPromotionId(1);
				po.setPromotionOp("取消代理");
			} else if (user.getPromotion() == 1
					&& user.getLevel() >= UserConstants.USER_LEVEL_LIMIT) {
				po.setPromotion("不能代理");
				po.setPromotionId(-1);
			} else {
				po.setPromotion("取消代理");
				po.setPromotionId(0);
				po.setPromotionOp("加入代理");
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

	public Integer getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Integer promotionId) {
		this.promotionId = promotionId;
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

	public String getPromotionOp() {
		return promotionOp;
	}

	public void setPromotionOp(String promotionOp) {
		this.promotionOp = promotionOp;
	}

	public long getLastDayCount() {
		return lastDayCount;
	}

	public void setLastDayCount(long lastDayCount) {
		this.lastDayCount = lastDayCount;
	}

	public long getCurrentDayCount() {
		return currentDayCount;
	}

	public void setCurrentDayCount(long currentDayCount) {
		this.currentDayCount = currentDayCount;
	}

	public long getCurrentDayValidCount() {
		return currentDayValidCount;
	}

	public void setCurrentDayValidCount(long currentDayValidCount) {
		this.currentDayValidCount = currentDayValidCount;
	}

	public long getLastDayValidCount() {
		return lastDayValidCount;
	}

	public void setLastDayValidCount(long lastDayValidCount) {
		this.lastDayValidCount = lastDayValidCount;
	}

}
