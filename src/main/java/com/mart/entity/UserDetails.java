package com.mart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_details")
public class UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id") 
	private Long userId;
 
    @Column(name = "employee_code", unique = true)
	private String employeeCode;
 
	@Column(name = "phone_otp" , nullable = true)
	private int phoneOtp;
		

	public UserDetails() {
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public int getPhoneOtp() {
		return phoneOtp;
	}

	public void setPhoneOtp(int phoneOtp) {
		this.phoneOtp = phoneOtp;
	}
	
	
	

}
