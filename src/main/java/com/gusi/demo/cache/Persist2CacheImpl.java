package com.gusi.demo.cache;

import java.util.Iterator;

import com.gusi.demo.dao.PersonDao;
import com.gusi.demo.pojo.Person;

/**
 * 持久化对象转换为缓存对象实现类
 * 
 * @author GUSI
 *
 */
public class Persist2CacheImpl implements Persist2Cache {
	private PersonDao personDao;

	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public Iterator<Person> iteratorPersion() {
		return personDao.getPersonIterator();
	}

}
