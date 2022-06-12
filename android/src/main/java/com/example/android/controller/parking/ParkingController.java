package com.example.android.controller.parking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.android.mapper.parking.ParkingMapper;
import com.example.android.model.parking.Parking;

@RestController
@RequestMapping("/parking")
public class ParkingController {

	private ParkingMapper mapper;
	
	// 주차장 목록 조회
	@GetMapping("/list")
	public List<Parking> selectAllParking() {
		return mapper.selectAllParking();
	}
	
	@GetMapping("/detail/{id}")
	public Parking selectDtetailParking(@PathVariable("id") int parking_id) {
		return mapper.selectDetailParking(parking_id);
	}
	
}
