package com.jpaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.jpaproject.entity.RoomDto;

public interface RoomRepository extends JpaRepository<RoomDto,Integer> {
	
	@Query(value = "select nvl(max(roomcd),100) + 1 from room",nativeQuery = true)
	int findByNative();
}
