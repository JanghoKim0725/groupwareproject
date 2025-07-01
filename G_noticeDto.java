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
@Table(name="G_notice")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class G_noticeDto {
	
	@Id //기본키 설정 (고유번호)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int   	  gntcno;
	
	// 비밀번호
	@Column(nullable = false, length = 200)
	private String    gntcps;
	
	// 제목
	@Column(nullable = false, length = 200)
	private String    gntctt;
	
	// 작성자(관리자)
	@Column(nullable = false, length = 200)
	private String    gntcwr;
	
	// 부서
	@Column(nullable = false, length = 50)
	private String 	  gndept;
	
	// 게시일
	@CreationTimestamp
	private Timestamp gntcrd;
	
	// 내용
	@Column(length = 5000)
	private String    gntcct;
	
	// 조회수
	private int gntcht = 0;
}