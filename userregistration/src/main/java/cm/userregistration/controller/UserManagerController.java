package cm.userregistration.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cm.userregistration.domain.User;

import com.userregistration.constants.UserRestURIConstants;

@Controller
public class UserManagerController {

	 private static final Logger logger = LoggerFactory.getLogger(UserManagerController.class);
     
	    //Map to store Users, ideally we should use database
	    Map<Integer, User> usersData = new HashMap<Integer, User>();
	     
	    @RequestMapping(value = UserRestURIConstants.INSERT_USER, method = RequestMethod.GET)
	    public @ResponseBody User insertUser() {
	        logger.info("Start insertUser");
	        User user = new User();
	        user.setName("test");
	        user.setMailId("test@mail.com");
	        user.setRegisteredDate(new Date());
	        usersData.put(9999, user);
	        return user;
	    }
	   
	    @RequestMapping(value = UserRestURIConstants.GET_USER, method = RequestMethod.GET)
	    public @ResponseBody User getUser(@PathVariable("id") int empId) {
	        logger.info("Start getUser. ID="+empId);
	        User user = usersData.get(empId);
	        if(user==null){
	        	return null; // ideally we should throw an Exception saying "UserNotFoundException" and handle it appropriately
	        }
	        return user;
	    }
	     
	    @RequestMapping(value = UserRestURIConstants.GET_ALL_USER, method = RequestMethod.GET)
	    public @ResponseBody List<User> getAllUsers() {
	        logger.info("Start getAllUsers.");
	        List<User> users = new ArrayList<User>();
	        Set<Integer> userIdKeys = usersData.keySet();
	        for(Integer i : userIdKeys){
	        	users.add(usersData.get(i));
	        }
	        return users;
	    }
	     
	    @RequestMapping(value = UserRestURIConstants.DELETE_USER, method = RequestMethod.GET)
	    public @ResponseBody User deleteUser(@PathVariable("id") int empId) {
	        logger.info("Start deleteUser.");
	        User user = usersData.get(empId);
	        if(user!=null){
	        usersData.remove(empId);
	        }else{
	        	return null; // ideally we should throw an Exception saying "UserNotFoundException" and handle it appropriately
	        }
	        return user;
	    }
	
}
