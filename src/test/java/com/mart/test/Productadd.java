package com.mart.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSmartTagPr;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mart.api.Endpoints;
import com.mart.entity.UserDetails;
import com.mart.utility.Apiutils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Productadd extends Apiutils {

	private static Logger logger = LogManager.getLogger(Productadd.class);

	private String employeeCode = "INFO-169";

	private String otp = "";

	private String authToken = "";

	@BeforeClass
	public void setup() throws Exception {

		Properties properties = new Properties();
		FileInputStream inputStream = new FileInputStream("config.properties");
		try {
			properties.load(inputStream);
		} catch (IOException e) {

			e.printStackTrace();
		}

		String baseURI = properties.getProperty("baseURI");
		if (baseURI != null && !baseURI.isEmpty()) {
			RestAssured.baseURI = baseURI;
			logger.info("Base URI set to: " + baseURI);
		} else {

			logger.error("Exception occured", new Exception("Base URI is missing in the config.properties file."));
		}

		inputStream.close();
	}

	@Test
	public void requestOTP() throws Exception {

		String basePath = Endpoints.getOTP;

		try {

			Map<String, Object> user = new HashMap<>();
			user.put("employeeCode", employeeCode);

			Response response = getOTPByEmployeCode(basePath, user);

			if (response.getStatusCode() == 200) {

				logger.info(response.getBody().asPrettyString());
				logger.info("OTP sent successfully");
			}

		} catch (AssertionError e) {

			logger.error("Assertion failed loginUser : " + e.getMessage());
			throw e;

		} catch (Exception e) {

			logger.error("Test failed loginUser : " + e.getMessage());
			throw e;
		}

	}

	@Test
	public void getOtpFromDatabase() {

		Connection connection = null;

		try {

			connection = DriverManager.getConnection("jdbc:postgresql://15.207.182.112:5432/martdb", "postgres",
					"glpdbf9a7e2w4");

			if (connection != null) {
				System.out.println("Connection success");
			} else {
				System.out.println("Connection failed");
			}

			String fetchOtpQuery = "SELECT column_name FROM information_schema.columns WHERE table_name = 'user_details';";
			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(fetchOtpQuery);

			System.out.println("Columns in 'user_details' table from martdb is :");
			while (resultSet.next()) {
				String columnName = resultSet.getString("column_name");
				System.out.println(columnName);
			}

			// Query to fetch OTP by employeeCode
			String fetchOtpFromDb = "SELECT phone_otp FROM public.user_details WHERE employee_code = '" + employeeCode
					+ "' ORDER BY updated_date_time DESC LIMIT 1";
			ResultSet otpResultSet = statement.executeQuery(fetchOtpFromDb);

			if (otpResultSet.next()) {
				otp = otpResultSet.getString("phone_otp");
				System.out.println("Retrieved OTP for employee code " + employeeCode + ": " + otp);
			} else {
				System.out.println("No OTP found for employee code: " + employeeCode);
			}

			connection.close();

		} catch (SQLException e) {

			System.err.println("SQL Error Details:");
			System.err.println("Message: " + e.getMessage());
			System.err.println("SQL State: " + e.getSQLState());
			System.err.println("Error Code: " + e.getErrorCode());
			e.printStackTrace();

		} catch (Exception e) {

			System.err.println("An unexpected error occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	public void loginAdminUsingOtp() throws Exception {

		String basePath = Endpoints.loginUser;

		try {

			Map<String, Object> adminDetails = new HashMap<String, Object>();
			adminDetails.put("phone", "8888888888");
			adminDetails.put("password", "1234");
			logger.info("Attempting to login with: " + adminDetails);

			Response response = loginRequest(basePath, adminDetails);
			
			  authToken = response.getHeader("Authorization");
			  logger.info("The admin token is : " + authToken);
			  
			  if (authToken == null || authToken.isEmpty()) { throw new
			  Exception("Authorization token is missing or empty for user " +adminDetails);  
			}  
			 
			if (response.getStatusCode() == 200) {

				System.out.println(response.getBody().asPrettyString());

				logger.info("loginAdminUsingOtp Test passed : All assertions are successful.");
				logger.info("Admin logged in successfully");
				logger.info("The response body for Login is : " + response.prettyPrint());

			} else {

				System.out.println(response.getBody().asPrettyString());
			}

		} catch (AssertionError e) {

			logger.error("Assertion failed loginAdminUsingOtp : " + e.getMessage());
			throw e;

		} catch (Exception e) {

			logger.error("Test failed loginAdminUsingOtp : " + e.getMessage());
			throw e;
		}
	}

	@Test
	public void addProductByAdmin() {

		String basePath = Endpoints.addProduct;

		try {

			Map<String, Object> addingProducts = new HashMap<>();

			addingProducts.put("productName", "French Fries");
			addingProducts.put("productDescription", "French Fries is a chinese food.");
			addingProducts.put("productPrice", 170);
			addingProducts.put("productActive", true);
			addingProducts.put("location.locationId", 1);
			addingProducts.put("productUpdatedBy", 12);

			File file = new File(getClass().getClassLoader().getResource("images/french-fries.jpg").getFile());

			Response response = addProduct(basePath, authToken, file, addingProducts);
			if (response.getStatusCode() == 200) {

				System.out.println(response.getBody().asPrettyString());

			} else {

				System.out.println(response.getBody().asPrettyString());
			}

		} catch (AssertionError e) {

			logger.error("Assertion failed addProductByAdmin : " + e.getMessage());
			throw e;

		} catch (Exception e) {

			logger.error("Test failed addProductByAdmin : " + e.getMessage());
			throw e;
		}

	}

}
