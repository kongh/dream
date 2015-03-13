package com.coder.dream.demo.webservice.demo.support;

import javax.jws.WebService;

import com.coder.dream.demo.webservice.demo.HelloWorld;

@WebService(endpointInterface="com.coder.dream.demo.webservice.demo.HelloWorld")
public class HelloWorldImpl implements HelloWorld{

	@Override
	public String sayHi(String text) {
		System.out.println("sayHi called");
        return "Hello " + text;
	}

}
