package com.jpaproject.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.jpaproject.entity.I_noticeDto;
import com.jpaproject.repository.I_noticeRepository;

@Service
public class I_noticeService {

	// 리퍼지토리 설정
	public final I_noticeRepository i_noticeRepository;
	
	// 전체컨트롤러 서비스 실행 설정
	public I_noticeService(I_noticeRepository i_noticeRepository) {this.i_noticeRepository = i_noticeRepository;}
	
	// 전체공지사항 목록화면 출력 (필수공지사항 -> 일반공지사항 순으로 역순 정렬 + 검색기능에 따른 데이터 개수에 따라 페이지 재 정렬)
	public Page<I_noticeDto> list(int page,int size,String search) {
			
		 Pageable pageable = PageRequest.of(page,size);
		 return i_noticeRepository.findByIntcttContaining(search, pageable);
	}
	
	// 전체공지사항 목록화면 총데이터 개수
	public Long count() {return i_noticeRepository.count();}
	
	// 전체공지사항 상세보기
	public I_noticeDto detail(int intcno) {return i_noticeRepository.findById(intcno).orElse(null);}
	
	// 전체공지사항 조회수증가
	public I_noticeDto saveHits(int intcno) {
		
		I_noticeDto dto = detail(intcno);
		int      hits = dto.getIntcht();
		hits++;
		dto.setIntcht(hits++);
		//  dto :: save시 키값이 포함 되어있는 경우 UPDATE 처리함
		return i_noticeRepository.save(dto);
	}
	
	// 전체공지사항 등록,수정,삭제 서비스
	public I_noticeDto notice(I_noticeDto dto1) {
		
		// 수정하기 상황(바뀐내용 덮어쓰기)
		if(dto1.getIntcno() > 0 && dto1.getIntctt() != null && !dto1.getIntctt().equals("")) {
			
			I_noticeDto dto2 = detail(dto1.getIntcno());
			
			// 조회수개수 초기화방지 그대로 유지
			dto1.setIntcht(dto2.getIntcht());
			
			// 등록일 초기화방지 그대로 유지
			dto1.setIntcrd(dto2.getIntcrd());
		}
		
		// 삭제하기 상황
		if(dto1.getIntcno() > 0 && dto1.getIntctt().equals("")) i_noticeRepository.deleteById(dto1.getIntcno());
		
		// 등록하기 상황
		else 												    i_noticeRepository.save(dto1);
		
		return dto1;
	}
}