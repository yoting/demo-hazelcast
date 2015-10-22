package com.gusi.demo.one;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class MyApplication {

	private final Map loggedOnUsers;

	private final Users usersDB = new Users();

	private final SimpleDateFormat sdf = new SimpleDateFormat("kk:mm:ss-SS");

	private long lastChange;

	public MyApplication() {

		HazelcastInstance instance = Hazelcast.newHazelcastInstance();

		loggedOnUsers = instance.getMap("Users");
	}

	/**
	 * A user logs on to the application
	 *
	 * @param username
	 *            The user name
	 */
	public void logon(String username) {

		User user = usersDB.get(username);

		loggedOnUsers.put(username, user);
		lastChange = System.currentTimeMillis();
	}

	/**
	 * The user logs out (or off depending on your pov).
	 */
	public void logout(String username) {

		loggedOnUsers.remove(username);
		lastChange = System.currentTimeMillis();
	}

	/**
	 * @return Return true if the user is logged on
	 */
	public boolean isLoggedOn(String username) {
		return loggedOnUsers.containsKey(username);
	}

	/**
	 * Return a list of the currently logged on users – perhaps to sys admin.
	 */
	public Collection loggedOnUsers() {
		return loggedOnUsers.values();
	}

	/**
	 * Display the logged on users
	 */
	public void displayUsers() {

		StringBuilder sb = new StringBuilder("Logged on users:\n");
		Collection users = loggedOnUsers.values();
		Iterator it = users.iterator();
		while (it.hasNext()) {
			sb.append(it.next());
			sb.append("\n");
		}
		sb.append(loggedOnUsers.size());
		sb.append(" — ");
		sb.append(sdf.format(new Date(lastChange)));
		sb.append("\n");
		System.out.println(sb.toString());
	}

}