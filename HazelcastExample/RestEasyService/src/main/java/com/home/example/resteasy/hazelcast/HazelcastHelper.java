package com.home.example.resteasy.hazelcast;

import com.hazelcast.core.HazelcastInstance;

import lombok.Getter;
import lombok.Setter;

public final class HazelcastHelper {

	@Getter
	private static HazelcastHelper INSTANCE = new HazelcastHelper();

	@Setter
	@Getter
	public HazelcastInstance hz;

//	private HazelcastHelper() {
//		hz = Hazelcast.newHazelcastInstance();
//	}

}
