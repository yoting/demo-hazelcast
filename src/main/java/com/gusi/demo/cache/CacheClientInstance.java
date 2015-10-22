package com.gusi.demo.cache;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.IQueue;
import com.hazelcast.core.MultiMap;
import com.hazelcast.query.SqlPredicate;

/**
 * 缓存客户端实例，实际上是对原有客户端的封装
 * 
 * @author GUSI
 *
 */
public class CacheClientInstance {

	private static final Logger logger = LoggerFactory
			.getLogger(CacheClientInstance.class);

	// -----------------------------------------------------------------

	public static void main(String[] args) {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.getGroupConfig().setName("dev").setPassword("dev-pass");
		clientConfig.getNetworkConfig().addAddress("192.168.0.95:5701");

		HazelcastInstance client = HazelcastClient
				.newHazelcastClient(clientConfig);

		IMap<String, String> map = client.getMap("test");
		map.put("1", "test1");
		map.put("2", "test2");
		map.put("3", "test3");
		map.put("4", "test4");
		map.put("5", "test5");
		map = client.getMap("test");

		List<String> temp = (List<String>) map.values();

		for (String str : temp) {
			if (str.equals("test3")) {
				System.out.println("开始移除");
				map.remove("3");
			}
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(temp);

		map = client.getMap("test");
		System.out.println(map.values());
		client.shutdown();
	}

	// -----------------------------------------------------------------

	private HazelcastInstance hazelcast = null;

	public CacheClientInstance() {
	}

	public void setHazelcast(HazelcastInstance hazelcast) {
		this.hazelcast = hazelcast;
	}

	public HazelcastInstance getHazelcast() {
		if (hazelcast == null) {
			logger.info("hazelcast unavailable");
		}

		return hazelcast;
	}

	public void destory() {
		// HazelcastClient.shutdownAll();
		hazelcast.shutdown();
	}

	// -----------------------------------------------------------------

	public <T> IQueue<T> queue(String name) {
		return getHazelcast().getQueue(name);
	}

	public Object poll(String name) {
		return getHazelcast().getQueue(name).poll();
	}

	public void offer(String name, Object value) {
		getHazelcast().getQueue(name).offer(value);
	}

	// -----------------------------------------------------------------

	public <T> IMap<String, T> mapper(String name) {
		return getHazelcast().getMap(name);
	}

	public void clear(String name) {
		getHazelcast().getMap(name).clear();
	}

	public void insert(String name, String key, Object value) {
		getHazelcast().getMap(name).put(key, value);
	}

	public void update(String name, String key, Object value) {
		getHazelcast().getMap(name).put(key, value);
	}

	public void delete(String name, String key) {
		getHazelcast().getMap(name).remove(key);
	}

	public void evict(String name, Object value) {
		getHazelcast().getMap(name).evict(value);
	}

	public boolean contain(String name, String key) {
		return getHazelcast().getMap(name).containsKey(key);
	}

	public Object locate(String name, String key) {
		return getHazelcast().getMap(name).get(key);
	}

	public Collection<Object> pager(String name) {
		return getHazelcast().getMap(name).values();
	}

	public Collection<Object> pager(String name, String hql) {
		return getHazelcast().getMap(name).values(new SqlPredicate(hql));
	}

	// -----------------------------------------------------------------

	public <T> MultiMap<String, T> m_mapper(String name) {
		return getHazelcast().getMultiMap(name);
	}

	public void m_clear(String name) {
		getHazelcast().getMultiMap(name).clear();
	}

	public void m_insert(String name, String key, Object value) {
		getHazelcast().getMultiMap(name).put(key, value);
	}

	public void m_update(String name, String key, Object value) {
		getHazelcast().getMultiMap(name).put(key, value);
	}

	public void m_delete(String name, String key) {
		getHazelcast().getMultiMap(name).remove(key);
	}

	public boolean m_contain(String name, String key) {
		return getHazelcast().getMultiMap(name).containsKey(key);
	}

	public Collection<Object> m_locate(String name, String key) {
		return getHazelcast().getMultiMap(name).get(key);
	}

	public Collection<Object> m_pager(String name) {
		return getHazelcast().getMultiMap(name).values();
	}

	// -----------------------------------------------------------------
}
