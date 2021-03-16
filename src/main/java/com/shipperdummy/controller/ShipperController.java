package com.shipperdummy.controller;

import java.lang.module.ResolutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shipperdummy.model.ShipperModel;
import com.shipperdummy.service.ShipperFeignService;
import com.shipperdummy.service.ShipperService;

@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequestMapping(value = "/shipper")
public class ShipperController {

	private static final Logger logger = LoggerFactory.getLogger(ShipperController.class);
	
	@Autowired
	private ShipperService shipperService;
	
	@Autowired 
	private ShipperFeignService shipperFeignService;

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}

	@PostMapping("/signup")
	public void signUpShipper(@RequestParam String shipperFullName, @RequestParam String shipperEmail,
			@RequestParam String shipperPassword) {
		
		ShipperModel model = shipperService.findByShipperEmail(shipperEmail);
		if (model != null) {
			outputExceptionMessage("Email Already Exist!");
		} else {
			ShipperModel shipperModel = new ShipperModel();
			shipperModel.setShipperFullName(shipperFullName);
			shipperModel.setShipperEmail(shipperEmail);
			shipperModel.setShipperPassword(encrypt(shipperPassword));
			shipperService.save(shipperModel);
			shipperFeignService.createShipper(shipperModel.getShipperEmail(), shipperModel.getShipperPassword());
			
			inputDebugLog("CREATED SHIPPER ACCOUNT");
		}
	}
	
	private void outputExceptionMessage(String message) {
		throw new ResolutionException(message);
	}
	
	private String encrypt(String password) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.encode(password);
	} 
	
	private void inputDebugLog(String message) {
		logger.debug("[SHIPPER] " + message);
	}
}
