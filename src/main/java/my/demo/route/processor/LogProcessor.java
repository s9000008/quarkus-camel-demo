package my.demo.route.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;
import my.demo.constant.Constant;
import my.demo.dto.SchoolDto;

@ApplicationScoped
@Named("LogProcessor")
@Slf4j
public class LogProcessor {

	
	public void input(Exchange exchange) {
		Message message = exchange.getIn();
		String user = message.getHeader(Constant.HEADER_USER, String.class);
		String method = message.getHeader(Constant.HEADER_METHOD, String.class);
		SchoolDto dto = message.getBody(SchoolDto.class);
		String code = dto.getCode();
		log.info("[Request][user: {}, method: {}, code: {}, {}]", user,method,code,dto);
	}
	
	public void output(Exchange exchange) {
		Message message = exchange.getIn();
		String user = message.getHeader(Constant.HEADER_USER, String.class);
		String method = message.getHeader(Constant.HEADER_METHOD, String.class);
		String returnCode = message.getHeader(Constant.RETURNCODE, String.class);
		log.info("[Response][user: {}, method: {}, {}]", user,method,returnCode);
	}
	
	public void error(Exchange exchange) {
		Message message = exchange.getIn();
		String user = message.getHeader(Constant.HEADER_USER, String.class);
		String method = message.getHeader(Constant.HEADER_METHOD, String.class);
		SchoolDto dto = message.getBody(SchoolDto.class);
		String code = dto.getCode();
		message.setHeader(Constant.RETURNCODE, "9999");
		log.info("[ERROR][user: {}, method: {}, code: {}]", user,method,code);
	}
}
