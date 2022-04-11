package com.revature.spring_mvc.web.dtos;

import java.time.LocalDateTime;

public class ThrowableResponse {
	 private int statusCode;
	    private String message;
	    private String cause;
	    private String datetime;

	    public ThrowableResponse() {
	        super();
	        this.datetime = LocalDateTime.now().toString();
	    }

	    public ThrowableResponse(int statusCode, Throwable t) {
	        this();
	        this.statusCode = statusCode;
	        this.message = t.getMessage();
	        this.cause = t.getClass().getSimpleName();
	    }

	    public ThrowableResponse(int statusCode, String message, Throwable t) {
	        this(statusCode, t);
	        this.message = message;
	    }

	    public int getStatusCode() {
	        return statusCode;
	    }

	    public void setStatusCode(int statusCode) {
	        this.statusCode = statusCode;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }

	    public String getCause() {
	        return cause;
	    }

	    public void setCause(String cause) {
	        this.cause = cause;
	    }

	    public String getDatetime() {
	        return datetime;
	    }

	    public void setDatetime(String datetime) {
	        this.datetime = datetime;
	    }

	    @Override
	    public String toString() {
	        return "ThrowableResponse{" +
	                "statusCode=" + statusCode +
	                ", message='" + message + '\'' +
	                ", cause='" + cause + '\'' +
	                ", datetime='" + datetime + '\'' +
	                '}';
	    }
}
