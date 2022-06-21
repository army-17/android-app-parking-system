package com.example.android.model.reserve;

import java.time.LocalDateTime;

public class Reserve {

	private int reserve_seq;
	private int member_seq;
	private int parking_seq;
	private int total_fee;
	private String parking_name;
	private String lotcode;
	private String reserve_start_date;
	private String reserve_start_time;
	private String reserve_end_date;
	private String reserve_end_time;
	
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
	public int getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}
	public String getParking_name() {
		return parking_name;
	}
	public void setParking_name(String parking_name) {
		this.parking_name = parking_name;
	}
	public String getLotcode() {
		return lotcode;
	}
	public void setLotcode(String lotcode) {
		this.lotcode = lotcode;
	}
	public String getReserve_start_date() {
		return reserve_start_date;
	}
	public void setReserve_start_date(String reserve_start_date) {
		this.reserve_start_date = reserve_start_date;
	}
	public String getReserve_start_time() {
		return reserve_start_time;
	}
	public void setReserve_start_time(String reserve_start_time) {
		this.reserve_start_time = reserve_start_time;
	}
	public String getReserve_end_date() {
		return reserve_end_date;
	}
	public void setReserve_end_date(String reserve_end_date) {
		this.reserve_end_date = reserve_end_date;
	}
	public String getReserve_end_time() {
		return reserve_end_time;
	}
	public void setReserve_end_time(String reserve_end_time) {
		this.reserve_end_time = reserve_end_time;
	}

}
