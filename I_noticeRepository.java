package com.jpaproject.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.jpaproject.entity.I_noticeDto;

public interface I_noticeRepository extends JpaRepository<I_noticeDto,Integer> {
	
	// 전체공지사항 검색기능(제목,부서별 분류) + 필수공지사항 -> 일반공지사항 순으로 역순정렬
	@Query(value = "select * from I_notice where intctt,indept like '%' || :search || '%' "
				 + "order by case when intcca = '필수' then 0 else 1 end, intcno desc", 
		   countQuery  = "select count(*) from I_notice where intctt like '%' || :search || '%'", nativeQuery = true)
	
	// 검색에 따른 데이터 개수에 따라 페이지 재 정렬
	Page<I_noticeDto> findByIntcttContaining(@Param("search")String search, Pageable pageable);
}