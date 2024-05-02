package my.demo.route.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.model.Constants;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import my.demo.constant.Constant;
import my.demo.controller.service.SchoolService;
import my.demo.domain.School;
import my.demo.dto.SchoolDto;

@ApplicationScoped
@Named("InitProcessor")
public class InitProcessor implements Processor{

	@Inject
	SchoolService schoolService;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		Message msg = exchange.getIn();
		String method = msg.getHeader(Constant.HEADER_METHOD,String.class);
		if("add".equals(method)) {
			exchange.setProperty(Constant.HEADER_METHOD, "create");
		}else if("delete".equals(method)) {
			exchange.setProperty(Constant.HEADER_METHOD, "delete");
		}else if("query".equals(method)) {
			exchange.setProperty(Constant.HEADER_METHOD, "query");
		}else{
			exchange.setProperty(Constant.HEADER_METHOD, "query");
		}
		
		msg.setHeader(Constant.RETURNCODE, "0000");
	}

}
