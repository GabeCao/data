package com.zytx.dao;

import org.springframework.web.bind.annotation.RequestParam;

import com.zytx.entity.User;

public interface UserDao {
	public User getUserByusername(@RequestParam("username") String username);

}
