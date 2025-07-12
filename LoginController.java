package com.jpaproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jpaproject.entity.EmpDto;
import com.jpaproject.entity.LoginService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("login")
public class LoginController {

	//서비스설정
	private final LoginService loginService;
	
	// 컨트롤러 서비스 실행설정
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	// 로그인 처리
	@PostMapping
	@ResponseBody
	public String login(@RequestParam String userid,@RequestParam String pass,HttpSession session) {
		
		String msg = "";
		
		// 사용자조회
		EmpDto user = loginService.login(userid,pass);
		
		// 로그인 성공 -> 세션 저장
		if(user != null) {
			
			// 회원출력
			session.setAttribute("user",user);
			
			// 환영문구
			session.setAttribute("welcome",user.getName() + " " + user.getPosition() + "님 환영합니다!");
			
			return "ok";
		}
		
		// 로그인 실패할 시
		else return "fail";
	}
	
	// 로그아웃 처리
	@GetMapping("logout")
	public void logout(HttpSession session) {session.invalidate();}
}