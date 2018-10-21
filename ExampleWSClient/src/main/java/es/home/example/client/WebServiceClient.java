package es.home.example.client;

import javax.xml.ws.WebServiceRef;

import es.home.ws.example.Saludo;
import es.home.ws.example.impl.WebServiceSample;
import es.home.ws.example.impl.WebServiceSampleImplService;

public class WebServiceClient {
	@WebServiceRef(wsdlLocation = "META-INF/wsdl/webservice-sample.wsdl")
	private static WebServiceSample service;

	public WebServiceClient() {
		service = new WebServiceSampleImplService().getWebServiceSampleImplPort();
	}

	public String despide() {
		return service.despide();
	}

	public Saludo saluda() {
		return service.saluda();
	}
}
