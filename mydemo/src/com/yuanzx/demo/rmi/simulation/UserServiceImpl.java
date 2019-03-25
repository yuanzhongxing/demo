package com.yuanzx.demo.rmi.simulation;

/**
 * Created by yuanzx on 2019/3/25.
 */
public class UserServiceImpl implements UserService {


    @Override
    public User getUser() {
        User user = new User();
        user.setName("袁忠兴");
        user.setAge(29);
        return user;
    }
}
