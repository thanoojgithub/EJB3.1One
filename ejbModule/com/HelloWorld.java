package com;

import java.io.Serializable;

import javax.ejb.Stateless;

@Stateless
public class HelloWorld implements IHelloWorld, Serializable{

	private static final long serialVersionUID = -4320411706714159355L;

	@Override
	public String sayHello(String name) {
		return "Hi, " + name;
	}

}
