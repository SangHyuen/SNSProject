package com.codepresso.domain;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.codepresso.repository.*;

@Component
public class ReturnVO {
	
	private Object data;
	private String code;
	private String message;

	public void setCode(String hs) {
		this.code = hs;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setData(Object vo) {
		this.data = vo;
	}
	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	public Object getData() {
		return data;
	}


}
