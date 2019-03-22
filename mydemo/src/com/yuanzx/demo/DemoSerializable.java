package com.yuanzx.demo;

import com.yuanzx.model.Person;

import java.io.*;

/**
 * Created by yuanzx on 2019/3/21.
 */
public class DemoSerializable {

    public static void main(String[] args) {
//        serializePersion();//序列化
//        DeSerializePersion();//反序列化
        deepClone();//深度克隆
    }

    /**
     * 序列化
     */
    public static void serializePersion(){
        try {
            Person person = new Person();
            person.setAge(11);
            person.setName("张三");

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("persion")));
            objectOutputStream.writeObject(person);

            File f = new File("persion");
            System.out.println(f.length());

            Person person2 = new Person();
            person2.setAge(11);
            person2.setName("张三");
            objectOutputStream.writeObject(person2);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 反序列化
     */
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

    public static void deepClone(){
        Person person = new Person();
        person.setName("yuanzx");
        person.setAge(11);
        person.setAddr("111");
        person.setSex(1);
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("person")));
            objectOutputStream.writeObject(person);
            objectOutputStream.close();

            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("person")));
            Person person1 = (Person) objectInputStream.readObject();

            System.out.println(person);
            System.out.println(person1);

            System.out.println(person == person1);//结果：false    与深度clone的对象比对

            Person person2 = person;
            System.out.println(person == person2);// 结果：true    与浅度克隆的对象比对
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

}
