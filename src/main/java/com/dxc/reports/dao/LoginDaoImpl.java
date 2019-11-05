package com.dxc.reports.dao;

import java.util.List;

import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dxc.reports.model.Login;
import com.dxc.reports.security.LDAPConnect;
import com.dxc.reports.web.ApplicationController;

@Repository
public class LoginDaoImpl implements LoginDao {
	@Autowired
	private LDAPConnect ldapConnect;

	private final Logger logger = LoggerFactory.getLogger(ApplicationController.class);

	public List<String> validateUser(Login login) throws NamingException {
		try {
			return ldapConnect.loginCheck(login.getUsername(), login.getPassword());
		} catch (NamingException e) {
			logger.error("Unauthorized Access", e);
			throw e;
		}
		// return "Success";
	}

}
