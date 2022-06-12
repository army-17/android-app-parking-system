package com.example.android.mapper.member;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.android.model.member.Member;


@Mapper
public interface MemberMapper {
	// [1] 회원가입
	@Insert("INSERT INTO tb_member VALUES(#{member.member_name}, #{member.car_name}, #{member.member_id}, #{member.password})")
	int signup(@Param("member") Member member);
	
	// [2] 로그인
	@Select("SELECT count(*) FROM TB_member WHERE member_id = #{member_id} and password = #{password}")
	int login(@Param("member_id") String member_id, @Param("password") String password);
	
	// [3] 아이디 찾기
	@Select("SELECT member_id FROM TB_member WHERE member_name = #{member_name} and car_num = #{car_num}")
	String findMemberId(@Param("member_name") String member_name, @Param("car_num") String car_num );
	
	// [4] 비밀번호 찾기
	@Select("SELECT password FROM TB_member WHERE member_name = #{member_name} and car_num = #{car_num } and member_id = #{member_id}")
	String findMemberPwd(@Param("member_name") String member_name, @Param("car_num") String car_num, @Param("member_id") String member_id );
	
}