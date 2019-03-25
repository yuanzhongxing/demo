package com.yuanzx.demo.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by yuanzx on 2019/3/25.
 */
public class DemoRMIServerServiceImpl extends UnicastRemoteObject implements DemoRMIServerService {

    protected DemoRMIServerServiceImpl() throws RemoteException {
    }

    @Override
    public String sayHello(String name) throws RemoteException {
        return name + " hello";
    }

    @Override
    public Integer getAge() throws RemoteException{
        return 24;
    }
}
