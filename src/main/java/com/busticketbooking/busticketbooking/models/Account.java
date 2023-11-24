/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busticketbooking.busticketbooking.models;

/**
 *
 * @author Admin
 */
public class Account {
    private int id;
    private String phone;
    private String password;
    private String email;
    private String gender;  
    private String image;
    private String address;
    private String role;
    private String name;
    private int code;

    private boolean is_verify;
    public Account(){}

    public Account(int id, String phone, String password, String email, String gender, String image, String address, String role, String name, int code, boolean is_verify) {
        this.id = id;
        this.phone = phone;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.image = image;
        this.address = address;
        this.role = role;
        this.name = name;
        this.code = code;
        this.is_verify = is_verify;
    }

    public boolean isIs_verify() {
        return is_verify;
    }

    public void setIs_verify(boolean is_verify) {
        this.is_verify = is_verify;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", phone=" + phone + ", password=" + password + ", email=" + email + ", gender=" + gender + ", image=" + image + ", address=" + address + ", role=" + role + ", name=" + name + ", code=" + code + '}';
    }

    
    
    
}