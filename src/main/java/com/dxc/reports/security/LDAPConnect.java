package com.dxc.reports.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dxc.reports.util.ReadProperties;

@Component
public class LDAPConnect {
	@Autowired
	private ReadProperties readProperties;
	final Logger logger = LoggerFactory.getLogger(LDAPConnect.class);

	public List<String> loginCheck(String userName, String password) throws NamingException {
		try {

			String[] ldapDetails = new String[3];
			ldapDetails = readProperties.ldapDetailsReader();
			// String base = "ou=uniamzuat";
			// String dn=

			Hashtable<String, String> table = new Hashtable<>();
			// table.put(Context.PROVIDER_URL,
			// "ldap://CSCDVWBNOR461.fsg.amer.csc.com:389");
			table.put(Context.SECURITY_AUTHENTICATION, "simple");
			table.put(Context.PROVIDER_URL, ldapDetails[0]);
			// table.put(Context.SECURITY_PRINCIPAL, "cn=" + "CMATEST2" + "," +
			// "ou=uniamzist");
			table.put(Context.SECURITY_PRINCIPAL, "cn=" + userName + "," + "ou=" + ldapDetails[1]);

			// table.put(Context.SECURITY_CREDENTIALS, "XXXXX");
			table.put(Context.SECURITY_CREDENTIALS, password);
			// table.put(Context.INITIAL_CONTEXT_FACTORY,
			// "com.sun.jndi.ldap.LdapCtxFactory");
			table.put(Context.INITIAL_CONTEXT_FACTORY, ldapDetails[2]);

			DirContext ctx = new InitialDirContext(table);
			SearchControls constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);

			List<String> ldapGroups = readProperties.getLdapGroups("groups");
			List<String> userRoles = new ArrayList<String>();
			for (int i = 0; i < ldapGroups.size(); i++) {
				String[] attrIDs = { "member" };
				constraints.setReturningAttributes(attrIDs);
				NamingEnumeration answer = ctx.search("ou=" + ldapDetails[1], "cn=" + ldapGroups.get(i), constraints);
				if (answer.hasMore()) {
					Attributes attrs = ((SearchResult) answer.next()).getAttributes();
					for (NamingEnumeration ae = attrs.getAll(); ae.hasMore();) {
						Attribute attr = (Attribute) ae.next();
						/* print each value */
						/*
						 * for (NamingEnumeration e = attr.getAll();
						 * e.hasMore(); System.out .println("value: " +
						 * e.next())) {
						 * 
						 * }
						 */
						NamingEnumeration e = attr.getAll();
						while (e.hasMore()) {

							// System.out.println(user);

							if (((e.next() + "").split("=|,")[1]).equalsIgnoreCase(userName)) {
								userRoles.add(ldapGroups.get(i));

							}
						}
					}

				}
			}
			if (!(userRoles == null) && !userRoles.isEmpty())
				Collections.sort(userRoles);
			return userRoles;
		} catch (NamingException e) {
			logger.error("Unauthorized Access", e);
			throw e;
		}
	}

	/*
	 * public void getUserEmailAddress(String userName, String password) {
	 * NamingEnumeration<SearchResult> searchResults = null; String[]
	 * ldapDetails = new String[3]; ldapDetails =
	 * readProperties.ldapDetailsReader(); try { Hashtable<Object, Object> env =
	 * new Hashtable<>(); env.put(Context.INITIAL_CONTEXT_FACTORY,
	 * ldapDetails[2]); env.put(Context.SECURITY_AUTHENTICATION, "Simple");
	 * env.put(Context.SECURITY_PRINCIPAL, "cn=" + userName + "," + "ou=" +
	 * ldapDetails[1]); env.put(Context.SECURITY_CREDENTIALS, password);
	 * env.put(Context.PROVIDER_URL, ldapDetails[0]); DirContext ctx = new
	 * InitialDirContext(env); if (ctx != null) { try { SearchControls
	 * constraints = new SearchControls();
	 * constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
	 * 
	 * String[] attrIDs = { "mail", "telephonenumber" }; You can have multiple
	 * attributes like SN, telephonenumber, mail etc..
	 * 
	 * String[] attrIDs = { "mail" };
	 * constraints.setReturningAttributes(attrIDs);
	 * 
	 * NamingEnumeration answer = ctx.search("dc=csc,dc=com", "sAMAccountName="
	 * + "rchaudha", constraints); if (answer.hasMore()) { Attributes attrs =
	 * ((SearchResult) answer.next()).getAttributes();
	 * System.out.println("mail " + attrs.get("mail")); } else { throw new
	 * Exception("Invalid User"); }
	 * 
	 * } catch (Exception ex) { ex.printStackTrace(); } }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } }
	 */
}
