package com.coder.dream.demo.webservice.demo;

import javax.jws.WebService;

@WebService
public interface HelloWorld {
	String sayHi(String text);
}
