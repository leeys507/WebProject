//package com.example.wp;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.test.context.ContextConfiguration;	// +
//
//import com.wp.WebProjectApplication;	// +
//
//@SpringBootTest
//@ContextConfiguration(classes = WebProjectApplication.class)	// +
//class WebProjectApplicationTests {
//	@Autowired
//	private ApplicationContext context;
//
//	@Autowired
//	private SqlSessionFactory sessionFactory;
//
//	@Test
//	void contextLoads() {
//	}
//
//	@Test
//	public void testByApplicationContext() {
//		try {
//			System.out.println("=========================");
//			System.out.println(context.getBean("sqlSessionFactory"));
//			System.out.println("=========================");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Test
//	public void testBySqlSessionFactory() {
//		try {
//			System.out.println("=========================");
//			System.out.println(sessionFactory.toString());
//			System.out.println("=========================");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//}