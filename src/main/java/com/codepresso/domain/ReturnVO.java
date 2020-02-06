package com.codepresso.domain;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.codepresso.repository.*;

@Component
public class ReturnVO {
	
	private Object data;
	private HttpStatus code;
	private String message;

	public void setCode(HttpStatus hs) {
		this.code = hs;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setData(Object vo) {
		this.data = vo;
	}
	public HttpStatus getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	public Object getData() {
		return data;
	}


}
