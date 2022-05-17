package com.management.app.response;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CommenResponse<T> extends BaseResponse{
	private Map<String, T> result=new HashMap<String,T>();
	public CommenResponse(String key,T dto,String status, String massage) {
		super(status, massage);
		result.put(key, dto);
	}

}
