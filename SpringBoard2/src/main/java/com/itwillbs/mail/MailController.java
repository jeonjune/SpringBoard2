package com.itwillbs.mail;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@EnableAsync : 컨트롤러에서 호출하는 메서드가 Async 어노테이션이 있을 경우 비동기로 동작하도록 설정


@Controller
@EnableAsync
public class MailController {
	
	private static final Logger logger = LoggerFactory.getLogger(MailController.class);
	
	@Inject
	private MailService mService;
	
	@RequestMapping(value="/send", method=RequestMethod.GET)
	public String sendMailGET(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug(" sendMailGET() 실행 ");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		// 메일 보내기 기능
//		mService.sendMail("jyjeon0831@naver.com", "테스트 메일 ㅎㅇ", "ヾ(•ω•`)o");
		
		// 메일의 내용 생성(html)
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<h1> 안냐세요 아이티윌 전지윤임니다 </h1> ");

		sb.append("<a href='http://www.itwillbs.co.kr'> ");
		sb.append(" 홈페이지로 이동하기 ");		
		sb.append("</a> ");
		
		sb.append("<img src='https://media.tenor.com/RtmcggFXF04AAAAM/cat-kitten.gif'><hr>");
		
		sb.append("<h1> 어쩌라구요 </h1> ");
		sb.append("<h1> 뷁 </h1> ");
		
		sb.append("</body>");
		sb.append("</html>");
		
		mService.sendMail("jyjeon0831@naver.com", "ㅎㅇㅎㅇ", sb.toString());
		
		logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		return "redirect:/mailResult";
	}
}
