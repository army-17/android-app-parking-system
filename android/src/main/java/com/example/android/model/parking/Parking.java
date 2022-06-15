package com.example.android.model.parking;

public class Parking {
	
	private int parking_seq;
	private String parking_name;
	private String address;
	private int fee;
	private double latitude;
	private double longitude;
	private String img_save_path;
	private int remain_cnt;
	private char is_res_yn;
	
	public int getParking_seq() {
		return parking_seq;
	}
	public void setParking_seq(int parking_seq) {
		this.parking_seq = parking_seq;
	}
	public String getParking_name() {
		return parking_name;
	}
	public void setParking_name(String parking_name) {
		this.parking_name = parking_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getImg_save_path() {
		return img_save_path;
	}
	public void setImg_save_path(String img_save_path) {
		this.img_save_path = img_save_path;
	}
	public int getRemain_cnt() {
		return remain_cnt;
	}
	public void setRemain_cnt(int remain_cnt) {
		this.remain_cnt = remain_cnt;
	}
	public char getIs_res_yn() {
		return is_res_yn;
	}
	public void setIs_res_yn(char is_res_yn) {
		this.is_res_yn = is_res_yn;
	}

	
}
