package com.cityhelpz.cityhelpzapi.utils;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cityhelpz.cityhelpzapi.model.Response;

@Component
public class Utils {
	
	public Response successResponseStatus(List<?> objList, String objName, String message) {
		Response response = new Response(objName);
		if(objList.size() > 0) {
			response.setStatusCodeValue(200);
			response.setSuccess(true);
			response.setData(objList);
			response.setMessage(message);
			response.setRecordsSize(objList.size());
			return response;
		}
		return new Response();
	}
	
	public Response failureResponseStatus(String objName, String message) {
		Response response = new Response(objName);
		response.setStatusCodeValue(200);
		response.setSuccess(true);
		response.setMessage(message);
		return response;
	}


}
