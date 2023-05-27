package com.hemant.university_database;

public class storing_data {
    String username,phone,gmail,password;

    storing_data(String user, String phone, String gmail, String password)
    {
        this.username=user;
        this.phone=phone;
        this.gmail=gmail;
        this.password=password;

    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getUser() {
        return username;
    }

    public void setUser(String user) {
        this.username = user;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
