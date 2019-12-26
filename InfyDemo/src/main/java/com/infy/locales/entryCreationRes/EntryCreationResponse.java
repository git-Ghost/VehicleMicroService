package com.infy.locales.entryCreationRes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "code", "msg", "data" })
public class EntryCreationResponse {

	@JsonProperty("code")
	private Integer code;
	@JsonProperty("msg")
	private String msg;
	@JsonProperty("data")
	private Data data;

	@JsonProperty("code")
	public Integer getCode() {
		return code;
	}

	@JsonProperty("code")
	public void setCode(Integer code) {
		this.code = code;
	}

	@JsonProperty("msg")
	public String getMsg() {
		return msg;
	}

	@JsonProperty("msg")
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@JsonProperty("data")
	public Data getData() {
		return data;
	}

	@JsonProperty("data")
	public void setData(Long id) {
		Data data = new Data();
		data.setId(String.valueOf(id));
		this.data = data;
	}
}