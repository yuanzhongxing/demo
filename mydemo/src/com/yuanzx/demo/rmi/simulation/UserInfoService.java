package com.yuanzx.demo.rmi.simulation;

/**
 * Created by yuanzx on 2019/3/25.
 */
public class UserInfoService {

    UserService userService = new UserService_client();

    public void getUserInfo(){
        User user = userService.getUser();
        System.out.println(user);
    }

}
