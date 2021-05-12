package com.lti.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_insuranceDetails")
public class InsuranceDetails {
	
	@Id
	@SequenceGenerator(name="seq_insuranceDetails",initialValue=8001,allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_insuranceDetails")
	long insuranceId;
	double amountPerHectare;
	String insuranceCompany;
	

	
	@OneToMany(mappedBy="insuranceDetails",cascade=CascadeType.ALL)
	List<InsuranceApplied> insuranceApplied;
	
	

	public List<InsuranceApplied> getInsuranceApplied() {
		return insuranceApplied;
	}

	public void setInsuranceApplied(List<InsuranceApplied> insuranceApplied) {
		this.insuranceApplied = insuranceApplied;
	}

	public long getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(long insuranceId) {
		this.insuranceId = insuranceId;
	}

	public double getAmountPerHectare() {
		return amountPerHectare;
	}

	public void setAmountPerHectare(double amountPerHectare) {
		this.amountPerHectare = amountPerHectare;
	}

	public String getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

}
