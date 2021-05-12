package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.BidderPersonalDetails;
import com.lti.entity.CropDetails;
import com.lti.entity.FarmerPersonalDetails;
import com.lti.entity.SellRequest;
import com.lti.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepository;
	
	public long addAAdmin() {
		
		return adminRepository.addAAdmin();
	}

	public List<FarmerPersonalDetails> viewAllFarmerRegistrationRequest() {
	List<FarmerPersonalDetails> u = adminRepository.viewAllFarmerRegistrationRequest();
	return u;
	}
	
	public boolean approveAFarmer(long farmerId){
	return adminRepository.approveAFarmer(farmerId);
	}


	public List<BidderPersonalDetails> viewAllBidderRegistrationRequests() {
		return adminRepository.viewAllBidderRegistrationRequests();
	}

	public boolean approveABidder(long bidderId) {
		return adminRepository.approveABidder(bidderId);
	}

	public long addACropType(CropDetails cropDetails) {
		return adminRepository.addACropType(cropDetails);
	}

	public List<SellRequest> viewAllUnapprovedSellRequests() {
		return adminRepository.viewAllUnapprovedSellRequests();
	}

	public boolean approveSellRequest(long sellRequestId) {
		return adminRepository.approveSellRequest(sellRequestId);
	}

	public boolean approveFinalBidRequest(long bidRequestId) {
		return adminRepository.approveFinalBidRequest(bidRequestId);
	}

	public boolean loginAdmin(long adminId, String adminPassword) {
		
		return adminRepository.loginAdmin(adminId, adminPassword);
	}

	
}