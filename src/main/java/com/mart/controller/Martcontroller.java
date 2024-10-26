//package com.mart.controller;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.mart.dto.LoginDto;
//import com.mart.entity.Mart;
//import com.mart.service.Martservice;
//
//@RestController
//@RequestMapping("/api/v1/user")
//public class Martcontroller {
// 
//	@Autowired
//	Martservice martService;
// 
//@PostMapping("/verifyEmployeeCodeAndGenerateOtp")
//
//	public ResponseEntity<Object> verifyEmployeeCodeAndGenerateOtp(@RequestBody LoginDto loginDto, HttpServletResponse response) throws Exception{		
//
//		return new ResponseEntity<Object>(martService.verifyEmployeeCodeAndGenerateOtp(loginDto, response), HttpStatus.OK);
//
//	}
//
//
// 
//	@PostMapping("/login")
//	public ResponseEntity<Object> login(@RequestBody LoginDto loginDto, HttpServletResponse response) throws Exception{		
//
//		Mart userDetail = martService.verifyLoginCredential(loginDto,response);	
//
//		String token =  jwtUtil.createToken(userDetail.getUserName());
//
//		response.setHeader("Authorization", "Bearer " + token);    		
//
//	    HttpHeaders headers = new HttpHeaders();
//
//        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
//
//        loginDto.setUserId(userDetail.getUserId());
//
//	    loginDto.setEmailId(userDetail.getEmailId());
//
//	    loginDto.setRole(userDetail.getRole());
//
//	    loginDto.setPassword("");
//
//	    loginDto.setLocation(userDetail.getLocation());
//
//	    loginDto.setWalletAmount(userDetail.getWalletAmount());
//
//
//	    return new ResponseEntity<>(loginDto, headers,HttpStatus.OK);
//
//	}
// 
//	
//}
