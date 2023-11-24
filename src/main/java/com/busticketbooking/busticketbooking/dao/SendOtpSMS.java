/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busticketbooking.busticketbooking.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.SplittableRandom;

/**
 *
 * @author Admin
 */
public class SendOtpSMS {
    public static void main(String[] args ){
        String phone = "";
        String otpStr = SendOtpSMS.generateOtp(5);
        System.out.println("Generated OTP = " + otpStr);
        SendOtpSMS.sendSMS(otpStr, phone);
    }
    
    public static void sendSMS(String otpStr,String phone){
        try{
            String apiKey = "apiKey" + "ba0350a85e86c8dfabfc330bec14b5cc-d8f0e8c8-152a-4ac7-9575-0ccdf5c3a4ab";
            String message = "&message" + URLEncoder.encode("Your OTP is " + otpStr,"UTF-8");
            String numbers = "&number=" + phone;
            String apiURL = "ppwnve.api.infobip.com" + apiKey + message + numbers;
             URL url = new URL(apiURL);
             URLConnection connection = url.openConnection();
             connection.setDoOutput(true);
   
             BufferedReader reader = new BufferedReader(new
             InputStreamReader(connection.getInputStream()));
   
            String line = "";
            StringBuilder sb = new StringBuilder();

            while ( (line = reader.readLine()) != null) {
             sb.append(line).append("\n");
            }
            System.out.println(sb.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static String generateOtp(int otpLength){
        SplittableRandom splittableRandom = new SplittableRandom();
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < otpLength ; i++){
            sb.append(splittableRandom.nextInt(0,10));
        }
        return sb.toString();
    }
}
