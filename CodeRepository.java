package com.jpaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.jpaproject.entity.CodeDto;

@Repository
public interface CodeRepository extends JpaRepository<CodeDto,String> {
	
	// 부서명으로 코드값 가져오기
	@Query("SELECT c.ucode FROM CodeDto c WHERE c.ncode = :deptName AND c.pcode = 'B200'")
	String findUcodeByNcode(@Param("deptName") String deptName);
}
