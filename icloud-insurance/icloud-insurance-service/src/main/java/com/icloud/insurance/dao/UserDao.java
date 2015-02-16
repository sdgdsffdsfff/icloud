package com.icloud.insurance.dao;

import org.springframework.stereotype.Repository;

import com.icloud.insurance.model.User;

@Repository("userDao")
public class UserDao extends InsuranceBaseDao<User> {

}
