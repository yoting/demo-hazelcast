package com.gusi.demo.cache;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gusi.demo.pojo.Person;

/**
 * 缓存预先加载器
 * 
 * @author GUSI
 *
 */
public class CachePreLoader {
	private static final Logger logger = LoggerFactory
			.getLogger(CachePreLoader.class);

	// ------------------------------------------------------------------------------------------

	private CacheClientInstance cacheInstance;
	private Persist2Cache persist2cache;

	public CachePreLoader() {
	}

	public void setCacheInstance(CacheClientInstance cacheInstance) {
		this.cacheInstance = cacheInstance;
	}

	public void setPersist2cache(Persist2Cache persist2cache) {
		this.persist2cache = persist2cache;
	}

	// ------------------------------------------------------------------------------------------

	public void preload() {
		logger.debug("cache clear.");
		clear();
		logger.debug("cache cleaned.");

		logger.debug("cache mapping");
		mapping();
		logger.debug("cache mapped.");

		cacheInstance.mapper(CacheNames.CACHE_NAME_PERSON).addIndex("id", true);
	}

	public void preload(String cacheObject) {
		cacheInstance.clear(cacheObject);
		logger.info(cacheObject + " cache cleaned.");
		if (cacheObject.equalsIgnoreCase(CacheNames.CACHE_NAME_PERSON)) {
			mappingPerson();
		}

		logger.info(cacheObject + " cache mapped.");
	}

	// ------------------------------------------------------------------------------------------

	private void clear() {
		cacheInstance.clear(CacheNames.CACHE_NAME_PERSON);
	}

	private void mapping() {
		mappingPerson();
	}

	// ------------------------------------------------------------------------------------------

	private void mappingPerson() {
		int count = 0;
		Iterator<Person> it = persist2cache.iteratorPersion();
		while (it.hasNext()) {
			Person person = it.next();
			String key = String.valueOf(person.getId());
			cacheInstance.insert(CacheNames.CACHE_NAME_PERSON, key, person);
		}

		logger.info("Persoin Load Success! count:" + count);

		cacheInstance.mapper(CacheNames.CACHE_NAME_PERSON).addIndex("name",
				true);
		logger.info("Person Add Index Success!");
	}
}
