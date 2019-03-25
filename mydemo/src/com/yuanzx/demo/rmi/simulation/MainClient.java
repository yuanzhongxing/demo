package com.yuanzx.demo.rmi.simulation;

/**
 * Created by yuanzx on 2019/3/25.
 */
public class MainClient {

    public static void main(String[] args) {
        UserInfoService userInfoService = new UserInfoService();
        userInfoService.getUserInfo();
    }

}
