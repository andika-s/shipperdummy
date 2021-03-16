package com.shipperdummy.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shipperdummy.model.ShipperModel;
import com.shipperdummy.repository.ShipperRepository;

@Service
@Transactional
public class ShipperService {
	
	@Autowired
	private ShipperRepository shipperRepository;

	public ShipperModel findByShipperEmail(String shipperEmail) {
		return shipperRepository.findByShipperEmail(shipperEmail);
	}
	
	public void save(ShipperModel shipperModel) {
		shipperRepository.save(shipperModel);
	}
}
