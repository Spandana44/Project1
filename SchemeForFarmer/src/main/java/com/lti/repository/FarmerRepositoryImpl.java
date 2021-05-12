package com.lti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lti.entity.CropDetails;
import com.lti.entity.FarmerPersonalDetails;
import com.lti.entity.SellRequest;

@Repository
public class FarmerRepositoryImpl implements FarmerRepository {

	@PersistenceContext
	EntityManager em;

	@Transactional
	public long addAFarmer(FarmerPersonalDetails farmerPersonalDetails) {
		farmerPersonalDetails.setApproveStatus("pending");
		FarmerPersonalDetails farmerDetails = em.merge(farmerPersonalDetails);
		return farmerDetails.getFarmerId();
	}

	@Transactional
	public boolean loginFarmer(long farmerId, String farmerPassword) {
		String status = "approved";
		String jpql = "select f from FarmerPersonalDetails f where f.farmerId=:fId and f.farmerPassword=:fpassword and f.approveStatus=:astatus";

		TypedQuery<FarmerPersonalDetails> query = em.createQuery(jpql, FarmerPersonalDetails.class);
		query.setParameter("fId", farmerId);
		query.setParameter("fpassword", farmerPassword);
		query.setParameter("astatus", status);

		try {
			FarmerPersonalDetails farmer = query.getSingleResult();

			return true;

		} catch (Exception e) {

			return false;

		}
	}

	@Transactional
	public long sellCropRequest(long farmer_id, long cropId, int quantity) {
		String status = "Applied";
		String status1 = "approved";
		//String status2 = "rejected";
		SellRequest sellRequest = new SellRequest();
		String jpql = "select s from SellRequest s where s.farmerPersonalDetails.farmerId=:fid and s.cropDetails.cropId=:cid and s.sellRequestApproveStatus in(:astatus,:astatus1)";
		TypedQuery<SellRequest> query = em.createQuery(jpql, SellRequest.class);
		query.setParameter("fid", farmer_id);
		query.setParameter("cid", cropId);
		query.setParameter("astatus", status);
		query.setParameter("astatus1", status1);
		//query.setParameter("astatus2", status2);
		try {
			SellRequest req = query.getSingleResult();
			return 0;

		} catch (Exception e) {
		

			FarmerPersonalDetails farmerPersonalDetails = em.find(FarmerPersonalDetails.class, farmer_id);
			sellRequest.setFarmerPersonalDetails(farmerPersonalDetails);
			CropDetails cropDetails = em.find(CropDetails.class, cropId);
			sellRequest.setCropDetails(cropDetails);
			sellRequest.setQuantity(quantity);
			sellRequest.setSellRequestApproveStatus("Applied");
			SellRequest sellRequest2 = em.merge(sellRequest);
			return sellRequest2.getRequestId();

		}
	}

	@Transactional
	public List<SellRequest> viewSoldHistory(long farmerId) {
		String jpql = "select s from SellRequest s where s. farmerPersonalDetails.farmerId=:fid";
		TypedQuery<SellRequest> query = em.createQuery(jpql, SellRequest.class);
		query.setParameter("fid", farmerId);
		List<SellRequest> requests = query.getResultList();
		return requests;
	}

	@Transactional
	public FarmerPersonalDetails findFarmerById(long farmerId) {
		FarmerPersonalDetails personalDetails = em.find(FarmerPersonalDetails.class, farmerId);
		return personalDetails;
	}

}
