package com.jpaproject.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.jpaproject.entity.G_noticeDto;

public interface G_noticeRepository extends JpaRepository<G_noticeDto,Integer> {
	
	// 일반공지사항 검색기능 (제목구분)
	@Query(value = "select * from G_notice where (gntctt like '%' || :search || '%' or gndept like '%' || :search || '%') ",
		   nativeQuery = true)
	
	// 검색에 따른 데이터 개수에 따라 페이지 재 정렬
	Page<G_noticeDto> findByGntcttContaining(@Param("search")String search, Pageable pageable);
}