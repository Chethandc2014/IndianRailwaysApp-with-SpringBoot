package com.springboot.irc.controller;

import java.net.Authenticator.RequestorType;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myApp")
public class Application {
	
	@RequestMapping(value="/getEmployeeList",method=RequestMethod.GET)
	public List<String> getEmployeeList(){
		ArrayList<String> empList=new ArrayList();
		empList.add("Chethan");
		empList.add("Ravi");
		return empList;
	}

}
