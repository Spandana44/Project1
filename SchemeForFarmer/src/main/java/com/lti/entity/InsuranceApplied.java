package com.lti.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_insuranceApplied")
public class InsuranceApplied {

	@Id
	@SequenceGenerator(name="seq_insuranceApplied",initialValue=7001,allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_insuranceApplied")
	long policyNo;
	LocalDate validFrom;
	LocalDate validTill;

	double premiumAmount;
	double sumInsured;
	double insuranceArea;
	String insuranceApproveStatus;

	@OneToOne
	@JoinColumn(name="farmer_id")
	FarmerPersonalDetails farmerPersonalDetails;
	
	@OneToOne
	@JoinColumn(name="crop_id")
	CropDetails cropDetails;
	
	
	@ManyToOne
	@JoinColumn(name="insurance_id")
	InsuranceDetails insuranceDetails;
	
	

	public long getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(long policyNo) {
		this.policyNo = policyNo;
	}

	public LocalDate getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(LocalDate validFrom) {
		this.validFrom = validFrom;
	}

	public LocalDate getValidTill() {
		return validTill;
	}

	public void setValidTill(LocalDate validTill) {
		this.validTill = validTill;
	}

	public double getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(double premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public double getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(double sumInsured) {
		this.sumInsured = sumInsured;
	}

	public double getInsuranceArea() {
		return insuranceArea;
	}

	public void setInsuranceArea(double insuranceArea) {
		this.insuranceArea = insuranceArea;
	}

	public String getInsuranceApproveStatus() {
		return insuranceApproveStatus;
	}

	public void setInsuranceApproveStatus(String insuranceApproveStatus) {
		this.insuranceApproveStatus = insuranceApproveStatus;
	}

	public FarmerPersonalDetails getFarmerPersonalDetails() {
		return farmerPersonalDetails;
	}

	public void setFarmerPersonalDetails(FarmerPersonalDetails farmerPersonalDetails) {
		this.farmerPersonalDetails = farmerPersonalDetails;
	}

	public CropDetails getCropDetails() {
		return cropDetails;
	}

	public void setCropDetails(CropDetails cropDetails) {
		this.cropDetails = cropDetails;
	}

	public InsuranceDetails getInsuranceDetails() {
		return insuranceDetails;
	}

	public void setInsuranceDetails(InsuranceDetails insuranceDetails) {
		this.insuranceDetails = insuranceDetails;
	}

}
