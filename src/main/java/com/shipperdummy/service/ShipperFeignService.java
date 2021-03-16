package com.shipperdummy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.shipperdummy.feign.LoginService;
import com.shipperdummy.model.ShipperLogin;

@Service
public class ShipperFeignService {

	@Autowired
	private LoginService loginService;

	public ShipperLogin createShipper(@RequestParam String username, @RequestParam String password) {
		return loginService.createShipper(username, password);
	}
}
