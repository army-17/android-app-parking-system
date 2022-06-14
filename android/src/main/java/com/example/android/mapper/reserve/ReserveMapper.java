package com.example.android.mapper.reserve;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.android.model.reserve.Reserve;


@Mapper
public interface ReserveMapper {
	
	// [1] 예약 목록 조회
	@Select("SELECT * FROM tb_reserve where member_seq = #{member_seq} and is_del_yn = 'N'")
	List<Reserve> selectAllReserve(@Param("member_seq") int member_seq);

	// [2] 예약 세부 정보 조회
	@Select("SELECT * FROM tb_reserve where reserve_seq = #{reserve_seq} and reserve_seq = #{reserve_seq} and is_del_yn = 'N'")
	Reserve selectDetailReserve(@Param("reserve_seq") int reserve_seq, @Param("member_seq") int member_seq);
	
	// [3] 예약 등록
	@Insert("INSERT INTO tb_reserve VALUES(#{reserve.member_seq}, #{reserve.parking_seq}, now())")
	int inserReserve(@Param("reserve") Reserve reserve);

	// [4] 예약 취소
	@Update("UPDATE tb_reserve SET is_del_yn = 'Y' WHERE reserve_seq= #{reserve_seq}")
	int cancelReserve(@Param("reserve_seq") int reserve_seq);

}
