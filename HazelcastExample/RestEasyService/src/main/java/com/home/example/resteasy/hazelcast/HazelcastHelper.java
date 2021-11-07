package com.home.example.resteasy.hazelcast;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import lombok.Getter;

public final class HazelcastHelper {

	@Getter
	private static HazelcastHelper INSTANCE = new HazelcastHelper();

	@Getter
	public HazelcastInstance hz;

	private HazelcastHelper() {
		hz = Hazelcast.newHazelcastInstance();
	}

}
