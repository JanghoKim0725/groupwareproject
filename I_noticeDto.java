package com.jpaproject.entity;

import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="I_notice")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class I_noticeDto {
	
	@Id //기본키 설정 (고유번호)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int   	  intcno;
	
	// 비밀번호
	@Column(nullable = false, length = 200)
	private String    intcps;
	
	// 제목
	@Column(nullable = false, length = 200)
	private String    intctt;
	
	// 작성자(관리자)
	@Column(nullable = false, length = 200)
	private String    intcwr;
	
	// 부서
	@Column(nullable = false, length = 50)
	private String 	  ideptno;
	
	// 유형 일반/필수
	@Column(nullable = false, length = 50)
	private String 	  intcca;
	
	// 게시일
	@CreationTimestamp
	private Timestamp intcrd;
	
	// 내용
	@Column(length = 5000)
	private String    intcct;
	
	// 조회수
	private int intcht = 0;
}