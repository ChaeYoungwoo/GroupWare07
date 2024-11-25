package com.pcwk.ehr;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
public class JUnitLifeCycle {

	static final Logger log = LogManager.getLogger(JUnitLifeCycle.class);
	
	@BeforeClass
	public static void before() {
		log.debug("*@BeforeClass=");
	}
	
	@AfterClass
	public static void After() {
		log.debug("*@AfterClass=");
	}
	
	
	@Before //@Test 메소드 전에 수행
	public void setUp() throws Exception {
		log.debug("==============================================");
		log.debug("=@Before=");
		log.debug("==============================================");
	}

	@After //@Test 메소드 수행 이후 RUN
	public void tearDown() throws Exception {
		log.debug("==============================================");
		log.debug("=@After=");
		log.debug("==============================================");
	}

	@Test
	public void test() {
		log.debug("**********************************************");
		log.debug("=@TEST=");
		log.debug("**********************************************");
	}
	
	@Test
	public void test2() {
		log.debug("**********************************************");
		log.debug("=@TEST2=");
		log.debug("**********************************************");
	}

}
