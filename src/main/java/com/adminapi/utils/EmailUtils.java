package com.adminapi.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {
	
	@Autowired
	private JavaMailSender mailSender;

	public boolean emailGenerator(String to, String sub, String body) {
		
		
		
		try {
			
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			
			MimeMessageHelper helper= new MimeMessageHelper(mimeMessage, true);
			
			helper.setSubject(sub);
			helper.setTo(to);
			helper.setText(body, true);
			
			mailSender.send(mimeMessage);

			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return true;
	}
}
