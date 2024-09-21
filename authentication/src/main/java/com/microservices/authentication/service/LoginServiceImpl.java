package com.microservices.authentication.service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.microservices.authentication.Repo.UserRepo;
import com.microservices.authentication.dto.UserDTO;
import com.microservices.authentication.entity.Customer;
import com.microservices.authentication.entity.MyUser;
import com.microservices.authentication.entity.Retailer;

import jakarta.transaction.Transactional;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private UserRepo userDao;

	private Map<String, String> otpStore = new ConcurrentHashMap<>();
	private Map<String, Long> otpExpiry = new ConcurrentHashMap<>();
	private Map<String, Boolean> otpVerified = new ConcurrentHashMap<>(); // To store OTP verification status
	private static final long OTP_EXPIRY_TIME = 5 * 60 * 1000; // 5 minutes

	/**
	 * Step 1: Verify user credentials and send OTP to the registered email
	 */
	@Override
	public String sendOtp(String email, String password) {
		MyUser user = userDao.findByEmailAndPassword(email, password);

		if (user == null) {
			return "Invalid email or password.";
		}

		String otp = generateOtp();
		otpStore.put(email, otp);
		otpExpiry.put(email, System.currentTimeMillis() + OTP_EXPIRY_TIME);

		sendOtpEmail(email, otp);

		return "OTP sent successfully to your registered email.";
	}

	@Override
	public String verifyOtpForLogin(String email, String otp) {
		String storedOtp = otpStore.get(email);
		Long expiryTime = otpExpiry.get(email);

		if (storedOtp == null || expiryTime == null) {
			return "OTP not found. Please request a new OTP.";
		}

		if (System.currentTimeMillis() > expiryTime) {
			otpStore.remove(email);
			otpExpiry.remove(email);
			return "OTP has expired. Please request a new OTP.";
		}

		if (!storedOtp.equals(otp)) {
			return "Invalid OTP. Please try again.";
		}

		// Mark OTP as verified for login
		otpVerified.put(email, true);
		return "Login successful. OTP verified.";
	}

	@Override
	@Transactional
	public String registerUser(UserDTO user) {
		// Check if the email is verified
		if (!otpVerified.getOrDefault(user.getEmail(), false)) {
			return "OTP not verified. Please verify OTP before registration.";
		}

		// Check if the email or contact number already exists
		if (userDao.existsByDetails(user.getEmail(), user.getContactNo())) {
			return "Email or contact number already registered.";
		}

		MyUser newUser;
		switch (user.getUserType()) {
		case "admin":
			if (!"9".equals(user.getAdminPassword())) {
				return "Invalid admin password.";
			}
			newUser = new MyUser();
			break;

		case "customer":
			newUser = new Customer();
			break;

		case "retailer":
			newUser = new Retailer();
			break;

		default:
			return "Invalid user type.";
		}

		newUser.setUsername(user.getName());
		newUser.setEmail(user.getEmail());
		newUser.setContactNo(user.getContactNo());
		newUser.setPassword(user.getPassword());
		newUser.setUserType(user.getUserType());
		newUser.setStatus(MyUser.UserStatus.ACTIVE); // Set default status
		userDao.saveAndFlush(newUser);

		// Clear OTP data after successful registration
		otpStore.remove(user.getEmail());
		otpExpiry.remove(user.getEmail());
		otpVerified.remove(user.getEmail());

		return "User successfully registered.";
	}

	@Override
	public String verifyEmail(String email) {
		if (userDao.existsByDetails(email)) {
			return "Email is registered. Try with another.";
		} else {
			String otp = generateOtp();
			sendOtpEmail(email, otp);
			otpStore.put(email, otp);
			otpExpiry.put(email, System.currentTimeMillis() + OTP_EXPIRY_TIME);
			return "OTP sent successfully to your email.";
		}
	}

	@Override
	public String verifyOtp(String email, String otp) {
		String storedOtp = otpStore.get(email);
		Long expiryTime = otpExpiry.get(email);

		if (storedOtp == null || expiryTime == null) {
			return "OTP not found. Please request a new OTP.";
		}

		if (System.currentTimeMillis() > expiryTime) {
			otpStore.remove(email);
			otpExpiry.remove(email);
			return "OTP has expired. Please request a new OTP.";
		}

		if (!storedOtp.equals(otp)) {
			return "Invalid OTP.";
		}

		otpVerified.put(email, true); // Mark OTP as verified
		return "OTP verified successfully.";
	}

	@Override
	public String updatePassword(String email, String password) {
		if (userDao.updatePassword(password, email) > 0) {
			return "Password updated successfully.";
		}
		return "Issue with password update.";
	}

	@Override
	public String existsByEmail(String email) {
		if (userDao.existsBymail(email)) {
			String otp = generateOtp();
			sendOtpEmail(email, otp);
			otpStore.put(email, otp);
			otpExpiry.put(email, System.currentTimeMillis() + OTP_EXPIRY_TIME);
			return "OTP sent to your email.";
		}
		return "Email not found.";
	}

	private String generateOtp() {
		Random random = new Random();
		return String.valueOf(100000 + random.nextInt(900000)); // 6-digit OTP
	}

	private void sendOtpEmail(String to, String otp) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject("Your OTP Code");
		message.setText("Your OTP code is: " + otp);
		mailSender.send(message);
	}
}
