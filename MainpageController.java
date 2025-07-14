package com.jpaproject.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.jpaproject.service.InoticeService;
import com.jpaproject.entity.InoticeDto;

@Controller
public class MainpageController {
	
	// 서비스 설정
	private final InoticeService inoticeService;
	
	// 컨트롤러 서비스 실행설정
	public MainpageController(InoticeService inoticeService) {
		this.inoticeService = inoticeService;
	}
		
	
	@GetMapping("/index")
	public ModelAndView index(@RequestParam(defaultValue = "1") int indexpage, 
		 	 				  @RequestParam(defaultValue =  "") String search,
		 	 				  @RequestParam(defaultValue =  "") String deptno) {
		
		// 화면 모델 출력
		ModelAndView model = new ModelAndView();
				
		// 총 데이터베이스(전체공지사항 항목) 개수
		Long total = inoticeService.count();
		
		// 한 페이지당 보여줄 공지사항 데이터(항목) 개수
		int pageData = 10;  
		
		// 1page를 원하면 -> 0번세팅, 검색,분류 포함 페이징 처리
	    Page<InoticeDto> page = inoticeService.list(indexpage -1, pageData, search, deptno);
		
		// 화면 출력 시작번호 = (총 데이터개수 -(현재페이지번호 - 1) * 출력단위)
		int startPageRownum = (int)(page.getTotalElements() - page.getNumber() * pageData);
		
		model.addObject("search", search);
		model.addObject("deptno", deptno);
		model.addObject("indexpage", indexpage);
		model.addObject("plist",page.getContent());
		model.addObject("startPageRownum",startPageRownum);
		model.addObject("ptotal",page.getTotalElements());
		model.setViewName("mainpage/index");
		
		return model;
	}
}
