package com.example.home.ApacheCamelRestExample.router;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.example.home.ApacheCamelRestExample.pojo.ProposalEntity;

@Component
public class ProposalRouter extends RouteBuilder {
	public static final String SAVE = "direct:route.proposal.repository.save.uri";

	public static final String GET = "jpa://%s?resultClass=%s&namedQuery=%s";

	public static final String POST = "jpa://%s?usePersist=true";

	@Override
	public void configure() throws Exception {
		restConfiguration().component("servlet").bindingMode(RestBindingMode.json_xml);

		rest().get("proposal").produces(MediaType.APPLICATION_JSON_VALUE).route()
				.to(String.format(GET, ProposalEntity.class.getName(), ProposalEntity.class.getName(), "findAllProposta"))
				.log("---select all proposta: ${body}").marshal().json();

		rest().post("proposal").produces(MediaType.APPLICATION_JSON_VALUE).type(ProposalEntity.class).route().routeId("postProposalRoute")
				.log("--- binded ${body} ---").to(String.format(POST, ProposalEntity.class.getName()))
				.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201)).setBody(constant(null));

		from(SAVE).process(exchange -> exchange.getIn()).to("jpa://" + ProposalEntity.class.getName() + "?usePersist=true")
				.log("persistence").end();
	}

}
