package com.mcptecnologia.postresponse.common;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;


public class ResponseErrorHandler implements org.springframework.web.client.ResponseErrorHandler{
	private static final Logger log = Logger.getLogger(ResponseErrorHandler.class);

	public void handleError(ClientHttpResponse response) throws IOException {
		log.error("Response error: {} {} " +  response.getStatusCode() + " " + response.getStatusText());
		
	}

	public boolean hasError(ClientHttpResponse response) throws IOException {
		return isError(response.getStatusCode());
	}
	
	public static boolean isError(HttpStatus status) {
        HttpStatus.Series series = status.series();
        return (HttpStatus.Series.CLIENT_ERROR.equals(series)
                || HttpStatus.Series.SERVER_ERROR.equals(series));
    }

	

}
