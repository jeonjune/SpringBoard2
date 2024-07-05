package com.itwillbs.mail;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailService {
	
	private static final Logger logger = LoggerFactory.getLogger(MailService.class);
	
	@Inject
	private JavaMailSender mailSender;
	
	@Async
	public void sendMail(String to, String subject, String body) throws Exception {
		logger.debug(" sendMail() - 메일 전송 시작 ");
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper msgHelper = new MimeMessageHelper(message,true,"UTF-8" );
		
		msgHelper.setSubject(subject); //제목
		msgHelper.setTo(to); // 받는사람
//		msgHelper.setCc(cc); // 참조
//		msgHelper.setFrom(from); // 보내는 사람 (보여지는 이름 설정/생략시 이메일 주소 노출)
//		msgHelper.setText(body); // 보내는 내용
		msgHelper.setText(body,true); // 보내는 내용 (html 내용 포함)
		
		// 메일 전송
		mailSender.send(message);
		
		logger.debug("sendMail() - 메일 전송 끝");
	}
}
