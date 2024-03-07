package com.vmanage.login;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

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
	@Override
	public VendorEntity verifyOtp(Integer otp, String email) {

		VendorEntity byEmail = this.repository.findByEmail(email);

		System.out.println("EMAIL: " + email);
		System.out.println("OTP: " + otp);

		if (byEmail != null && !"".equals(byEmail.getVendorEmail())
				&& byEmail.getVendorEmail().equalsIgnoreCase(email)) {
			if (byEmail.getOtp() != null && byEmail.getOtp().equals(otp)
					&& Duration.between(byEmail.getOtpGenratedTime(), LocalDateTime.now()).getSeconds() < (1 * 60)) {
				System.out.println("otp matched.");
			} else {
				System.out.println("otp not matched and otp expired.");
			}

		} else if (!byEmail.getOtp().equals(otp)) {
			
				updateFailedAttemptCOunt(byEmail.getFailedAttempt(), email);
				if (byEmail.getFailedAttempt()>=3) {
					lockAccount(email);
					System.out.println("account locked.");
				}
				
				//System.out.println("failed count updated.");
			

		}

		return byEmail;
	}

	@Override
	public void updateFailedAttemptCOunt(int failedAttempt, String email) {
		this.repository.updateFailedAttemptCount(failedAttempt + 1, email);
		System.out.println("failed count updated.");
	}

	@Override
	public void lockAccount(String email) {
		
		VendorEntity byEmail = this.repository.findByEmail(email);
		byEmail.setAccountNonLocked(false);
		byEmail.setAccountLockTime(new Date());
		
		//this.repository.save(byEmail);		
	}

	@Override
	public void resetAttemptCount(String email) {
		this.repository.updateFailedAttemptCount(0, email);
		System.out.println("Failed attempt count reseted.");
		
	}
	
	private static final long lockDurationTime = 2 * 60 * 1000; // 300000
	public static final long ATTEMPT_TIME =3;

	@Override
	public boolean unlockAccountTimeExpired(String email) {
		
		VendorEntity byEmail = this.repository.findByEmail(email);
		
		long accountLockTime = byEmail.getAccountLockTime().getTime();
		long currentTime = System.currentTimeMillis();
		
		if (accountLockTime+lockDurationTime>currentTime) {
			byEmail.setAccountNonLocked(true);
			byEmail.setAccountLockTime(null);
			byEmail.setFailedAttempt(0);
		}
		
		return false;
	}

	/* INCREASE FAILED ATTEMPT COUNT */
	

	/* RESET ATTEMPT */
	

	/* LOCK THE USER IF CROSS MAXIMUN FAILED ATTEMPT */
	

	
	/* UNLOCK ACCOUNT TIME EXPERIED */
	

}
