package com.example.android.mapper.parking;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.android.model.parking.Parking;


@Mapper
public interface ParkingMapper {
	
	// [1] 주차장 목록 조회
	@Select("SELECT * FROM tb_parking")
	List<Parking> selectAllParking();
	
	// [2] 주차장 세부 정보 조회
	@Select("Select * from tb_parking where parking_seq = #{parking_seq}")
	Parking selectDetailParking(@Param("parking_seq") int parking_seq);
}
