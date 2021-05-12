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
	
	@GetMapping("/viewAllUnapprovedSellRequests")
	public List<SellRequest> viewAllUnapprovedSellRequests(){
		return adminService.viewAllUnapprovedSellRequests();
	}
	
	@GetMapping("/viewAllBidRequestsForOneSellRequest/{sellRequestId}")
	public List<BidRequest> viewAllBidRequestsForSellRequest(@PathVariable("sellRequestId") long sellRequestId){
		return marketService.viewAllBiddingRequestsForOneSellRequest(sellRequestId);
	}
	
	@GetMapping("/viewAllCrops")
	public List<CropDetails> viewAllCrops(){
		return marketService.viewAllCrops();
	}
	
	@GetMapping("/viewSoldHistory/{farmerId}")
	public List<SellRequest> viewSoldHistory(@PathVariable("farmerId") long farmerId){
		return farmerService.viewSoldHistory(farmerId);
	}
	
	@GetMapping("/viewBidHistory/{bidderId}")
	public List<BidRequest> viewBidRequests(@PathVariable("bidderId") long bidderId){
		return bidderService.viewBidHistory(bidderId);
	}
	
	@GetMapping("/approveAFarmer/{farmerId}")
	public boolean approveAFarmer(@PathVariable("farmerId") long farmerId) {
		return adminService.approveAFarmer(farmerId);
	}
	
	@GetMapping("/approveABidder/{bidderId}")
	public boolean approveABidder(@PathVariable("bidderId") long bidderId) {
		return adminService.approveABidder(bidderId);
	}
	
	@GetMapping("/loginAdmin")
	public boolean loginAdmin(@RequestParam("adminId") long adminId,@RequestParam("adminPassword") String adminPassword) {
		return adminService.loginAdmin(adminId, adminPassword);
	}
	
	@GetMapping("/loginFarmer")
	public boolean loginFarmer(@RequestParam("farmerId") long farmerId,@RequestParam("farmerPassword") String farmerPassword) {
		return farmerService.loginFarmer(farmerId, farmerPassword);
	}
	
	@GetMapping("/loginBidder")
	public boolean loginBidder(@RequestParam("bidderId") long bidderId,@RequestParam("bidderPassword") String bidderPassword) {
		return bidderService.loginBidder(bidderId, bidderPassword);
	}
	
	@RequestMapping(value="/addAFarmer",method = RequestMethod.POST)
	public long addAFarmer(@RequestBody FarmerPersonalDetails farmerPersonalDetails) {
		return farmerService.addAFarmer(farmerPersonalDetails);
	}
	
	@RequestMapping(value="/addABidder",method = RequestMethod.POST)
	public long addABidder(@RequestBody BidderPersonalDetails bidderPersonalDetails) {
		return bidderService.addOrUpdateBidder(bidderPersonalDetails);
	}
	
	@RequestMapping(value="/addACrop",method = RequestMethod.POST)
	public long addACrop(@RequestBody CropDetails cropDetails) {
		return adminService.addACropType(cropDetails);
	}
	
	@RequestMapping(value="/placeSellRequest",method = RequestMethod.POST)
	public long addAbidder(@RequestBody sellRequestDetails sellRequestDetails) {
		return farmerService.sellCropRequest(sellRequestDetails.getFarmerId(), sellRequestDetails.getCropId() , sellRequestDetails.getQuantity());
	}
	
	@RequestMapping(value="/applyBidRequest",method = RequestMethod.POST)
	public long applyForBid(@RequestBody BidRequestDetails bidRequestDetails) {
		return bidderService.applyForBid(bidRequestDetails.getSellRequestId(), bidRequestDetails.getBidderId(), bidRequestDetails.getBidAmount());
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
