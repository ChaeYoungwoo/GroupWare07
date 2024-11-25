package com.pcwk.ehr.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)	
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class UserDaoJUnit {

	final Logger log = LogManager.getFormatterLogger(getClass());

	
	UserVO userVO01;
	UserVO userVO02;
	UserVO userVO03;

	@Autowired //테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 의해 자동으로 주입된다.
	ApplicationContext context; //자동으로 주입되니 메소드를 시작할 때마다 새로운 객체를 만들지 않고
								//각 메소드 실행마다 만들어지는 주소값이 동일하다 = 빠르고 용량 적게 먹음
	
	@Autowired
	UserDao dao;
	
	
	@Before // @Test 수행 전 실행
	public void setUp() throws Exception {

		userVO01 = new UserVO("james01", "이상무01", "4321", "사용하지 않음");
		userVO02 = new UserVO("james02", "이상무02", "4321", "사용하지 않음");
		userVO03 = new UserVO("james03", "이상무03", "4321", "사용하지 않음");
	}
	
	@Test
	public void beans() {
		assertNotNull(context);
	}
	
	//메소드 예외사항 테스트: NullPointerException이 발생하면 성공
	//(expected = NullPointerException.class)
	@Test(expected = NullPointerException.class)
	public void getFailure() throws SQLException{
		
		//매번 동일 결과가 도출되도록 작성.
		//0. 전체 삭제
		//1. 건수 조회
		//2. 1건 등록
		//3. 1건 조회
		
		//0
		dao.deleteAll();
		
		//1.
		int count= dao.getCount();
		assertEquals(0,count);
		
		//2.
		dao.doSave(userVO01);
		
		//3.
		String unKnownId = userVO01.getUserId() + "99";
		userVO01.setUserId(unKnownId);
		UserVO outVO = dao.doSelectOne(userVO01);
		assertNotNull(outVO);
		
	}
	
	@Test
	public void getCount() throws SQLException {
		// 매번 동일 결과과 도축되도록 작성.
		// 0. 전체 삭제
		// 1. 건수 조회
		// 2. 1건 등록
		// 3. 건수 조회: 1

		// 2. 1건 등록
		// 3. 건수 조회: 2

		// 2. 1건 등록
		// 3. 건수 조회: 3

		// 0.
		dao.deleteAll();

		// 1. 삭제 건수: 0건
		int count = dao.getCount();
		assertEquals(0, count);

		// 2. 1건 등록
		dao.doSave(userVO01);

		// 3.
		count = dao.getCount();
		assertEquals(1, count);

		// 2. 1건 등록
		dao.doSave(userVO02);

		// 3.
		count = dao.getCount();
		assertEquals(2, count);

		// 2. 1건 등록
		dao.doSave(userVO03);

		// 3.
		count = dao.getCount();
		assertEquals(3, count);

	}

	@After
	public void tearDown() throws Exception {
		log.debug("==============================================");
		log.debug("=@After=");
		log.debug("==============================================");
	}
	
	@Test(timeout = 500) //addAndGet 메소드 총 수행 시간이 500/1000초 이내에 들어오면 성공, 단위는 miliseconds
	public void addAndGet() throws SQLException {
		// 매번 동일 결과과 도출되도록 작성.
		// 0. 전체 삭제
		// 1. 건수 조회
		// 2. 1건 등록
		// 3. 건수 조회
		// 4. 한건 조회
		// 5. 등록데이터 입력데이터 비교

		// 0
		dao.deleteAll();

		// 1 삭제 건수 조회, 0건이 나오면 성공
		int count = dao.getCount();
		assertEquals(0, count);

		// 2 1건 등록
		dao.doSave(userVO01);

		// 3 등록 건수 조회
		count = dao.getCount();
		assertEquals(1, count);

		// 4.
		UserVO outVO01 = dao.doSelectOne(userVO01);
		// Not Null 확인
		assertNotNull(outVO01);

		// 5.
		isSameUser(outVO01, userVO01);

	}

	public void isSameUser(UserVO outVO01, UserVO userVO01) {
		assertEquals(outVO01.getUserId(), userVO01.getUserId());
		assertEquals(outVO01.getName(), userVO01.getName());
		assertEquals(outVO01.getPassword(), userVO01.getPassword());
	}

}
