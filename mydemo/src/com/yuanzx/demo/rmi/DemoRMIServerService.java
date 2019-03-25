package com.yuanzx.demo.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by yuanzx on 2019/3/25.
 */

/**
 * 继承Remote，表示可以被远程调用
 */
public interface DemoRMIServerService extends Remote {

    String sayHello(String name) throws RemoteException;

    Integer getAge()  throws RemoteException;

}
