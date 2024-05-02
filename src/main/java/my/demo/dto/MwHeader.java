package my.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class MwHeader {
	@JsonProperty("user")
	private String user;
	@JsonProperty("method")
	private String method;
	@JsonProperty("code")
	private String code;
	
}
