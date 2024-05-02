package my.demo.route.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import my.demo.constant.Constant;
import my.demo.controller.service.SchoolService;
import my.demo.domain.School;
import my.demo.dto.SchoolDto;

@ApplicationScoped
@Named("DataQueryProcessor")
public class DataQueryProcessor implements Processor{

	@Inject
	SchoolService schoolService;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		Message msg = exchange.getIn();
		SchoolDto dto = msg.getBody(SchoolDto.class);
		School row = schoolService.get(dto.getCode());
		System.out.println(row);
		msg.setBody(row);
	}

}
