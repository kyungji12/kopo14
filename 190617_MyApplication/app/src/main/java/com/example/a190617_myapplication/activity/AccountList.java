package com.example.a190617_myapplication.activity;

public class AccountList {      //회원 관리 Class
    private String accountId;       //회원 아이디 String
    private String accountPw;       //회원 비밀번호 String

    public AccountList(String accountId, String accountPw){
        this.accountId = accountId;
        this.accountPw = accountPw;
    }

    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public String getAccountPw() {
        return accountPw;
    }
    public void setAccountPw(String accountPw) {
        this.accountPw = accountPw;
    }
}