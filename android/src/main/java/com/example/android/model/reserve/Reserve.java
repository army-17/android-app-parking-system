package com.example.android.model.reserve;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Reserve {

	private int reserve_seq;
	private int member_seq;
	private int parking_seq;
	private LocalDateTime reg_dt;
	private char is_del_yn;
	
}
