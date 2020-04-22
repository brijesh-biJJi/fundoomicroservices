package com.bridgelabz.response;

public class Response {
	private String message;
	private String token;
	private Object obj;
	private int statusCode;
	
	public Response(String msg,String token) {
		super();
		this.message = msg;
		this.token = token;
	}
	
	public Response(String msg,int statusCode) {
		super();
		this.message = msg;
		this.statusCode = statusCode;
	}
	
	public Response(String token, int statusCode,Object obj) {
		super();
		this.token=token;
		this.obj = obj;
		this.statusCode=statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}
