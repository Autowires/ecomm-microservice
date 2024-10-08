package com.microservices.authentication.service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.microservices.authentication.Repo.CustomerRepository;
import com.microservices.authentication.Repo.RetailerRepository;
import com.microservices.authentication.Repo.UserRepo;
import com.microservices.authentication.dto.RegistrationRequest;
import com.microservices.authentication.dto.RetailerRegister;
import com.microservices.authentication.entity.Customer;
import com.microservices.authentication.entity.MyUser;
import com.microservices.authentication.entity.Retailer;

import jakarta.transaction.Transactional;

@Service
public class RegistrationService {
	@Autowired
	private UserRepo userDao;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private RetailerRepository retailerRepository;

	@Autowired
	private EmailService emailService;

	private Map<String, String> otpStore = new ConcurrentHashMap<>();
	private Map<String, Long> otpExpiry = new ConcurrentHashMap<>();
	private Map<String, Boolean> otpVerified = new ConcurrentHashMap<>(); // To store OTP verification status
	private static final long OTP_EXPIRY_TIME = 5 * 60 * 1000; // 5 minutes

	@Transactional
	public String register(RegistrationRequest request) {
		checkOTPVerfifed(request.getEmail());

		if (userDao.existsByDetails(request.getEmail(), request.getContactNo())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email or contact number already registered.");
		}

		Customer customer = new Customer();
		customer.setUsername(request.getUsername());
		customer.setEmail(request.getEmail());
		customer.setContactNo(request.getContactNo());
		customer.setPassword(request.getPassword());
		System.out.println(request.getCity());
		customer.setCity(request.getCity());
		customer.setStatus(MyUser.UserStatus.ACTIVE);
		customer.setUserType("CUSTOMER");
		customer.setAge(request.getAge());
		customerRepository.save(customer);

		clearOTPs(request.getEmail());

		return "Customer registered successfully!";
	}

	@Transactional
	public String registerRetailer(RetailerRegister request) {

		checkOTPVerfifed(request.getEmail());
		if (userDao.existsByDetails(request.getEmail(), request.getContactNo())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email or contact number already registered.");
		}

		Retailer retailer = new Retailer();
		retailer.setUsername(request.getUsername());
		retailer.setEmail(request.getEmail());
		retailer.setContactNo(request.getContactNo());
		retailer.setPassword(request.getPassword());
		retailer.setCity(request.getCity());
		retailer.setStatus(MyUser.UserStatus.UNDER_REVIEW);
		retailer.setUserType("RETAILER");
		retailer.setAge(request.getAge());
		retailer.setAddress(request.getAddress());
		retailer.setGSTIN(request.getGSTIN());
		retailer.setPannumber(request.getPannumber());
		retailer.setShopName(request.getShopName());

		retailerRepository.save(retailer);

		clearOTPs(request.getEmail());

		return "Retailer registered successfully!";

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
			System.out.println(otp);
			otpExpiry.put(email, System.currentTimeMillis() + OTP_EXPIRY_TIME);
			return "OTP sent successfully to your email.";
		}
	}

	public String verifyEmail(String email, String otp) {
		String storedOtp = otpStore.get(email);
		Long expiryTime = otpExpiry.get(email);

		if (storedOtp == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "incorrect email");
		}

		if (System.currentTimeMillis() > expiryTime) {
			otpStore.remove(email);
			otpExpiry.remove(email);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "OTP has expired. Please request a new OTP.");
		}

		if (!storedOtp.equals(otp)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not matched");
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

	private void checkOTPVerfifed(String email) {
		if (!otpVerified.getOrDefault(email, false)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "OTP not verified.");
		}
	}

}
