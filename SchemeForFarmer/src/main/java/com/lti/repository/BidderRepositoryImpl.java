package com.lti.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.entity.BidRequest;
import com.lti.entity.BidderPersonalDetails;
import com.lti.entity.CropDetails;
import com.lti.entity.FarmerPersonalDetails;
import com.lti.entity.SellRequest;

@Repository
public class BidderRepositoryImpl implements BidderRepository {
	@PersistenceContext
	EntityManager em;

	@Transactional
	public long addOrUpdateBidder(BidderPersonalDetails bpd) {
		bpd.setApproveStatus("pending");
		BidderPersonalDetails b = em.merge(bpd);
		return b.getBidderId();
	}

	@Transactional
	public boolean loginBidder(long bidderId, String bidderPassword) {
		String status = "approved";
		String jpql = "select b from BidderPersonalDetails b where b.bidderId=:bId and b.bidderPassword=:bpassword and b.approveStatus=:astatus";

		TypedQuery<BidderPersonalDetails> query = em.createQuery(jpql, BidderPersonalDetails.class);
		query.setParameter("bId", bidderId);
		query.setParameter("bpassword", bidderPassword);
		query.setParameter("astatus", status);

		List<BidderPersonalDetails> bidders = query.getResultList();
		if (bidders.size() != 0)
			return true;
		else
			return false;

	}

	@Transactional
	public List<CropDetails> viewCropDetails() {
		String jpql = "select c from CropDetails c";
		Query query = em.createQuery(jpql);
		List<CropDetails> crops = query.getResultList();
		return crops;
	}

	@Transactional
	public long applyForBid(long sellRequest_id, long bidder_id, int bidAmount) {

		String jpql = "select b from BidRequest b where b.bidderPersonalDetails.bidderId=:bid and b.sellRequest.requestId=:sid";
		TypedQuery<BidRequest> query = em.createQuery(jpql, BidRequest.class);
		query.setParameter("bid", bidder_id);
		query.setParameter("sid", sellRequest_id);
		try {
			BidRequest req = query.getSingleResult();
			return 0;
		} catch (Exception e) {

			SellRequest request = em.find(SellRequest.class, sellRequest_id);
			BidderPersonalDetails bidderPersonalDetails = em.find(BidderPersonalDetails.class, bidder_id);
			BidRequest bidRequest = new BidRequest();
			bidRequest.setBidAmount(bidAmount);
			bidRequest.setBidDate(LocalDate.now());
			bidRequest.setStatus("applied");
			bidRequest.setSellRequest(request);
			bidRequest.setBidderPersonalDetails(bidderPersonalDetails);
			BidRequest bidRequest2 = em.merge(bidRequest);
			return bidRequest2.getBidRequestId();

		}
	}

	@Transactional
	public List<BidRequest> viewBidHistory(long bidderId) {
		String jpql = "select b from BidRequest b where b.bidderPersonalDetails.bidderId=:bId";
		Query query = em.createQuery(jpql);
		query.setParameter("bId", bidderId);
		List<BidRequest> requests = query.getResultList();
		return requests;
	}

	@Transactional
	public BidderPersonalDetails findBidderById(long bidderId) {
		return em.find(BidderPersonalDetails.class, bidderId);
	}
}