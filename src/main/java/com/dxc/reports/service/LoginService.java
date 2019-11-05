package com.dxc.reports.service;

import java.util.List;

import javax.naming.NamingException;

import com.dxc.reports.model.Login;

public interface LoginService {
	List<String> validateUser(Login login) throws NamingException;
}
