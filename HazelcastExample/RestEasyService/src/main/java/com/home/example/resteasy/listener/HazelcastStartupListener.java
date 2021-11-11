package com.home.example.resteasy.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.home.example.resteasy.hazelcast.HazelcastHelper;

import lombok.extern.log4j.Log4j2;

@WebListener
@Log4j2
public class HazelcastStartupListener implements ServletContextListener {
	@Override
	public void contextDestroyed(final ServletContextEvent sce) {
		log.info("web application stopping");
		Hazelcast.shutdownAll();
	}

	@Override
	public void contextInitialized(final ServletContextEvent sce) {
		String instance = System.getProperty("hazelcast.instance.name");
		String instanceEnv = System.getenv("hazelcast_instance_name");
		log.info("web application starting: " + instance + " env: " + instanceEnv);
		HazelcastInstance hzi = Hazelcast.getHazelcastInstanceByName(instance);
		if (hzi == null) {
			hzi = Hazelcast.getOrCreateHazelcastInstance();
		}
		HazelcastHelper.getINSTANCE().setHz(hzi);
	}
}