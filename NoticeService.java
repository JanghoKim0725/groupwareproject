package com.jpaproject.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.jpaproject.entity.NoticeDto;
import com.jpaproject.repository.NoticeRepository;

@Service
public class NoticeService {

	// 리퍼지토리 설정
	public final NoticeRepository noticeRepository;
	
	// 컨트롤러 서비스 실행 설정
	public NoticeService(NoticeRepository noticeRepository) {this.noticeRepository = noticeRepository;}
	
	// 공지사항 목록화면 출력
	public Page<NoticeDto> List(int page,int size) {
			
		// pageable : 페이징 처리를 위한 스프링에서 제공하는 인터페이스 = pageRequest.of(현재페이지번호,출력단위페이지번호,정렬방식)
		Pageable pageable = PageRequest.of(page,size,Sort.by("ntcno").descending()); 
		return noticeRepository.findAll(pageable);
	}
	
	// 공지사항 목록화면 총데이터 개수
	public Long count() {return noticeRepository.count();}
	
	// 공지사항 상세보기
	public NoticeDto detail(int ntcno) {return noticeRepository.findById(ntcno).orElse(null);}
	
	// 공지사항 등록,수정,삭제 서비스
	public NoticeDto notice(NoticeDto dto1) {
		
		// 수정하기 상황(바뀐내용 덮어쓰기)
		if(dto1.getNtcno() > 0 && dto1.getNtctt() != null && !dto1.getNtctt().equals("")) {
			
			NoticeDto dto2 = detail(dto1.getNtcno());
			
			// 조회수개수 초기화방지 그대로 유지
			dto1.setNtcht(dto2.getNtcht());
			
			// 등록일 초기화방지 그대로 유지
			dto1.setNtcrd(dto2.getNtcrd());
		}
		
		// 삭제하기 상황
		if(dto1.getNtcno() > 0 && dto1.getNtctt().equals("")) noticeRepository.deleteById(dto1.getNtcno());
		
		// 등록하기 상황
		else 												  noticeRepository.save(dto1);
		
		return dto1;
	}
}