package com.example.parking_system;

public class MemberData {
    private String member_name;
    private String car_num;
    private String member_id;
    private String password;

    public MemberData()  {}

    public MemberData(String member_name, String car_num, String member_id, String password){
        super();
        this.member_name = member_name;
        this.car_num = car_num;
        this.member_id = member_id;
        this.password = password;
    }

    public String getMember_name(){
        return member_name;
    }

    public void setMember_name(String member_name){
        this.member_name = member_name;
    }

    public String getCar_num(){
        return car_num;
    }

    public void setCar_num(String car_num){
        this.car_num = car_num;
    }

    public String getMember_id(){
        return member_id;
    }

    public void setMember_id(String member_id){
        this.member_id = member_id;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }


}
