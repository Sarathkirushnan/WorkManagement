package com.management.app.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
	private String status;
	private String massage;
	
	public BaseResponse(String status,String massage) {
		this.status=status;
		this.massage=massage;
	}
}
