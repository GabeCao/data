package com.zytx.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.zytx.dao.UserDao;
import com.zytx.entity.User;

public class LoginRealm extends AuthorizingRealm {

	@Autowired
	private UserDao userDao;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		String username = usernamePasswordToken.getUsername();
		User user = userDao.getUserByusername(username);
		ByteSource salt = ByteSource.Util.bytes(user.getUsername());
		if(user != null) {
			AuthenticationInfo info = new 
					SimpleAuthenticationInfo(user, user.getPassword(),salt , this.getName());
			return info;
		}
		return null;
	}

}
