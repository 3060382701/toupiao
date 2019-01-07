package com.cssl.pojo;


public class User {

    private Integer uid1;
    private String username;
    private String password1;
    private String isadmin;

    public String getUsername() {
        return username;
    }

    public void setUid1(Integer uid1) {
        this.uid1 = uid1;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public void setIsadmin(String isadmin) {
        this.isadmin = isadmin;
    }

    public String getPassword1() {
        return password1;
    }

    public String getIsadmin() {
        return isadmin;
    }

    public Integer getUid1() {
        return uid1;
    }
}
