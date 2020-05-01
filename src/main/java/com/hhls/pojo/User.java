package com.hhls.pojo;

public class User {

    private String loginId;
    private String loginPsd;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginPsd() {
        return loginPsd;
    }

    public void setLoginPsd(String loginPsd) {
        this.loginPsd = loginPsd;
    }

    @Override
    public String toString() {
        return "User{" +
            "loginId='" + loginId + '\'' +
            ", loginPsd='" + loginPsd + '\'' +
            '}';
    }
}