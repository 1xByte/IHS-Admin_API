package com.adminapi.utils;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtils {

	public String passwordGenerator() {
	
	String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	String pwd = RandomStringUtils.random( 8, characters );
	return pwd;
}}
