package com.lti.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.BidRequestDetails;
import com.lti.dto.sellRequestDetails;
import com.lti.entity.BidRequest;
import com.lti.entity.BidderPersonalDetails;
import com.lti.entity.CropDetails;
import com.lti.entity.FarmerPersonalDetails;
import com.lti.entity.SellRequest;
import com.lti.service.AdminService;
import com.lti.service.BidderService;
import com.lti.service.FarmerService;
import com.lti.service.MarketService;

@RestController
public class SchemeForFarmerResource {
	@Autowired
	AdminService adminService;
	
	@Autowired
	BidderService bidderService;

	@Autowired
	FarmerService farmerService;
	
	@Autowired
	MarketService marketService;

	@RequestMapping("/viewAllFarmerRequests")
	public List<FarmerPersonalDetails> viewAllFarmerRegistrationRequest() {
		List<FarmerPersonalDetails> u = adminService.viewAllFarmerRegistrationRequest();
		return u;
		}
	
	@RequestMapping("/viewAllBidderRequests")
	public List<BidderPersonalDetails> viewAllBidderRegistrationRequest() {
		List<BidderPersonalDetails> u = adminService.viewAllBidderRegistrationRequests();
		return u;
		}
	
	@GetMapping("/approveASellRequest/{sellRequestId}")
	public boolean approveASellRequest(@PathVariable("sellRequestId") long sellRequestId) {
		return adminService.approveSellRequest(sellRequestId);
	}
	
	@GetMapping("/approveFinalBidRequest/{bidRequestId}")
	public boolean approveFinalBidRequest(@PathVariable("bidRequestId") long bidRequestId) {
		return adminService.approveFinalBidRequest(bidRequestId);
	}
	
	
}
