package my.demo.controller;


import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.FluentProducerTemplate;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import my.demo.constant.Constant;
import my.demo.controller.repo.SchoolRepo;
import my.demo.controller.service.SchoolService;
import my.demo.domain.School;
import my.demo.dto.*;
import my.demo.dto.SchoolDto;
@Slf4j
@RequestScoped
@Path("api")
public class Controller {

	@Inject
	CamelContext camelContext;
	
	@Inject
	SchoolService schoolService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/gateway")
	public DefResponse<School> gateway(DefRequest<SchoolDto> request) {
    	final FluentProducerTemplate producer = camelContext.createFluentProducerTemplate();
		DefResponse<School> response = new DefResponse<>();
    	Exchange exchange = producer
    			.withExchangeProperty("method", response)
    	.withHeader(Constant.HEADER_USER, request.getHeader().getUser())
    	.withHeader(Constant.HEADER_METHOD, request.getHeader().getMethod())
    	.withBody(request.getPayload())
    	.to(Constant.ENTRY)
    	.request(Exchange.class);
    	try {
    		producer.stop();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	MwHeader header = request.getHeader();
    	header.setCode(exchange.getIn().getHeader(Constant.RETURNCODE, String.class));
    	response.setHeader(header);
    	if(exchange.getIn().getBody() != null) {
    		response.setPayload(exchange.getIn().getBody(School.class));
    	}
        return response;
    }
	
	@POST
	@Path("/test")
    public Response login() {
        return Response.ok("test").build();
    }

	@GET
	@Path("/get/{code}")
    public School get(@PathParam("code") String code) {
		log.info("Query school by code : ",code);
		School row = schoolService.get(code);
        return row;
    }
	
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
	@Path("/add")
	public Response addSchool(SchoolDto schoolDto) {
		log.info("add school");
		schoolService.add(schoolDto);
        return Response.ok("success").build();
    }
}
