package com.yuanzx.demo.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ThreadUtils;

/**
 * Created by yuanzx on 2019/3/29.
 */
public class DemoCuratorCreateSession {

    private static final String ADDRESS = "192.168.236.128:2181,192.168.236.129:2181,192.168.236.130:2181,192.168.236.131:2181";

    public static final Integer sessionTimeOut = 5000;

    public static void main(String[] args) {
        //创建会话的两种方式 normal
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(ADDRESS,5000,
                5000,new ExponentialBackoffRetry(1000,1));
        curatorFramework.start();


        //fluent风格
        CuratorFramework curatorFramework2 = CuratorFrameworkFactory.builder().connectString(ADDRESS).sessionTimeoutMs(5000).connectionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000,3)).build();
        curatorFramework2.start();

        System.out.println("success");
    }

    public static CuratorFramework getSession(){
        //fluent风格
        CuratorFramework curatorFramework2 = CuratorFrameworkFactory.builder().connectString(ADDRESS).sessionTimeoutMs(5000).connectionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(10000,3)).build();
        curatorFramework2.start();

        System.out.println("success");
        return curatorFramework2;
    }

}
