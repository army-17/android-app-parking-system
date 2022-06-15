package com.example.android.model.reserve;

import java.time.LocalDateTime;

public class Reserve {

	private int reserve_seq;
	private int member_seq;
	private int parking_seq;
	private LocalDateTime reg_dt;
	private char is_del_yn;

	public int getReserve_seq() {
		return reserve_seq;
	}
	public void setReserve_seq(int reserve_seq) {
		this.reserve_seq = reserve_seq;
	}
	public int getMember_seq() {
		return member_seq;
	}
	public void setMember_seq(int member_seq) {
		this.member_seq = member_seq;
	}
	public int getParking_seq() {
		return parking_seq;
	}
	public void setParking_seq(int parking_seq) {
		this.parking_seq = parking_seq;
	}
	public LocalDateTime getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(LocalDateTime reg_dt) {
		this.reg_dt = reg_dt;
	}
	public char getIs_del_yn() {
		return is_del_yn;
	}
	public void setIs_del_yn(char is_del_yn) {
		this.is_del_yn = is_del_yn;
	}

	
	
}
