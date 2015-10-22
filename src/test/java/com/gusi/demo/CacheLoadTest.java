package com.gusi.demo;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gusi.demo.cache.CacheClientInstance;
import com.gusi.demo.cache.CacheNames;
import com.gusi.demo.pojo.Person;
import com.hazelcast.core.IMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/application-context.xml" })
public class CacheLoadTest {

	@Resource
	CacheClientInstance cacheInstance;

	@Test
	public void testLoad() {
		IMap<String, Person> map = cacheInstance.getHazelcast().getMap(
				CacheNames.CACHE_NAME_PERSON);
		System.out.println(map);
		Person p1 = map.get("1");
		System.out.println(p1);

		map.put("9", new Person(9, "gusi", 23, true));

		System.out.println(map.size());
		Person p9 = map.get("9");
		System.out.println(p9);
	}
}
