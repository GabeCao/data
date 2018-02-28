package com.zytx;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zytx.dao.UserDao;
import com.zytx.entity.User;

public class UserDaoTest extends BaseTest{
	@Autowired
	private UserDao userDao;
	
	@Test
	public void testUserDao() {
		User user = userDao.getUserByusername("qw");
		System.out.println(user);
	}

}
