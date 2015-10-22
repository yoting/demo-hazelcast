package com.gusi.demo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gusi.demo.dao.PersonDao;
import com.gusi.demo.pojo.Person;

/**
 * Unit test for simple App.
 */
public class AppTest {

	@Test
	public void testEV() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"application-context.xml");
		PersonDao dao = (PersonDao) ac.getBean("personDao");
		System.out.println(dao);
		dao.addPerson(new Person(1, "cs", 11, false));
	}

}
