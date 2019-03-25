package com.yuanzx.demo.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by yuanzx on 2019/3/25.
 */
public class MainClient {

    public static void main(String[] args) {
        try {
            DemoRMIServerService demoRMIServerService =  (DemoRMIServerService) Naming.lookup("rmi://localhost:8888/sayHello");
            System.out.println(demoRMIServerService.sayHello("袁忠兴"));
            System.out.println(demoRMIServerService.getAge());
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
