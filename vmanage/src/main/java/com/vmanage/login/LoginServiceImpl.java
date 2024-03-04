package com.vmanage.login;

import java.time.Duration;
import java.time.LocalDateTime;
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

		VendorEntity byEmail = this.service.findByEmail(email);

		if (byEmail.getVendorEmail().equalsIgnoreCase(email)) {
			
			Integer otp = this.otpGenerator.generateOtp();
			System.out.println("OTP: " + otp);

			boolean emailSender2 = this.emailSender.emailSender(email, "santosha7022@outlook.com", "One Time Password",
					"Your OTP for login is " + otp + ". Don't share with anyone.");

			this.service.updateOtpGeneratedTime(LocalDateTime.now(), email);

			if (emailSender2) {
				this.service.updateOtpByEmail(otp, email);

				System.out.println("otp sent to mail.");
			} else {
				System.err.println("otp not sent.");
			}
		}
	}

	

	/* VERIFY OTP */
	
	/*
	@Override
	public Integer verifyOtp(Integer otp) {

		List<VendorEntity> list = this.repository.findAll();
		for (VendorEntity vendorEntity : list) {
			System.out.println(vendorEntity.getOtp() + " " + otp);

				if (vendorEntity.getOtp() != null  && vendorEntity.getOtp().equals(otp)) {
					
					System.out.println("otp matched.");
				
			} else {
				System.out.println("otp not matched.");
			}
		}
		return otp;
	}
	*/

	@Override
	public Integer findOtp(Integer otp, String email) {
		
		Integer otpByEmail = this.repository.findOtpByEmail(email);
		
		//VendorEntity vendorEntity = new VendorEntity();
		
		System.out.println("email " + email);
		System.out.println("otp " + otp);
		
		if (email != null) {
			if (otpByEmail != null && !"".equals(otpByEmail) && otpByEmail.equals(otp)  ) {
				System.out.println("otp matches.");
			}
		} else {
			System.out.println("otp not matches.");
		}
		
		return otpByEmail;
	}

}
