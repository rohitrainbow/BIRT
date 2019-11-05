package com.dxc.reports.dao;

import java.util.List;

import javax.naming.NamingException;

import com.dxc.reports.model.Login;

public interface LoginDao {
	List<String> validateUser(Login login) throws NamingException;
}
