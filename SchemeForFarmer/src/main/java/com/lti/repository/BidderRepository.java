package com.lti.repository;

import java.util.List;

import com.lti.entity.BidRequest;
import com.lti.entity.BidderPersonalDetails;
import com.lti.entity.CropDetails;

public interface BidderRepository {

	long addOrUpdateBidder(BidderPersonalDetails BidderPersonalDetails);

	boolean loginBidder(long bidderId, String bidderPassword);

	List<CropDetails> viewCropDetails();

	long applyForBid(long sellRequest_id, long bidder_id, int bidAmount);

	List<BidRequest> viewBidHistory(long bidderId);

	BidderPersonalDetails findBidderById(long bidderId);
}