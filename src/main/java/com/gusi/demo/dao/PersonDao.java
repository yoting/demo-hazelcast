package com.gusi.demo.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.gusi.demo.cache.CacheClientInstance;
import com.gusi.demo.cache.CacheNames;
import com.gusi.demo.pojo.Person;
import com.hazelcast.core.IMap;

@Repository(value = "personDao")
@Transactional
public class PersonDao {

	@Resource
	private CacheClientInstance cacheInstance;

	@Resource
	private HibernateTemplate hibernateTemplate;
	@Resource
	private SessionFactory sessionFactory;

	public Iterator<Person> getPersonIterator() {
		Iterator<Person> it = null;

		String queryString = "from Person";
		List<Person> list = (List<Person>) hibernateTemplate.find(queryString);

		it = list.iterator();
		return it;
	}

	private IMap<String, Person> getMap() {
		IMap<String, Person> map = cacheInstance.getHazelcast().getMap(
				CacheNames.CACHE_NAME_PERSON);
		return map;
	}

	public void printPersonAll() {
		List<Person> list = getPersonAll();
		for (Person p : list) {
			System.out.println(p);
		}
	}

	public boolean addPerson(Person person) {
		IMap<String, Person> map = getMap();
		map.put(person.getId() + "", person);

		hibernateTemplate.save(person);
		// Session session = sessionFactory.openSession();
		// session.beginTransaction();
		// session.save(person);
		// session.getTransaction().commit();
		// session.flush();
		// session.close();

		return true;
	}

	public Person getPersonByKey(String id) {
		IMap<String, Person> map = getMap();
		Person person = map.get(id);
		return person;
	}

	public List<Person> getPersonAll() {
		IMap<String, Person> map = getMap();
		List<Person> list = new ArrayList<Person>(map.values());
		return list;
	}
}
