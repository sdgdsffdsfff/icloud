package com.cninfo.shtb.member.vo;

import org.dozer.DozerBeanMapper;

import com.cninfo.shtb.member.domain.AccountAggregate;
import com.icloud.framework.util.ICloudUtils;

public class AccountVO {
	private String userName;
	private int userId;
	private int money;

	public static AccountVO convertAccountVO(AccountAggregate account) {
		if (!ICloudUtils.isNotNull(account))
			return null;
		DozerBeanMapper mapper = new DozerBeanMapper();
		AccountVO accountVO = (AccountVO) mapper.map(account, AccountVO.class);
		return accountVO;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

}
