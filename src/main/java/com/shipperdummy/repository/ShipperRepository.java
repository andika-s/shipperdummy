package com.shipperdummy.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shipperdummy.model.ShipperModel;

@Repository
public interface ShipperRepository extends PagingAndSortingRepository<ShipperModel, String>, JpaSpecificationExecutor<ShipperModel> {
	
	ShipperModel findByShipperEmail(String shipperEmail);
}
