package com.shipperdummy.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shipperdummy.model.ShipperLogin;

import feign.Body;
import feign.Headers;

@FeignClient(value = "LOGIN-DUMMY", url = "http://localhost:9080")
public interface LoginService {

	@PostMapping(path = "/create/user")
	@Headers("Authorization= Bearer")
	@Body("%7B \"username\": \"{username}\", \"password\": \"{password}\" %7D")
	public ShipperLogin createShipper(@RequestParam String username, @RequestParam String password);
}
