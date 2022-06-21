package com.example.android.controller.member;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.android.mapper.member.MemberMapper;

@RestController
@RequestMapping("/member")
public class MemberController {

	
	
	private MemberMapper mapper;

	public MemberController(MemberMapper mapper) {
		this.mapper = mapper;
	}

	// 회원가입
	@PostMapping("/signup")
	public void signup(@RequestParam("member_name") String member_name, 
			@RequestParam("car_num") String car_num, 
			@RequestParam("member_id") String member_id,
			@RequestParam("password") String password) {
		mapper.signup(member_name, car_num, member_id, password);
	}
	
	// 로그인
	@PostMapping("/login")
	public int login(@RequestParam("member_id") String member_id, @RequestParam("password") String password) {
		return mapper.login(member_id, password);
	}
	
	// 아이디 찾기
	@PostMapping("/findId")
	public String findMemberId(@RequestParam("member_name") String member_name, @RequestParam("car_num") String car_num) {
		return mapper.findMemberId(member_name, car_num);
	}
	
	// 비밀번호 찾기
	@PostMapping("/findPwd")
	public String findMemberPwd(@RequestParam("member_name") String member_name, @RequestParam("car_num") String car_num, @RequestParam("member_id") String member_id) {
		return mapper.findMemberPwd(member_name, car_num, member_id);
	}
	
	// 자동차 번호 중복확인
	@PostMapping("/checkCarNum")
	public int checkCarNum(@RequestParam("car_num") String car_num) {
		return mapper.checkCarNum(car_num);
	}
	
	// 아이디 중복확인
	@PostMapping("/checkMemberId")
	public int checkMemberId(@RequestParam("member_id") String member_id) {
		return mapper.checkMemberId(member_id);
	}
}
