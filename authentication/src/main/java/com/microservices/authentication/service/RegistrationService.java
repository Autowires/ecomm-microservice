package com.microservices.authentication.service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.microservices.authentication.Repo.CustomerRepository;
import com.microservices.authentication.Repo.UserRepo;
import com.microservices.authentication.dto.CustomerRequest;
import com.microservices.authentication.entity.Customer;
import com.microservices.authentication.entity.MyUser;

import jakarta.transaction.Transactional;

@Service
public class RegistrationService {
	@Autowired
	private UserRepo userDao;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private Map<String, String> otpStore = new ConcurrentHashMap<>();
	private Map<String, Long> otpExpiry = new ConcurrentHashMap<>();
	private Map<String, Boolean> otpVerified = new ConcurrentHashMap<>(); // To store OTP verification status
	private static final long OTP_EXPIRY_TIME = 5 * 60 * 1000; // 5 minutes

	@Transactional
	public String registerCustomer(CustomerRequest customerRequest) {
		if (!otpVerified.getOrDefault(customerRequest.getEmail(), false)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "OTP not verified.");
		}

		if (userDao.existsByDetails(customerRequest.getEmail(), customerRequest.getContactNo())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email or contact number already registered.");
		}

		Customer customer = new Customer();
		customer.setUsername(customerRequest.getName());
		customer.setEmail(customerRequest.getEmail());
		customer.setContactNo(customerRequest.getContactNo());
		customer.setPassword(passwordEncoder.encode(customerRequest.getPassword()));
		customer.setCity(customerRequest.getCity());
		customer.setStatus(MyUser.UserStatus.ACTIVE);
		customer.setUserType("CUSTOMER");
		customer.setAge(customerRequest.getAge());
		customerRepository.save(customer);

		clearOTPs(customerRequest.getEmail());

		return "Customer registered successfully!";
	}

	private void clearOTPs(String email) {
		otpStore.remove(email);
		otpExpiry.remove(email);
		otpVerified.remove(email);
	}

	public String verifyEmail(String email) {
		if (userDao.existsByDetails(email)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is registered. Try with another.");
		} else {
			String otp = generateOtp();
			sendOtpEmail(email, otp);
			otpStore.put(email, otp);
			otpExpiry.put(email, System.currentTimeMillis() + OTP_EXPIRY_TIME);
			return "OTP sent successfully to your email.";
		}
	}

	public String verifyEmail(String email, String otp) {
		String storedOtp = otpStore.get(email);
		Long expiryTime = otpExpiry.get(email);

		if (storedOtp == null || expiryTime == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		if (System.currentTimeMillis() > expiryTime) {
			otpStore.remove(email);
			otpExpiry.remove(email);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "OTP has expired. Please request a new OTP.");
		}

		if (!storedOtp.equals(otp)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		otpVerified.put(email, true);
		return "OTP verified.";
	}

	private String generateOtp() {
		Random random = new Random();
		return String.valueOf(100000 + random.nextInt(900000)); // 6-digit OTP
	}

	private void sendOtpEmail(String to, String otp) {
		emailService.sendEmail(to, "Your OTP Code", "Your OTP Code " + otp);
	}
}
