package com.yuanzx.demo.rmi.simulation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by yuanzx on 2019/3/25.
 */
public class MainServer {

    public static void main(String[] args) {
        UserService userService_server = new UserService_server();

        try {
            ServerSocket serverSocket = new ServerSocket(8889);
            Socket socket1 = serverSocket.accept();
            while (socket1!=null){
                ObjectInputStream objectInputStream = new ObjectInputStream(socket1.getInputStream());
                String method = (String) objectInputStream.readObject();
                if(method.equals("getUser")){
                    User user = userService_server.getUser();
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket1.getOutputStream());
                    objectOutputStream.writeObject(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {

        }
    }
}
