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

@Configuration
@EnableScheduling
@Component
@PropertySource("file:${cronProperties}")
public class WREScheduledJobStarter {
	@Autowired
	private ScheduledFileAccess scheduledFileAccess;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	final Logger logger = LoggerFactory.getLogger(ScheduledJobStarter.class);

	ApplicationContext context;

	/*
	 * WRTRPS (Monthly) Start
	 * 
	 * 
	 */
	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule1.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob1() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule1", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob1 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule2.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob2() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule2", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob2 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule3.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob3() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule3", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob3 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule4.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob4() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule4", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob4 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule5.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob5() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule5", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob5 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule6.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob6() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule6", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob6 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule7.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob7() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule7", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob7 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule8.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob8() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule8", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob8 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule9.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob9() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule9", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob9 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule10.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob10() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule10", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob10 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule11.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob11() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule11", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob11 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule12.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob12() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule12", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob12 for WRE", e);
		}
	}

	/*
	 * WRTRPS (Monthly) End
	 * 
	 * 
	 */

	/*
	 * EXTMKTS COLI BOLI Monthly Start
	 * 
	 * 
	 */
	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule13.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob13() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule13", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob13 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule14.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob14() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule14", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob14 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule15.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob15() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule15", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob15 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule16.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob16() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule16", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob16 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule17.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob17() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule17", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob17 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule18.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob18() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule18", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob18 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule19.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob19() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule19", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob19 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule20.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob20() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule20", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob20 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule21.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob21() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule21", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob21 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule22.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob22() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule22", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob22 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule23.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob23() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule23", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob23 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule24.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob24() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule24", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob24 for WRE", e);
		}
	}
	/*
	 * EXTMKTS COLI BOLI Monthly End
	 * 
	 * 
	 */

	/*
	 * WRM QUARTERLY START
	 * 
	 * 
	 */

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule25.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob25() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule25", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob25 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule26.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob26() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule26", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob26 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule27.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob27() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule27", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob27 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule28.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob28() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule28", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob28 for WRE", e);
		}
	}
	/*
	 * WRM QUARTERLY End
	 * 
	 * 
	 */

	/*
	 * EXTMKTS QUARTERLY START
	 * 
	 * 
	 */

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule29.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob29() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule29", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob29 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule30.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob30() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule30", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob30 for WRE", e);
		}
	}
	/*
	 * EXTMKTS QUARTERLY End
	 * 
	 * 
	 */

	/*
	 * WRCNARPS (Monthly) Start
	 * 
	 * 
	 */
	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule31.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob31() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule31", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob31 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule32.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob32() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule32", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob32 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule33.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob33() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule33", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob33 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule34.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob34() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule34", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob34 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule35.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob35() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule35", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob35 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule36.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob36() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule36", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob36 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule37.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob37() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule37", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob37 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule38.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob38() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule38", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob38 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule39.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob39() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule39", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob39 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule40.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob40() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule40", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob40 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule41.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob41() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule41", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob41 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.claim_Financial_at_Policy_Level.schedule42.cronexpression}")
	void startClaimFinancialAtPolicyLevelJob42() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPolicyLevelJob("schedule42", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPolicyLevelJob42 for WRE", e);
		}
	}

	/*
	 * WRCNARPS (Monthly) End
	 * 
	 * 
	 */

	/*
	 * WRTRPS (Monthly) Start
	 * 
	 * 
	 */
	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule1.cronexpression}")
	void startClaimPolicyAggregateSPv9Job1() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule1", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job1 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule2.cronexpression}")
	void startClaimPolicyAggregateSPv9Job2() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule2", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job2 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule3.cronexpression}")
	void startClaimPolicyAggregateSPv9Job3() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule3", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job3 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule4.cronexpression}")
	void startClaimPolicyAggregateSPv9Job4() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule4", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job4 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule5.cronexpression}")
	void startClaimPolicyAggregateSPv9Job5() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule5", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job5 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule6.cronexpression}")
	void startClaimPolicyAggregateSPv9Job6() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule6", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job6 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule7.cronexpression}")
	void startClaimPolicyAggregateSPv9Job7() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule7", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job7 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule8.cronexpression}")
	void startClaimPolicyAggregateSPv9Job8() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule8", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job8 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule9.cronexpression}")
	void startClaimPolicyAggregateSPv9Job9() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule9", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job9 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule10.cronexpression}")
	void startClaimPolicyAggregateSPv9Job10() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule10", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job10 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule11.cronexpression}")
	void startClaimPolicyAggregateSPv9Job11() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule11", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job11 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule12.cronexpression}")
	void startClaimPolicyAggregateSPv9Job12() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule12", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job12 for WRE", e);
		}
	}

	/*
	 * WRTRPS (Monthly) End
	 * 
	 * 
	 */

	/*
	 * EXTMKTS COLI BOLI Monthly Start
	 * 
	 * 
	 */
	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule13.cronexpression}")
	void startClaimPolicyAggregateSPv9Job13() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule13", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job13 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule14.cronexpression}")
	void startClaimPolicyAggregateSPv9Job14() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule14", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job14 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule15.cronexpression}")
	void startClaimPolicyAggregateSPv9Job15() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule15", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job15 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule16.cronexpression}")
	void startClaimPolicyAggregateSPv9Job16() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule16", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job16 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule17.cronexpression}")
	void startClaimPolicyAggregateSPv9Job17() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule17", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job17 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule18.cronexpression}")
	void startClaimPolicyAggregateSPv9Job18() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule18", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job18 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule19.cronexpression}")
	void startClaimPolicyAggregateSPv9Job19() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule19", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job19 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule20.cronexpression}")
	void startClaimPolicyAggregateSPv9Job20() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule20", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job20 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule21.cronexpression}")
	void startClaimPolicyAggregateSPv9Job21() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule21", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job21 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule22.cronexpression}")
	void startClaimPolicyAggregateSPv9Job22() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule22", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job22 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule23.cronexpression}")
	void startClaimPolicyAggregateSPv9Job23() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule23", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job23 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule24.cronexpression}")
	void startClaimPolicyAggregateSPv9Job24() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule24", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job24 for WRE", e);
		}
	}
	/*
	 * EXTMKTS COLI BOLI Monthly End
	 * 
	 * 
	 */

	/*
	 * WRM QUARTERLY START
	 * 
	 * 
	 */

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule25.cronexpression}")
	void startClaimPolicyAggregateSPv9Job25() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule25", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job25 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule26.cronexpression}")
	void startClaimPolicyAggregateSPv9Job26() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule26", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job26 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule27.cronexpression}")
	void startClaimPolicyAggregateSPv9Job27() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule27", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job27 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule28.cronexpression}")
	void startClaimPolicyAggregateSPv9Job28() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule28", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job28 for WRE", e);
		}
	}
	/*
	 * WRM QUARTERLY End
	 * 
	 * 
	 */

	/*
	 * EXTMKTS QUARTERLY START
	 * 
	 * 
	 */

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule29.cronexpression}")
	void startClaimPolicyAggregateSPv9Job29() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule29", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job29 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule30.cronexpression}")
	void startClaimPolicyAggregateSPv9Job30() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule30", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job30 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule43.cronexpression}")
	void startClaimPolicyAggregateSPv9Job43() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule43", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job43 for WRE", e);
		}
	}

	/*
	 * EXTMKTS QUARTERLY End
	 * 
	 * 
	 */

	/*
	 * WRCNARPS (Monthly) Start
	 * 
	 * 
	 */
	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule31.cronexpression}")
	void startClaimPolicyAggregateSPv9Job31() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule31", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job31 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule32.cronexpression}")
	void startClaimPolicyAggregateSPv9Job32() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule32", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job32 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule33.cronexpression}")
	void startClaimPolicyAggregateSPv9Job33() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule33", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job33 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule34.cronexpression}")
	void startClaimPolicyAggregateSPv9Job34() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule34", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job34 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule35.cronexpression}")
	void startClaimPolicyAggregateSPv9Job35() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule35", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job35 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule36.cronexpression}")
	void startClaimPolicyAggregateSPv9Job36() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule36", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job36 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule37.cronexpression}")
	void startClaimPolicyAggregateSPv9Job37() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule37", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job37 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule38.cronexpression}")
	void startClaimPolicyAggregateSPv9Job38() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule38", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job38 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule39.cronexpression}")
	void startClaimPolicyAggregateSPv9Job39() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule39", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job39 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule40.cronexpression}")
	void startClaimPolicyAggregateSPv9Job40() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule40", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job40 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule41.cronexpression}")
	void startClaimPolicyAggregateSPv9Job41() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule41", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job41 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.Claim_Policy_Aggregate_SP_V9.schedule42.cronexpression}")
	void startClaimPolicyAggregateSPv9Job42() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimPolicyAggregateSPv9Job("schedule42", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimPolicyAggregateSPv9Job42 for WRE", e);
		}
	}

	/*
	 * WRCNARPS (Monthly) End
	 * 
	 * 
	 */

	/*
	 * WRR_WHU (Daily) Start
	 * 
	 * 
	 */

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule1.cronexpression}")
	void startClaimsDetailReportJob() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule1", "WRE", "LIFEDLY");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob1 for WRE", e);
		}
	}

	/*
	 * WRR_WHU (Daily) End
	 * 
	 * 
	 */

	/*
	 * ALL_BUT_RPSW (Monthly) Start
	 * 
	 * 
	 */

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule2.cronexpression}")
	void startClaimsDetailReportJob2() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule2", "WRE", "LIFEMTHLY");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob2 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule3.cronexpression}")
	void startClaimsDetailReportJob3() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule3", "WRE", "LIFEMTHLY");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob3 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule4.cronexpression}")
	void startClaimsDetailReportJob4() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule4", "WRE", "LIFEMTHLY");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob4 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule5.cronexpression}")
	void startClaimsDetailReportJob5() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule5", "WRE", "LIFEMTHLY");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob5 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule6.cronexpression}")
	void startClaimsDetailReportJob6() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule6", "WRE", "LIFEMTHLY");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob6 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule7.cronexpression}")
	void startClaimsDetailReportJob7() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule7", "WRE", "LIFEMTHLY");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob7 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule8.cronexpression}")
	void startClaimsDetailReportJob8() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule8", "WRE", "LIFEMTHLY");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob8 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule9.cronexpression}")
	void startClaimsDetailReportJob9() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule9", "WRE", "LIFEMTHLY");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob9 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule10.cronexpression}")
	void startClaimsDetailReportJob10() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule10", "WRE", "LIFEMTHLY");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob10 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule11.cronexpression}")
	void startClaimsDetailReportJob11() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule11", "WRE", "LIFEMTHLY");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob11 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule12.cronexpression}")
	void startClaimsDetailReportJob12() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule12", "WRE", "LIFEMTHLY");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob12 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule13.cronexpression}")
	void startClaimsDetailReportJob13() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule13", "WRE", "LIFEMTHLY");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob13 for WRE", e);
		}
	}

	/*
	 * ALL_BUT_RPSW (Monthly) End
	 * 
	 * 
	 */

	/*
	 * BPOPS 2559 RPS ESS Extracts Monthly WRCNARPS Start
	 * 
	 * 
	 */

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule14.cronexpression}")
	void startClaimsDetailReportJob14() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule14", "WRE", "");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob14 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule15.cronexpression}")
	void startClaimsDetailReportJob15() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule15", "WRE", "");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob15 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule16.cronexpression}")
	void startClaimsDetailReportJob16() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule16", "WRE", "");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob16 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule17.cronexpression}")
	void startClaimsDetailReportJob17() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule17", "WRE", "");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob17 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule18.cronexpression}")
	void startClaimsDetailReportJob18() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule18", "WRE", "");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob18 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule19.cronexpression}")
	void startClaimsDetailReportJob19() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule19", "WRE", "");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob19 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule20.cronexpression}")
	void startClaimsDetailReportJob20() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule20", "WRE", "");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob20 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule21.cronexpression}")
	void startClaimsDetailReportJob21() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule21", "WRE", "");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob21 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule22.cronexpression}")
	void startClaimsDetailReportJob22() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule22", "WRE", "");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob22 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule23.cronexpression}")
	void startClaimsDetailReportJob23() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule23", "WRE", "");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob23 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule24.cronexpression}")
	void startClaimsDetailReportJob24() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule24", "WRE", "");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob24 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule25.cronexpression}")
	void startClaimsDetailReportJob25() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule25", "WRE", "");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob25 for WRE", e);
		}
	}

	/*
	 * BPOPS 2559 RPS ESS Extracts Monthly WRCNARPS End
	 * 
	 * 
	 */

	/*
	 * BPOPS 2559 RPS ESS Extracts Monthly WRTRPS Start
	 * 
	 * 
	 */

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule26.cronexpression}")
	void startClaimsDetailReportJob26() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule26", "WRE", "");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob26 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule27.cronexpression}")
	void startClaimsDetailReportJob27() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule27", "WRE", "");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob27 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule28.cronexpression}")
	void startClaimsDetailReportJob28() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule28", "WRE", "");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob28 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule29.cronexpression}")
	void startClaimsDetailReportJob29() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule29", "WRE", "");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob29 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule30.cronexpression}")
	void startClaimsDetailReportJob30() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule30", "WRE", "");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob30 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule31.cronexpression}")
	void startClaimsDetailReportJob31() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule31", "WRE", "");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob31 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule32.cronexpression}")
	void startClaimsDetailReportJob32() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule32", "WRE", "");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob32 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule33.cronexpression}")
	void startClaimsDetailReportJob33() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule33", "WRE", "");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob33 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule34.cronexpression}")
	void startClaimsDetailReportJob34() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule34", "WRE", "");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob34 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule35.cronexpression}")
	void startClaimsDetailReportJob35() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule35", "WRE", "");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob35 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule36.cronexpression}")
	void startClaimsDetailReportJob36() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule36", "WRE", "");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob36 for WRE", e);
		}
	}

	@Scheduled(cron = "${WRE.ClaimsDetailReport.schedule37.cronexpression}")
	void startClaimsDetailReportJob37() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimsDetailReportJob("schedule37", "WRE", "");

		} catch (Exception e) {
			logger.error("Issue in startClaimsDetailReportJob37 for WRE", e);
		}
	}

	/*
	 * BPOPS 2559 RPS ESS Extracts Monthly WRTRPS End
	 * 
	 * 
	 */
	
	
	
	/*
	 * BPOPS-2718 CsaDeathClaimPending Monthly Start
	 *
	 */
	@Scheduled(cron = "${WRE.csA_Death_Claim_Pending_Report.schedule1.cronexpression}")
	void startCsaDeathClaimPendingJob1() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			final Calendar c = Calendar.getInstance();
			if (c.get(Calendar.DATE) == c.getActualMaximum(Calendar.DATE)) {
				// do your stuff

				scheduledFileAccess.sendCsaDeathClaimPendingJob("schedule1", "WRE");
			}

		} catch (Exception e) {
			logger.error("Issue in startCsaDeathClaimPendingJob1", e);
		}
	}

	/*
	 * BPOPS-2718 CsaDeathClaimPending Monthly End
	 *
	 */


	@Scheduled(cron = "${WRE.Claim_Financial_at_Payee_Level_SP_v7.schedule1.cronexpression}")
	void startClaimFinancialAtPayeeLevelSPv7Job2() {

		// SendMail sm=new SendMail();
		// sm.main(null);

		try {
			scheduledFileAccess.sendClaimFinancialAtPayeeLevelSPv7Job("schedule1", "WRE");

		} catch (Exception e) {
			logger.error("Issue in startClaimFinancialAtPayeeLevelSPv7Job2", e);
		}
	}
}
