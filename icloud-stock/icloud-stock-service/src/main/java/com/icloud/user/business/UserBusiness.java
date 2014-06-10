package com.icloud.user.business;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.icloud.user.service.ISessionService;


public class UserBusiness {
	@Resource(name = "sessionService")
	protected ISessionService sessionService;
}
