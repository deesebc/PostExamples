package es.home.example.encrypt;

import java.security.Provider;
import java.security.Security;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class GetSupportedPaswords {

	public static void main(final String[] args) {
		for (Provider provider : Security.getProviders()) {
			log.info("Provider: " + provider.getName());
			for (Provider.Service service : provider.getServices()) {
				log.info("  Algorithm: " + service.getAlgorithm());
			}
		}

	}
}
