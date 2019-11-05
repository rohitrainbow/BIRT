package com.dxc.reports.birt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dxc.reports.model.Client;
import com.dxc.reports.model.Login;
import com.dxc.reports.util.ReadProperties;

public class BirtUtil {
	final Logger logger = LoggerFactory.getLogger(FileAccess.class);

	public String checkSession(Login login) {
		String session = "inactive";
		try {
			ReadProperties readProperties = new ReadProperties();
			Map<String, String> historyGroups = new HashMap<String, String>();

			for (String group : readProperties.getLdapGroups("onlyHistoryGroups")) {
				historyGroups.put(group, group);
			}
			if (login != null) {
				List<String> clientNameList = readProperties.getClientName(login.getRole());

				List<Client> clientList = new ArrayList<Client>();

				if (!(clientNameList == null) && !clientNameList.isEmpty()) {
					Collections.sort(clientNameList);

					clientNameList.forEach(names -> clientList.add(new Client(names)));
				}

				if (historyGroups.get(login.getRole()) == null && !(clientList.isEmpty())) {
					session = "active";
				}
			}
		} catch (Exception e) {
			logger.error("checkSession() of BirtUtil", e);
		}

		return session;
	}
}
