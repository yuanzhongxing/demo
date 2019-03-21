package com.yuanzx.demo;

import com.yuanzx.model.Person;

import java.io.*;

/**
 * Created by yuanzx on 2019/3/21.
 */
public class DemoSerializable {

    public static void main(String[] args) {
        serializePersion();
        DeSerializePersion();
    }

    public static void serializePersion(){

        try {
            Person person = new Person();
            person.setAge(11);
            person.setName("张三");

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("persion")));
            objectOutputStream.writeObject(person);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void DeSerializePersion(){
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("persion")));
            Person person = (Person) objectInputStream.readObject();
            System.out.println(person.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
