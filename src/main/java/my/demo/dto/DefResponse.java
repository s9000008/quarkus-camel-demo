package my.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class DefResponse<T> {
	@JsonProperty("header")
	private MwHeader header;
	@JsonProperty("payload")
	private T payload;
}
