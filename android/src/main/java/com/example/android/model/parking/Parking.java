package com.example.android.model.parking;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
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

}
