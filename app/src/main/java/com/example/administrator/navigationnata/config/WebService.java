package com.example.administrator.navigationnata.config;

/**
 * Created by Administrator on 3/31/2016.
 */
public class WebService {

    private static final String URL= "http://192.168.10.180/andro/wcf/androServices.asmx/";
    private static String product = "Products";
    private static String login = "Login";
    private static String register = "Register";

    public static String getProduct() {
        return URL + product;
    }

    public static String getLogin() {
        return URL + login;
    }

    public static String getRegister() {
        return URL + register;
    }
}
