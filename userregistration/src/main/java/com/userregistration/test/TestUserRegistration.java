package com.userregistration.test;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import cm.userregistration.domain.User;

import com.userregistration.constants.UserRestURIConstants;

public class TestUserRegistration {
	public static final String SERVER_URI = "http://localhost:8080/userregistration";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testInsertUser();
		testGetUser();
	}
	
	
	 private static void testGetUser() {
	        RestTemplate restTemplate = new RestTemplate();
	        User user = restTemplate.getForObject(SERVER_URI+"/getuser/9999", User.class);
	        printUserData(user);
	    }
	 
	 public static void printUserData(User user){
	        System.out.println("MAILID="+user.getMailId()+",Name="+user.getName()+",RegisteredDate="+user.getRegisteredDate());
	    }
	 
	 private static void testInsertUser() {
	        RestTemplate restTemplate = new RestTemplate();
	        User user = restTemplate.getForObject(SERVER_URI+UserRestURIConstants.INSERT_USER, User.class);
	        printUserData(user);
	    }
	 
	 private static void testGetAllUsers() {
	        RestTemplate restTemplate = new RestTemplate();
	        //we can't get List<Employee> because JSON convertor doesn't know the type of
	        //object in the list and hence convert it to default JSON object type LinkedHashMap
	        List<LinkedHashMap> emps = restTemplate.getForObject(SERVER_URI+UserRestURIConstants.GET_ALL_USER, List.class);
	        System.out.println(emps.size());
	        for(LinkedHashMap map : emps){
	            System.out.println("ID="+map.get("id")+",Name="+map.get("name")+",CreatedDate="+map.get("createdDate"));;
	        }
	    }

}
