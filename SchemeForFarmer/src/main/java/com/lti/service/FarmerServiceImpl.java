package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.CropDetails;
import com.lti.entity.FarmerPersonalDetails;
import com.lti.entity.SellRequest;
import com.lti.repository.FarmerRepository;

@Service
public class FarmerServiceImpl implements FarmerService {

	@Autowired
	FarmerRepository farmerRepository;
	public long addAFarmer(FarmerPersonalDetails farmerPersonalDetails) {
	
		return farmerRepository.addAFarmer(farmerPersonalDetails);	 
	}

	public boolean loginFarmer(long farmerId, String farmerPassword) {
		return farmerRepository.loginFarmer(farmerId, farmerPassword);
		
	}

	public long sellCropRequest(long farmerId, long cropId , int quantity) {
		
		return farmerRepository.sellCropRequest(farmerId, cropId, quantity);
	}

	public  List<SellRequest>viewSoldHistory(long farmerId) {
		return farmerRepository.viewSoldHistory(farmerId);

	}
	
	 
	public FarmerPersonalDetails findFarmerById(long farmerId) {
		 return farmerRepository.findFarmerById(farmerId);
	 }

}
