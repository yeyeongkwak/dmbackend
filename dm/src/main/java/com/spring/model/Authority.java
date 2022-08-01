package com.spring.model;

public enum Authority {
	READ,WRITE,MASTER;
	
	public String getName() {
		return this.name();
	}
}
