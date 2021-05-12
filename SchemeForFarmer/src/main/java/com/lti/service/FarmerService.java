package com.lti.service;

import java.util.List;

import com.lti.entity.CropDetails;
import com.lti.entity.FarmerPersonalDetails;
import com.lti.entity.SellRequest;

public interface FarmerService {

	 long addAFarmer(FarmerPersonalDetails farmerPersonalDetails);
	 boolean loginFarmer(long farmerId, String farmerPassword);
	 long sellCropRequest(long farmerId,  long cropId , int quantity);
	 FarmerPersonalDetails findFarmerById(long farmerId);
	 List<SellRequest> viewSoldHistory(long farmerId);
}
