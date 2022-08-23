package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.spring.dto.MailDTO;


@Service
public class MailServiceImpl implements MailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	@Override
	public void sendMail(MailDTO mailDTO) {
		System.out.println("메일 전송 완료");
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(mailDTO.getAddress());
		message.setSubject(mailDTO.getTitle());
		message.setText(mailDTO.getMessage());
		System.out.println(message);
		mailSender.send(message);
	}

}
