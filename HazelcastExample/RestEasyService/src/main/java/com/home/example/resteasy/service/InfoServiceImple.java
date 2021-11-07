package com.home.example.resteasy.service;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

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

	private String getIp() throws UnknownHostException, SocketException {
		String ip = null;
		try (final DatagramSocket socket = new DatagramSocket()) {
			socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			ip = socket.getLocalAddress().getHostAddress();
		}
		log.info("ip: " + ip);
		log.info("socketAddress: " + HazelcastHelper.getINSTANCE().getHz().getCluster().getLocalMember().getSocketAddress());
		return ip;
	}

	@GET
	@Path("/{property}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Info getProperty(@PathParam("property") final String property) {
		log.info("getProperty - init");
		log.info("property: " + property);
		Info info = null;
		try {
			IMap<String, String> map = HazelcastHelper.getINSTANCE().getHz().getMap("infoProperties");
			info = new Info(getIp(), property, map.get(property));
		} catch (UnknownHostException | SocketException e) {
			log.error(e.getMessage(), e);
		}
		return info;
	}

	@POST
	@Path("/{property}/{value}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Info setProperty(@PathParam("property") final String property, @PathParam("value") final String value) {
		log.info("setProperty - init");
		log.info("property: " + property);
		log.info("value: " + value);
		Info info = null;
		try {
			IMap<String, String> map = HazelcastHelper.getINSTANCE().getHz().getMap("infoProperties");
			info = new Info(getIp(), property, map.get(property));
			map.put(property, value);
		} catch (UnknownHostException | SocketException e) {
			log.error(e.getMessage(), e);
		}
		return info;
	}

}
