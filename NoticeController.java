package com.jpaproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.jpaproject.entity.NoticeDto;
import com.jpaproject.service.NoticeService;

@RestController
@RequestMapping("/notice")
public class NoticeController {
	
	// 서비스 설정
	private final NoticeService noticeService;
	
	// 컨트롤러 서비스 실행설정
	public NoticeController(NoticeService noticeService) {this.noticeService = noticeService;}
	
	// 게시판 등록화면 출력
	@GetMapping("/AdminWrite")
	public ModelAndView AdminWrite() {
			
		ModelAndView model = new ModelAndView();
		model.setViewName("notice/adminNoticeWrite");
		return model;
	}
		
	// 게시판 등록,수정,삭제 서비스
	@PostMapping
	public String notice(NoticeDto dto1) {
			
		String 	  msg = "ok";
		NoticeDto dto2 = noticeService.notice(dto1);
		if(dto2 == null) msg = "fail";  
		return 	  msg;
	}	
}