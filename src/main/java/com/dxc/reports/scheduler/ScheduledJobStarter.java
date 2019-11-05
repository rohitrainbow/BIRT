package com.dxc.reports.scheduler;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//import com.dxc.reports.email.SendMail;

/**
 * @author CSC
 * 
 */
@Configuration
@EnableScheduling
@Component
@PropertySource("file:${cronProperties}")
public class ScheduledJobStarter{
	
	@Autowired
	private ScheduledFileAccess scheduledFileAccess;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	final Logger logger = LoggerFactory.getLogger(ScheduledJobStarter.class);

	ApplicationContext context;

	@Scheduled(cron = "${AIG.Claim_Policy_Aggregate_SP_V9.schedule1.cronexpression}")
	void startClaimPolicyAggregateSPv9Job1() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule1","AIG");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job1",e);
		}
	}
	
	

	
	

	
	@Scheduled(cron = "${AIG.claim_Financial_at_Policy_Level.schedule1.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob1() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule1","AIG");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob1",e);
		}
	}
	
	
	
	@Scheduled(cron = "${AIG.Claim_Financial_at_Payee_Level_SP_v7.schedule1.cronexpression}")
	void startClaimFinancialAtPayeeLevelSPv7Job1() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			
			scheduledFileAccess.sendClaimFinancialAtPayeeLevelSPv7Job("schedule1","AIG");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPayeeLevelSPv7Job1",e);
		}
	}
	
	

	
	@Scheduled(cron = "${ATH.Claim_Financial_at_Payee_Level_SP_v7.schedule1.cronexpression}")
	void startClaimFinancialAtPayeeLevelSPv7Job3() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPayeeLevelSPv7Job("schedule1","ATH");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPayeeLevelSPv7Job3",e);
		}
	}
	
	@Scheduled(cron = "${ATH.Claim_Financial_at_Payee_Level_SP_v7.schedule2.cronexpression}")
	void startClaimFinancialAtPayeeLevelSPv7Job4() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPayeeLevelSPv7Job("schedule2","ATH");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPayeeLevelSPv7Job4",e);
		}
	}
	
	@Scheduled(cron = "${ATH.Claim_Financial_at_Payee_Level_SP_v7.schedule3.cronexpression}")
	void startClaimFinancialAtPayeeLevelSPv7Job5() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPayeeLevelSPv7Job("schedule3","ATH");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPayeeLevelSPv7Job5",e);
		}
	}
	
	@Scheduled(cron = "${ATH.Claim_Financial_at_Payee_Level_SP_v7.schedule4.cronexpression}")
	void startClaimFinancialAtPayeeLevelSPv7Job6() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPayeeLevelSPv7Job("schedule4","ATH");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPayeeLevelSPv7Job6",e);
		}
	}
	
}
