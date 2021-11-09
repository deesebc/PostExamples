package com.home.example.resteasy.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hazelcast.map.IMap;
import com.home.example.resteasy.bean.Info;
import com.home.example.resteasy.hazelcast.HazelcastHelper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Path("/info")
public class InfoServiceImple {

	private String getMachine() {
		return HazelcastHelper.getINSTANCE().getHz().getCluster().getLocalMember().getSocketAddress().getHostName();
	}

	@GET
	@Path("/{property}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Info getProperty(@PathParam("property") final String property) {
		log.info("getProperty - init");
		log.info("property: " + property);
		log.info("hz: " + HazelcastHelper.getINSTANCE().getHz());
		IMap<String, String> map = HazelcastHelper.getINSTANCE().getHz().getMap("infoProperties");
		return new Info(getMachine(), property, map.get(property));
	}

	@POST
	@Path("/{property}/{value}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Info setProperty(@PathParam("property") final String property, @PathParam("value") final String value) {
		log.info("setProperty - init");
		log.info("property: " + property);
		log.info("value: " + value);
		log.info("hz: " + HazelcastHelper.getINSTANCE().getHz());
		IMap<String, String> map = HazelcastHelper.getINSTANCE().getHz().getMap("infoProperties");
		Info info = new Info(getMachine(), property, map.get(property));
		map.put(property, value);
		return info;
	}

}
