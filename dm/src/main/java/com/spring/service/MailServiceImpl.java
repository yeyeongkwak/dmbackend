package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.spring.dto.MailDTO;
import com.spring.entity.User;
import com.spring.repository.UserRepository;


@Service
public class MailServiceImpl implements MailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	UserRepository userRepository;
	

	BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
	
	
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



	@Override
	public MailDTO createMailAndChangePassword(String email, String id) {
		String randomPw = getTempPassword();
		System.out.println(randomPw);
        MailDTO mailDTO = new MailDTO();
        mailDTO.setAddress(email);
        mailDTO.setTitle(id+"님의 DM서비스 임시비밀번호 안내 이메일 입니다.");
        mailDTO.setMessage("안녕하세요. DM서비스 임시비밀번호 안내 관련 이메일 입니다." + "[" + id + "]" +"님의 임시 비밀번호는" + randomPw + "입니다.");
        updatePassword(randomPw,email);	
        return mailDTO;
	}

	  public void updatePassword(String randomPw,String email){
		  String password = pwEncoder.encode(randomPw);
	        String id = userRepository.findByEmail(email).getId();
	        System.out.println(id);
	        System.out.println(password);
	        userRepository.updatePassword(password, id);
	        
	    }

	public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
        String randomPw = "";
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            randomPw += charSet[idx];
        }
        return randomPw;
    }

	@Override
	public void mailSend(MailDTO mailDTO) {
	     System.out.println("메일 전송 완료!");
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(mailDTO.getAddress());
	        message.setFrom("docsmanager.cs@gmail.com");
	        message.setSubject(mailDTO.getTitle());
	        message.setText(mailDTO.getMessage());
	        mailSender.send(message);
	    }
	
	public boolean mailCheck(String email) {
		User user = userRepository.findByEmail(email);
		if(email.isEmpty()) {
			throw new NullPointerException("이메일 검색창 비어있음");
		}
		else if(user == null) {
			return true;
		}
		else
			return false;
	}
}
