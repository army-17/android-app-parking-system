package com.example.parking_system;

import android.os.Parcel;
import android.os.Parcelable;

public class MemberData implements Parcelable {

    private int member_seq;
    private String member_name;
    private String car_num;
    private String member_id;
    private String password;

    public MemberData(
            int member_seq,
            String member_name,
            String car_num,
            String member_id,
            String password
    ) {
        this.member_seq = member_seq;
        this.member_name = member_name;
        this.car_num = car_num;
        this.member_id = member_id;
        this.password = password;
    }

    protected MemberData(Parcel in) {

        member_seq = in.readInt();
        member_name = in.readString();
        car_num = in.readString();
        member_id = in.readString();
        password = in.readString();

    }

    public static final Creator<MemberData> CREATOR = new Creator<MemberData>() {
        @Override
        public MemberData createFromParcel(Parcel parcel) {
            return new MemberData(parcel);
        }

        @Override
        public MemberData[] newArray(int i) {
            return new MemberData[i];
        }
    };

    //get, sets
    public int getMember_seq() { return member_seq; }
    public void setMember_seq(int member_seq) { this.member_seq = member_seq; }
    public String getMember_name() { return member_name; }
    public void setMember_name(String member_name) { this.member_name = member_name; }
    public String getCar_num() { return car_num; }
    public void setCar_num(String car_num) { this.car_num = car_num; }
    public String getMember_id() { return member_id; }
    public void setMember_id(String member_id) { this.member_id = member_id;}
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    //end get, sets


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(member_seq);
        parcel.writeString(member_name);
        parcel.writeString(car_num);
        parcel.writeString(member_id);
        parcel.writeString(password);

    }
}
