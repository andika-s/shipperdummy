package com.shipperdummy.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.shipperdummy.idgenerator.StringPrefixedSequenceIdUserGenerator;

@Entity
@Table(name = "Shipper_Model")
public class ShipperModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipperID")
	@GenericGenerator(name = "shipperID", strategy = "com.shipperdummy.idgenerator.StringPrefixedSequenceIdUserGenerator", parameters = {
			@Parameter(name = StringPrefixedSequenceIdUserGenerator.INCREMENT_PARAM, value = "1") })
	@Column(name = "shipperID")
	private String shipperID;

	@Column(name = "shipperFullName")
	private String shipperFullName;

	@Column(name = "shipperEmail")
	private String shipperEmail;

	@Column(name = "shipperPassword")
	private String shipperPassword;

	@CreationTimestamp
	@Column(name = "shipperCreatedOn")
	private LocalDateTime shipperCreatedOn;

	public String getShipperID() {
		return shipperID;
	}

	public String getShipperFullName() {
		return shipperFullName;
	}

	public String getShipperEmail() {
		return shipperEmail;
	}

	public String getShipperPassword() {
		return shipperPassword;
	}

	public LocalDateTime getShipperCreatedOn() {
		return shipperCreatedOn;
	}

	public void setShipperID(String shipperID) {
		this.shipperID = shipperID;
	}

	public void setShipperFullName(String shipperFullName) {
		this.shipperFullName = shipperFullName;
	}

	public void setShipperEmail(String shipperEmail) {
		this.shipperEmail = shipperEmail;
	}

	public void setShipperPassword(String shipperPassword) {
		this.shipperPassword = shipperPassword;
	}

	public void setShipperCreatedOn(LocalDateTime shipperCreatedOn) {
		this.shipperCreatedOn = shipperCreatedOn;
	}

	@Override
	public String toString() {
		return "ShipperModel [shipperID=" + shipperID 
					+ ", shipperFullName=" + shipperFullName 
					+ ", shipperEmail=" + shipperEmail 
					+ ", shipperPassword=" + shipperPassword 
					+ ", shipperCreatedOn=" + shipperCreatedOn
				+ "]";
	}

}
