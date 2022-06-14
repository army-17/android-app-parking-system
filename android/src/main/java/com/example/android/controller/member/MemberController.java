package com.example.android.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.android.mapper.member.MemberMapper;
import com.example.android.model.member.Member;

@RestController
@RequestMapping("/member")
public class MemberController {

	private MemberMapper mapper;
	
	// 회원가입
	@PostMapping("/signup")
	public int signup(@RequestBody Member member) {
		return mapper.signup(member);
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
	// 아이디 찾기
	@PostMapping("/findPwd")
	public String findMemberPwd(@RequestParam("member_name") String member_name, @RequestParam("car_num") String car_num, @RequestParam("member_id") String member_id) {
		return mapper.findMemberPwd(member_name, car_num, member_id);
	}
}
