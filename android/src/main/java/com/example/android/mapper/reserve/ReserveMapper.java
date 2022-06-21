package com.example.android.mapper.reserve;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.android.model.reserve.Reserve;

@Mapper
public interface ReserveMapper {
	
	// [1] 예약 목록 조회
	@Select("SELECT * FROM tb_reserve WHERE MEMBER_SEQ = #{member_seq}")
	List<Reserve> selectAllReserve(@Param("member_seq") int member_seq);

	// [2] 예약 세부 정보 조회
	@Select("SELECT * FROM tb_reserve WHERE RESERVE_SEQ = #{reserve_seq} AND RESERVE_SEQ = #{reserve_seq}")
	Reserve selectDetailReserve(@Param("reserve_seq") int reserve_seq, @Param("member_seq") int member_seq);
	
	// [3] 예약 등록
	@Insert("INSERT INTO tb_reserve (member_seq, parking_seq, total_fee, parking_name, lotcode, reserve_start_date, reserve_start_time, reserve_end_date, reserve_end_time) "
			+ "VALUES(#{member_seq}, #{parking_seq}, #{total_fee}, #{parking_name}, #{lotcode}, #{reserve_start_date}, #{reserve_start_time}, #{reserve_end_date}, #{reserve_end_time})")
	int inserReserve(@Param("member_seq") int member_seq, @Param("parking_seq") int parking_seq
			, @Param("total_fee") int total_fee, @Param("parking_name") String parking_name, @Param("lotcode") String lotcode
			, @Param("reserve_start_date") String reserve_start_date, @Param("reserve_start_time") String reserve_start_time
			, @Param("reserve_end_date") String reserve_end_date, @Param("reserve_end_time") String reserve_end_time);

	// [4] 예약 취소
	@Delete("DELETE FROM tb_reserve WHERE RESERVE_SEQ = #{reserve_seq}")
	int cancelReserve(@Param("reserve_seq") int reserve_seq);

}
