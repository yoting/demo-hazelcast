package com.gusi.demo.cache;

import java.util.Iterator;

import com.gusi.demo.pojo.Person;

/**
 * 持久化对象转换为缓存对象接口
 * 
 * @author GUSI
 *
 */
public interface Persist2Cache {
	public Iterator<Person> iteratorPersion();
}
