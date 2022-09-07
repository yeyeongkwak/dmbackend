package com.spring.service;

import com.spring.dto.MailDTO;

public interface MailService {		
		
	public void sendMail(MailDTO mailDTO);

	public MailDTO createMailAndChangePassword(String email, String id);
	
	 public void mailSend(MailDTO mailDTO);
}
