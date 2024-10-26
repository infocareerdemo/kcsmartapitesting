//package com.mart.service;
//
//import java.time.LocalDateTime;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//
//import com.mart.dto.LoginDto;
//import com.mart.entity.Mart;
//import com.mart.repository.Martrepository;
//
//public class Martservice {
//
//	@Autowired
//	Martrepository martRepository;
//	
//	
//	public boolean verifyEmployeeCodeAndGenerateOtp(LoginDto loginDto,
//			HttpServletResponse response) {
//		/* FunUtils funUtils = new FunUtils(); */
//		int otp = 123456;
//
//		Mart userDetails =	martRepository.findByEmployeeCode(loginDto.getEmployeeCode());
//		if(userDetails !=null) {
//			   Long phone = userDetails.getPhone();
// 
//			   userDetails.setPhoneOtp(otp);
//			   userDetails.setUpdatedDateTime(LocalDateTime.now());
//			   smsNotificationService.sendOtpToMobile(phone, (long) otp);
//			   martRepository.save(userDetails);
//		}else {
//	           throw new ApplicationException(HttpStatus.NOT_FOUND, 1001, LocalDateTime.now(), "User Not Found");
//		}
//		return true;
//	}	
//
// 
//	public Mart verifyLoginCredential(LoginDto loginDto, HttpServletResponse response) throws Exception{		
//	 Mart userDetail = martRepository.findByEmployeeCode(loginDto.getEmployeeCode());
//	    if(userDetail !=null) {	    	
//	         if(userDetail.getPhoneOtp() == loginDto.getPhoneOTP()) {	        	 
//	         }else {
//		            throw new ApplicationException(HttpStatus.UNAUTHORIZED, 1001, LocalDateTime.now(), "Invalid Credentials");
//	         }	    	
//	    }else {
//	        throw new Exception(HttpStatus.NOT_FOUND, 1001, LocalDateTime.now(), "User Not Found");
//	    }
//		return userDetail;
//	}  
//}
