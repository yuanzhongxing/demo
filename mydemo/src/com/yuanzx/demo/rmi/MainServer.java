package com.yuanzx.demo.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by yuanzx on 2019/3/25.
 */
public class MainServer {

    public static void main(String[] args) {
        try {
            DemoRMIServerService demoRMIServerService = new DemoRMIServerServiceImpl();
            LocateRegistry.createRegistry(8888);
            Naming.bind("rmi://localhost:8888/sayHello",demoRMIServerService);
            System.out.println("server success");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }

    }

}
