package com.jpaproject.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.jpaproject.entity.G_noticeDto;
import com.jpaproject.repository.G_noticeRepository;

@Service
public class G_noticeService {

	// 리퍼지토리 설정
	public final G_noticeRepository g_noticeRepository;
	
	// 전체컨트롤러 서비스 실행 설정
	public G_noticeService(G_noticeRepository g_noticeRepository) {this.g_noticeRepository = g_noticeRepository;}
	
	// 일반공지사항 목록화면 출력 (필수공지사항 -> 일반공지사항 순으로 역순 정렬 + 검색기능에 따른 데이터 개수에 따라 페이지 재 정렬)
	public Page<G_noticeDto> list(int page,int size,String search) {
			
		 Pageable pageable = PageRequest.of(page,size);
		 return g_noticeRepository.findByGntcttContaining(search, pageable);
	}
	
	// 일반공지사항 목록화면 총데이터 개수
	public Long count() {return g_noticeRepository.count();}
	
	// 일반공지사항 상세보기
	public G_noticeDto detail(int gntcno) {return g_noticeRepository.findById(gntcno).orElse(null);}
	
	// 일반공지사항 조회수증가
	public G_noticeDto saveHits(int gntcno) {
		
		G_noticeDto dto = detail(gntcno);
		int      hits = dto.getGntcht();
		hits++;
		dto.setGntcht(hits++);
		//  dto :: save시 키값이 포함 되어있는 경우 UPDATE 처리함
		return g_noticeRepository.save(dto);
	}
	
	// 일반공지사항 등록,수정,삭제 서비스
	public G_noticeDto notice(G_noticeDto dto1) {
		
		// 수정하기 상황(바뀐내용 덮어쓰기)
		if(dto1.getGntcno() > 0 && dto1.getGntctt() != null && !dto1.getGntctt().equals("")) {
			
			G_noticeDto dto2 = detail(dto1.getGntcno());
			
			// 조회수개수 초기화방지 그대로 유지
			dto1.setGntcht(dto2.getGntcht());
			
			// 등록일 초기화방지 그대로 유지
			dto1.setGntcrd(dto2.getGntcrd());
		}
		
		// 삭제하기 상황
		if(dto1.getGntcno() > 0 && dto1.getGntctt().equals("")) g_noticeRepository.deleteById(dto1.getGntcno());
		
		// 등록하기 상황
		else 												    g_noticeRepository.save(dto1);
		
		return dto1;
	}
}