package com.example.parking_system;

import android.os.Parcel;
import android.os.Parcelable;

public class ReservationData implements Parcelable {
    
    private int reservationId;
    private double latitude;
    private double longitude;
    private String name;
    private String image;
    private String date;
    private String time;
    private String lotcode;
    private String barcode;
    
    public ReservationData(
             int reservationId,
             double latitude,
             double longitude,
             String name,
             String image,
             String date,
             String time,
             String lotcode,
             String barcode) {
        
        this.reservationId = reservationId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.image = image;
        this.date = date;
        this.time = time;
        this.lotcode = lotcode;
        this.barcode = barcode;
        
    }
    
    protected ReservationData(Parcel in) {

        reservationId = in.readInt(); 
        latitude = in.readDouble(); 
        longitude = in.readDouble(); 
        name = in.readString(); 
        image = in.readString(); 
        date = in.readString(); 
        time = in.readString(); 
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
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }    
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

        parcel.writeInt(reservationId);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeString(name);
        parcel.writeString(image);
        parcel.writeString(date);
        parcel.writeString(time);
        parcel.writeString(lotcode);
        parcel.writeString(barcode);
        
    }
}
