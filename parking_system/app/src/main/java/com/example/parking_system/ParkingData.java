package com.example.parking_system;

import android.os.Parcel;
import android.os.Parcelable;

public class ParkingData implements Parcelable {

    private int parking_seq;
    private String parking_name;
    private String address;
    private int fee;
    private double latitude;
    private double longitude;
    private String img_save_path;
    private int remain_cnt;
    private String is_res_yn;

    public ParkingData(
            int parking_seq,
            String parking_name,
            String address,
            int fee,
            double latitude,
            double longitude,
            String img_save_path,
            int remain_cnt,
            String is_res_yn) {

        this.parking_seq = parking_seq;
        this.parking_name = parking_name;
        this.address = address;
        this.fee =  fee;
        this.latitude = latitude;
        this.longitude = longitude;
        this.img_save_path = img_save_path;
        this.remain_cnt = remain_cnt;
        this.is_res_yn =  is_res_yn;

    }

    protected ParkingData(Parcel in) {

        parking_seq = in.readInt();
        parking_name = in.readString();
        address = in.readString();
        fee = in.readInt();
        latitude = in.readDouble();
        longitude = in.readDouble();
        img_save_path = in.readString();
        remain_cnt = in.readInt();
        is_res_yn = in.readString();

    }

    public static final Creator<ParkingData> CREATOR = new Creator<ParkingData>() {
        @Override
        public ParkingData createFromParcel(Parcel parcel) {
            return new ParkingData(parcel);
        }

        @Override
        public ParkingData[] newArray(int i) {
            return new ParkingData[i];
        }
    };

    //gets
    public int getParking_seq() { return parking_seq; }
    public String getParking_name() { return parking_name; }
    public String getAddress() { return address; }
    public int getFee() { return fee; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public String getImg_save_path() { return img_save_path; }
    public int getRemain_cnt() { return remain_cnt; }
    public String getIs_res_yn() { return is_res_yn; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(parking_seq);
        parcel.writeString(parking_name);
        parcel.writeString(address);
        parcel.writeInt(fee);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeString(img_save_path);
        parcel.writeInt(remain_cnt);
        parcel.writeString(is_res_yn);

    }
}
