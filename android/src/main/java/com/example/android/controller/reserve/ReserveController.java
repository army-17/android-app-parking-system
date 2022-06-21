package com.example.android.controller.reserve;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.android.mapper.reserve.ReserveMapper;
import com.example.android.model.reserve.Reserve;

@RestController
@RequestMapping("/reserve")
public class ReserveController {

	private ReserveMapper mapper;
	
	public ReserveController(ReserveMapper mapper) {
		this.mapper = mapper;
	}

	@GetMapping("/list/{member_seq}")
	public List<Reserve> selectAllReserve(@PathVariable("member_seq") int member_seq) {
		return mapper.selectAllReserve(member_seq);
	}
	
	@PostMapping("/detail/{reserve_seq}")
	public Reserve selectDetailReserve(@PathVariable("reserve_seq") int reserve_seq, @RequestParam("member_seq") int member_seq) {
		return mapper.selectDetailReserve(reserve_seq, member_seq);
	}
	
	@PostMapping("/insert")
	public int insertReserve(@RequestParam("member_seq") int member_seq, 
			@RequestParam("parking_seq") int parking_seq,
			@RequestParam("total_fee") int total_fee,
			@RequestParam("parking_name") String parking_name,
			@RequestParam("lotcode") String lotcode,
			@RequestParam("reserve_start_date") String reserve_start_date,
			@RequestParam("reserve_start_time") String reserve_start_time,
			@RequestParam("reserve_end_date") String reserve_end_date,
			@RequestParam("reserve_end_time") String reserve_end_time) {
		return mapper.inserReserve(member_seq, parking_seq, total_fee, parking_name, 
				lotcode, reserve_start_date, reserve_start_time, reserve_end_date, reserve_end_time);
	}
	
	@PostMapping("/cancel")
	public int cancelReserve(@RequestParam("reserve_seq") int reserve_seq) {
		return mapper.cancelReserve(reserve_seq);
	}
}
