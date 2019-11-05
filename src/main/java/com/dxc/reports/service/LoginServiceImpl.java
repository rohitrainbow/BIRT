package com.dxc.reports.service;

import java.util.List;

import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.reports.dao.LoginDao;
import com.dxc.reports.model.Login;
import com.dxc.reports.web.ApplicationController;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;

	private final Logger logger = LoggerFactory.getLogger(ApplicationController.class);

	@Override
	public List<String> validateUser(Login login) throws NamingException {
		// TODO Auto-generated method stub
		// return loginDao.validateUser(login);

		try {
			return loginDao.validateUser(login);
		} catch (NamingException e) {
			logger.error("Unauthorized Access", e);
			throw e;
		}
	}

}
