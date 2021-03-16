package com.shipperdummy.controller;

import java.lang.module.ResolutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ShipperControllerAdvice {

	private static Logger logger = LoggerFactory.getLogger(ShipperController.class);
	
	@ExceptionHandler(BindException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Object Validation Error")
	public void handleBadRequest(BindException e) {
		inputWarnLog("RETURNING HTTP 400 BAD REQUEST", e);
	}
	
	@ExceptionHandler(ResolutionException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Object Validation Error")
	public void handleBadRequest(ResolutionException e) {
		inputWarnLog("RETURNING HTTP 400 BAD REQUEST", e);
	}
	
	private void inputWarnLog(String message, Object e) {
		logger.warn("[SHIPPER] " + message + " " + e);
	}
	
	private void inputErrorLog(String message, Object e) {
		logger.error("[SHIPPER] " + message + " " + e);
	}
}
