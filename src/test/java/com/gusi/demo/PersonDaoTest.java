package com.gusi.demo;

import java.util.Random;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import javax.xml.ws.RespectBinding;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gusi.demo.cache.CacheClientInstance;
import com.gusi.demo.cache.CacheNames;
import com.gusi.demo.dao.PersonDao;
import com.gusi.demo.pojo.Person;
import com.hazelcast.core.IMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/application-context.xml" })
public class PersonDaoTest {

	@Resource(name = "personDao")
	private PersonDao dao;

	@Test
	public void testGetPerson() {
		Person p1 = dao.getPersonByKey("1");
		System.out.println(p1);
	}

	@Test
	public void testAddPerson() {
		for (int i = 0; i < 10; i++) {
			Person p = new Person(Math.abs(new Random().nextLong()), "测9试" + i,
					20 + i, new Random().nextBoolean());
			dao.addPerson(p);
		}
		System.out.println(dao.getPersonAll().size());
		dao.printPersonAll();
	}
}
