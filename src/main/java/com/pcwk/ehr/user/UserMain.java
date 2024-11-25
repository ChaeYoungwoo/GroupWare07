package com.pcwk.ehr.user;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserMain {

	final Logger log = LogManager.getLogger(UserMain.class);

	UserDao dao;
	UserVO user;

	ApplicationContext context;

	public UserMain() {

		// context = new AnnotationConfigApplicationContext(DaoFactory.class);
		context = new GenericXmlApplicationContext("applicationContext.xml");

		dao = context.getBean("userDao", UserDao.class);
		// dao = (UserDao)context.getBean("userDao"); //위에 방법이랑 똑같음 인자 한 개를 쓸 건지 두 개를 쓸
		// 건지
		user = new UserVO("james01", "이상무01", "4321", "사용하지 않음");

	}

	public void doSave() {
		log.debug("doSave");
		try {
			int flag = dao.doSave(user);

			if (1 == flag) {
				log.debug("*****************************");
				log.debug("등록 성공");
				log.debug("*****************************");
			} else {
				log.debug("등록 실패");
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void doSelectOne() {
		log.debug("doSelectOne");
		try {
			UserVO outVO = dao.doSelectOne(user);

			if (null != outVO) {
				log.debug("*****************************");
				log.debug("조회 성공: " + outVO.toString());
				log.debug("*****************************");
			} else {
				log.debug("조회 실패");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteAll() throws SQLException {
		log.debug("deleteAll");
		dao.deleteAll();
	}
	
	private void getCount() throws SQLException {
		log.debug("getCount");
		dao.getCount();
		
	}

	public static void main(String[] args) throws SQLException {
		UserMain main = new UserMain();
		
		//전체 삭제
		main.deleteAll();
		
		// 등록
		main.doSave();

		// 단건조회
		main.doSelectOne();
		
		// 건수조회
		main.getCount();



	}

	
}
