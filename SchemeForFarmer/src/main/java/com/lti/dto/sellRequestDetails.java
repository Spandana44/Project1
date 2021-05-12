package com.lti.dto;

public class sellRequestDetails {
    long farmerId;
    long cropId;
    int quantity;
    
	
	public long getFarmerId() {
		return farmerId;
	}
	public void setFarmerId(long farmerId) {
		this.farmerId = farmerId;
	}
	public void setCropId(long cropId) {
		this.cropId = cropId;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    
	
    
}
