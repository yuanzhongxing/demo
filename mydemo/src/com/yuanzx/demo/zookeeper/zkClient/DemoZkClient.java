package com.yuanzx.demo.zookeeper.zkClient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * Created by yuanzx on 2019/3/29.
 */
public class DemoZkClient {

    private static final String ADDRESS = "192.168.236.128:2181,192.168.236.129:2181,192.168.236.130:2181,192.168.236.131:2181";


    public static void main(String[] args) {
        //建立连接
        ZkClient zkClient = getZkClient();

        /**
         * 事件监听/订阅
         * 相比与原生的API，原生的是所有path的监听都放在一起了，这里分开了
         */
        zkClient.subscribeDataChanges("/yzx", new IZkDataListener() {
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println(String.format("Data is change,path=%s,value=%s",s,o));
            }

            public void handleDataDeleted(String s) throws Exception {
            }
        });

        /**
         * 创建临时节点
         */
        zkClient.createEphemeral("/yzx","123");

        /**
         * 创建持久节点
         */
        zkClient.createPersistent("/yzx1","123");

        /**
         * 更新一个节点
         */
        zkClient.writeData("/yzx","234");

        /**
         * 删除一个节点
         */
        zkClient.delete("/yzx1");
    }

    public static ZkClient getZkClient(){
        ZkClient zkClient = new ZkClient(ADDRESS,5000);
        return zkClient;
    }

}
