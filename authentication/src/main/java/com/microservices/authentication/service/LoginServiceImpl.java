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

	private static final long OTP_EXPIRY_TIME = 5 * 60 * 1000; // 5 minutes

	@Override
	@Transactional
	public String registerUser(UserDTO user) {
		if (userDao.existsByDetails(user.getEmail(), user.getContactNo())) {
			return "Email or contact number already registered.";
		}
		System.out.println(user.getUserType());
		MyUser newUser;
		switch (user.getUserType()) {
		case "admin":
			if (!"9".equals(user.getAdminPassword())) {
				return "Invalid admin password.";
			}
			MyUser admin = new MyUser();
			admin.setUsername(user.getName());
			admin.setEmail(user.getEmail());
			admin.setContactNo(user.getContactNo());
			admin.setUserType(user.getUserType());
			admin.setPassword(user.getPassword());
			admin.setStatus(MyUser.UserStatus.ACTIVE); // Set default status
			newUser = admin;
			break;

		case "customer":
			Customer customer = new Customer();
			customer.setUsername(user.getName());
			customer.setEmail(user.getEmail());
			customer.setPassword(user.getPassword());
			customer.setAge(user.getAge());
			customer.setContactNo(user.getContactNo());
			customer.setCity(user.getCity());
			customer.setUserType(user.getUserType());
			customer.setStatus(MyUser.UserStatus.ACTIVE);
			newUser = customer;
			break;

		case "retailer":
			Retailer retailer = new Retailer();
			retailer.setUsername(user.getName());
			retailer.setEmail(user.getEmail());
			retailer.setContactNo(user.getContactNo());
			retailer.setCity(user.getCity());
			retailer.setGSTIN(user.getGstNumber());
			retailer.setPannumber(user.getPanNumber());
			retailer.setPassword(user.getPassword());
			retailer.setUserType(user.getUserType());
			retailer.setStatus(MyUser.UserStatus.UNDER_REVIEW); // Set default status
			newUser = retailer;
			break;

		default:
			return "Invalid user type.";
		}
		System.out.println("before flush");
		userDao.saveAndFlush(newUser);

		return "User successfully registered.";
	}

	@Override
	public String sendOtp(String email, String password) {
		// Authenticate the user
		try {
			MyUser user = userDao.findByEmailAndPassword(email, password);
			String otp = generateOtp();
			otpStore.put(email, otp);
			otpExpiry.put(email, System.currentTimeMillis() + OTP_EXPIRY_TIME);

			// Send OTP via email
			try {
				sendOtpEmail(email, otp);
				return "OTP.... sent successfully for ur Registered mail : " + otp;
			} catch (Exception e) {
				e.printStackTrace();
				return "Your email is not registered in our dataBase";
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "something Gone Wrong";
	}

	private String generateOtp() {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000); // 6-digit OTP
		System.out.println("otp genereted : " + otp);
		return String.valueOf(otp);
	}

	private void sendOtpEmail(String to, String otp) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject("Your OTP Code  for Login");
		message.setText("Your OTP code is : " + otp);
		mailSender.send(message);
	}

	private void sendOtpToEmail(String email, String otp) {
		// Create a SimpleMailMessage object
		SimpleMailMessage message = new SimpleMailMessage();

		// Set the recipient's email address
		message.setTo(email);

		// Set the subject of the email
		String subject = "Your OTP for Password Recovery";
		message.setSubject(subject);

		// Set the body of the email
		String text = "Your OTP is: " + otp + ". This OTP is valid for 10 minutes.";
		message.setText(text);

		// Send the email
		mailSender.send(message);
	}

	@Override
	public String verifyEmail(String email) {
		if (userDao.existsByDetails(email)) {
			return "email is registered.. 'TRY WITH ANOTHER'";
		} else {
			String Otp = generateOtp();
			sendOtpEmail(email, Otp);
			// otpStore.put(email, Otp);
			// otpExpiry.put(email, System.currentTimeMillis() + OTP_EXPIRY_TIME);

			// Send OTP via email
			try {
				sendOtpEmail(email, Otp);
				return "OTP.... sent successfully for ur  mail : " + Otp;
			} catch (Exception e) {
				e.printStackTrace();
				return "Your email is not registered in our dataBase";
			}
		}

	}

	@Override
	public String updatePassword(String email, String password) {

		if (userDao.updatePassword(password, email) > 0) {
			return "password updated successfully";
		}
		return "Issue with passwordUpdate";
	}

	@Override
	public String existsByEmail(String email) {
		boolean emailExists = userDao.existsBymail(email);

		if (emailExists) {
			String otp = generateOtp();
			sendOtpToEmail(email, otp);
			return "OTP sent : " + otp;
		}
		return "Email not found";
	}

}