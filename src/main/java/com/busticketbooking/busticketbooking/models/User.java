package com.busticketbooking.busticketbooking.models;

public class User {
    private int userId;
    private String phone;
    private String password;
    private String email;
    private String gender;
    private String image;
    private String address;
    private String role;
    private String name;

    private String code;
    private Boolean isVerify;

    public User(int userId, String phone, String password, String email, String gender, String image, String address, String role, String name, boolean isVerify) {
        this.userId = userId;
        this.phone = phone;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.image = image;
        this.address = address;
        this.role = role;
        this.name = name;
        this.isVerify = isVerify;
    }

    public User(int userId, String phone, String password, String email, String gender, String image, String address, String role, String name, String code, boolean isVerify) {
        this.userId = userId;
        this.phone = phone;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.image = image;
        this.address = address;
        this.role = role;
        this.name = name;
        this.code = code;
        this.isVerify = isVerify;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public User() {
    }

    public Boolean isVerify() {
        return isVerify;
    }

    public void setVerify(boolean verify) {
        isVerify = verify;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
