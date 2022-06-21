package com.example.parking_system;

public class LogedMemberSeq {
    public static int login_member_seq;

    public static void setLogin_member_seq(int login_member_seq) {
        LogedMemberSeq.login_member_seq = login_member_seq;
    }

    public static int getLogin_member_seq() {
        return login_member_seq;
    }
}
