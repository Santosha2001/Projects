package com.vmanage.login;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmanage.email.EmailSender;
import com.vmanage.entities.VendorEntity;
import com.vmanage.otp.OtpGenerator;
import com.vmanage.repository.VendorRepository;
import com.vmanage.service.VendorService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private EmailSender emailSender;

	@Autowired
	private OtpGenerator otpGenerator;

	@Autowired
	private VendorService service;

	@Autowired
	private VendorRepository repository;

	/* SEND OTP TO MAIL */
	@Override
	public void sendOtp(String email) {
		
		VendorEntity vendorEntity = new VendorEntity();

		VendorEntity byEmail = this.service.findByEmail(email);
		System.out.println("byEmail: " + byEmail);

		if (byEmail.getVendorEmail().equalsIgnoreCase(email)) {
			String otp = this.otpGenerator.generateOtp();
			System.out.println("OTP: " + otp);

			boolean emailSender2 = this.emailSender.emailSender(email, "santosha7022@outlook.com", "One Time Password",
					"Your OTP for login is " + otp + ". Don't share with anyone.");

			if (emailSender2) {
				this.service.updateOtpByEmail(otp, email);
				vendorEntity.setOtpGenratedTime(LocalDateTime.now());
				System.out.println("otp sent to mail.");
			} else {
				System.err.println("otp not sent.");
			}
		}
	}

	/* VERIFY OTP */
	@Override
	public String verifyOtp(String otp) {

		/*
		 * List<String> otps = this.repository.findOtp(otp);
		 * 
		 * for (String otpCheck : otps) { System.out.println(otpCheck + " " + otp);
		 * 
		 * if (otpCheck != null && !"".equals(otpCheck)) { if
		 * (otpCheck.equalsIgnoreCase(otp)) {
		 * 
		 * System.out.println("OTP IS FOUND."); } } else {
		 * System.out.println("OTP IS NOT FOUND."); } }
		 */

		List<VendorEntity> list = this.repository.findAll();

		for (VendorEntity vendorEntity : list) {
			System.out.println(vendorEntity.getOtp() + " " + otp);
			if (vendorEntity.getOtp() != null && !"".equals(vendorEntity.getOtp())) {
				if (vendorEntity.getOtp().equals(otp)) {
					System.out.println("otp match to database.");
				}
			}
			System.out.println("otp not match to database.");
		}

		return null;
	}

}
