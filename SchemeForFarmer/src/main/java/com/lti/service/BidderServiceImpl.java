package com.lti.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.BidRequest;
import com.lti.entity.BidderPersonalDetails;
import com.lti.entity.CropDetails;
import com.lti.repository.BidderRepository;

@Service
public class BidderServiceImpl implements BidderService {

	@Autowired
	BidderRepository bidderRepository;

	public long addOrUpdateBidder(BidderPersonalDetails bpd) {
		return bidderRepository.addOrUpdateBidder(bpd);
	}

	public boolean loginBidder(long bidderId, String bidderPassword) {
		return bidderRepository.loginBidder(bidderId, bidderPassword);
	}

	public List<CropDetails> viewCropDetails() {
		return bidderRepository.viewCropDetails();
	}

	public long applyForBid(long sellRequest_id, long bidder_id, int bidAmount) {
		return bidderRepository.applyForBid(sellRequest_id, bidder_id, bidAmount);
	}

	public List<BidRequest> viewBidHistory(long bidderId) {
		return bidderRepository.viewBidHistory(bidderId);
	}

	public BidderPersonalDetails findBidderById(long bidderId) {
		return bidderRepository.findBidderById(bidderId);
	}
}