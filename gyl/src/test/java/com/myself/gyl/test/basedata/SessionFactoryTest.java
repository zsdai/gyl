package com.myself.gyl.test.basedata;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Test;

import com.myself.gyl.utils.SpringUtils;

public class SessionFactoryTest extends SpringUtils{
	@Test
	public void sessionFactoryTest(){
		context.getBean("sessionFactory");
	}
	@Test
	public void queryCountTest(){
		SessionFactory factory=(SessionFactory)context.getBean("sessionFactory");
		Session session = factory.openSession();
		Long count =(Long)session.createQuery("select count(*) from Department").uniqueResult();
		System.out.println(count);
		session.close();
	}
}
