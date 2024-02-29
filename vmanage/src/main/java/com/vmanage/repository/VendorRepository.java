package com.vmanage.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.vmanage.entities.VendorEntity;

public interface VendorRepository {

	void save(VendorEntity entity);

	VendorEntity isExistByGstOrNumberOrMailOrSite(String gst, Long number, String email, String website);

	List<VendorEntity> findAll();

	VendorEntity findByEmail(String email);

	void updateOtpByEmail(String otp, String email);

	List<String> findOtp(String otp);

	void updatedOtpGeneratedTime(LocalDateTime otpGeneratedTime, String email);
}
