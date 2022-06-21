package com.example.parking_system;

import android.os.Parcel;
import android.os.Parcelable;

//        // int reserve_seq = (auto-gen at upload)
//
//        // int member_seq = from static value from login
//        // int parking_seq = parking_seq
//        // int total_fee = String totalFee
//        // String parking_name = parking_name
//        // String lotcode = java.util.random
//        // String reserve_start_date = yMd startDate
//        // String reserve_start_time = Hm startDate
//        // String reserve_end_date = yMd endDate
//        // String reserve_end_time = Hm endTime

public class ReserveData implements Parcelable {
    
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
    
    public ReserveData(
            int reserve_seq,
            int member_seq,
            int parking_seq,
            int total_fee,
            String parking_name,
            String lotcode,
            String reserve_start_date,
            String reserve_start_time,
            String reserve_end_date,
            String reserve_end_time
    ) {

        this.reserve_seq = reserve_seq;
        this.member_seq = member_seq;
        this.parking_seq = parking_seq;
        this.total_fee = total_fee;
        this.parking_name = parking_name;
        this.lotcode = lotcode;
        this.reserve_start_date = reserve_start_date;
        this.reserve_start_time = reserve_start_time;
        this.reserve_end_date = reserve_end_date;
        this.reserve_end_time = reserve_end_time;
        
    }
    
    protected ReserveData(Parcel in) {

        reserve_seq = in.readInt();
        member_seq = in.readInt();
        parking_seq = in.readInt();
        total_fee = in.readInt();
        parking_name = in.readString();
        lotcode = in.readString();
        reserve_start_date = in.readString();
        reserve_start_time = in.readString();
        reserve_end_date = in.readString();
        reserve_end_time = in.readString();
        
    }

    public static final Creator<ReserveData> CREATOR = new Creator<ReserveData>() {
        @Override
        public ReserveData createFromParcel(Parcel in) {
            return new ReserveData(in);
        }

        @Override
        public ReserveData[] newArray(int size) {
            return new ReserveData[size];
        }
    };

    //get, sets

    public int getReserve_seq() {
        return reserve_seq;
    }

    public int getMember_seq() {
        return member_seq;
    }

    public int getParking_seq() {
        return parking_seq;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public String getParking_name() {
        return parking_name;
    }

    public String getLotcode() {
        return lotcode;
    }

    public String getReserve_start_date() {
        return reserve_start_date;
    }

    public String getReserve_start_time() {
        return reserve_start_time;
    }

    public String getReserve_end_date() {
        return reserve_end_date;
    }

    public String getReserve_end_time() {
        return reserve_end_time;
    }

    public void setReserve_seq(int reserve_seq) {
        this.reserve_seq = reserve_seq;
    }

    public void setMember_seq(int member_seq) {
        this.member_seq = member_seq;
    }

    public void setParking_seq(int parking_seq) {
        this.parking_seq = parking_seq;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public void setParking_name(String parking_name) {
        this.parking_name = parking_name;
    }

    public void setLotcode(String lotcode) {
        this.lotcode = lotcode;
    }

    public void setReserve_start_date(String reserve_start_date) {
        this.reserve_start_date = reserve_start_date;
    }

    public void setReserve_start_time(String reserve_start_time) {
        this.reserve_start_time = reserve_start_time;
    }

    public void setReserve_end_date(String reserve_end_date) {
        this.reserve_end_date = reserve_end_date;
    }

    public void setReserve_end_time(String reserve_end_time) {
        this.reserve_end_time = reserve_end_time;
    }

    //end get, sets


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(reserve_seq);
        parcel.writeInt(member_seq);
        parcel.writeInt(parking_seq);
        parcel.writeInt(total_fee);
        parcel.writeString(parking_name);
        parcel.writeString(lotcode);
        parcel.writeString(reserve_start_date);
        parcel.writeString(reserve_start_time);
        parcel.writeString(reserve_end_date);
        parcel.writeString(reserve_end_time);
        
    }
}


























