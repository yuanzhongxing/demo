package com.yuanzx.demo.rmi.simulation;

/**
 * Created by yuanzx on 2019/3/25.
 */
public class UserService_server implements UserService{

    private UserService userService = new UserServiceImpl();

    @Override
    public User getUser() {
        return userService.getUser();
    }
}
