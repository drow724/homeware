package com.home.bar.common.response;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class BaseResponse {

	private HttpStatus	status;
	
	private Object		data;
}
