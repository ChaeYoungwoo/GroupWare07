package com.pcwk.ehr;

import static org.junit.Assert.*;
import org.junit.Test;
import org.apache.logging.log4j.*;
import static org.junit.Assert.*;

public class JUnitTest01 {
	
	final Logger log = LogManager.getLogger(getClass());
	
//	JUnit4 테스트 메소드 작성 방법:
//
//		1. 테스트 메소드는 public
//		2. 메소드에 @Test를 사용한다.
//		3. return은 항상 void
//		4. 파라미터(인자) 사용할 수 없다.
	
	@Test
	public void testSubtraction() {
		int x = 13; 
		int y = 15;
		
		int result = x - y;
		
		assertTrue(result == -2); //조건이 TRUE인지 확인
	}
	
	@Test
	public void testAddition() {
		int x = 13;
		int y = 15;
		
		int result = x + y;
		
		assertEquals(28, result); //기대값, 실제값
	}
	
	@Test
	public void test() {
		log.debug("------------------------------------------------------");
		log.debug("test");
		log.debug("------------------------------------------------------");
	}
	
	@Test
	public void pcwkTest() {
		log.debug("------------------------------------------------------");
		log.debug("pcwkTest");
		log.debug("------------------------------------------------------");
	}
}
