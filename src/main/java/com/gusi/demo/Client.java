package com.gusi.demo;

import java.util.Queue;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class Client {
	public static void main(String[] args) {
		ClientConfig clientConfig = new ClientConfig();
		ClientNetworkConfig clientConfig_1 = new ClientNetworkConfig();
//		clientConfig_1.addAddress("127.0.0.1:5701");
		clientConfig.addAddress("127.0.0.1:5701");
		HazelcastInstance client = HazelcastClient
				.newHazelcastClient(clientConfig);
		IMap<Integer,String> map = client.getMap("customers");
		System.out.println("Map Size:" + map.size());
		System.out.println("Map first obj:"+map.get(1));
		System.out.println("Map name:"+map.getName());
		
		Queue<String> queue = client.getQueue("customers");
		System.out.println("First customer: " + queue.poll());
		System.out.println("Second customer: " + queue.peek());
		System.out.println("Queue size: " + queue.size());
	}
}
