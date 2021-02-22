package com.swisscom.featuretoggle.util;

import javax.ws.rs.core.Response;

public class ResponseFactory {
	public static final int SERVER_ERROR_CODE = 500;
	public static final int NOT_FOUND_CODE = 404;
	public static final int BAD_REQUEST_CODE = 400;
	public static final int OK_CODE = 200;
	
	public static Response serverError(Object entity) {
		return Response.status(SERVER_ERROR_CODE).entity(entity).build();
	}
	
	public static Response notFound(Object entity) {
		return Response.status(NOT_FOUND_CODE).entity(entity).build();
	}
	
	public static Response badRequest(Object entity) {
		return Response.status(BAD_REQUEST_CODE).entity(entity).build();
	}
	
	public static Response ok(Object entity) {
		return Response.status(OK_CODE).entity(entity).build();
	}


}
