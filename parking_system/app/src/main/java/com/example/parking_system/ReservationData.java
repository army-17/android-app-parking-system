package com.example.parking_system;

import android.os.Parcel;
import android.os.Parcelable;

public class ReservationData implements Parcelable {
    
    private int memberId;
    private int reservationId; //reserve/reserve_seq
    private double latitude; //parking/
    private double longitude; //parking/
    private String name; //member/member_name
    private String image;
    private String date; //
    private String startTime; //
    private String endTime;
    private String lotcode;
    private String barcode;
    
    public ReservationData(
            int memberId,
            int reservationId,
            double latitude,
            double longitude,
            String name,
            String image,
            String date,
            String startTime,
            String endTime,
            String lotcode,
            String barcode) {

        this.memberId = memberId;
        this.reservationId = reservationId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.image = image;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lotcode = lotcode;
        this.barcode = barcode;
        
    }
    
    protected ReservationData(Parcel in) {

        memberId = in.readInt();
        reservationId = in.readInt(); 
        latitude = in.readDouble(); 
        longitude = in.readDouble(); 
        name = in.readString(); 
        image = in.readString(); 
        date = in.readString(); 
        startTime = in.readString();
        endTime = in.readString();
        lotcode = in.readString(); 
        barcode = in.readString(); 
        
    }
    
    public static final Creator<ReservationData> CREATOR = new Creator<ReservationData>() {
        @Override
        public ReservationData createFromParcel(Parcel in) {
            return new ReservationData(in);
        }

        @Override
        public ReservationData[] newArray(int size) {
            return new ReservationData[size];
        }
    };
    
    //get, sets
    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }
    public int getReservationId() {
        return reservationId;
    }
    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }    
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }
    public String getLotcode() {
        return lotcode;
    }
    public void setLotcode(String lotcode) {
        this.lotcode = lotcode;
    }    
    public String getBarcode() {
        return barcode;
    }
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    //end get, sets

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(memberId);
        parcel.writeInt(reservationId);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeString(name);
        parcel.writeString(image);
        parcel.writeString(date);
        parcel.writeString(startTime);
        parcel.writeString(endTime);
        parcel.writeString(lotcode);
        parcel.writeString(barcode);
        
    }
}
