package com.yuanzx.demo.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * Created by yuanzx on 2019/4/2.
 */
public class DemoCuratorDataOperation {
    private static final String ADDRESS = "192.168.236.128:2181,192.168.236.129:2181";

    public static CuratorFramework getSession(){
        //fluent风格
        //创建会话的两种方式 normal
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(ADDRESS,5000,
                5000,new ExponentialBackoffRetry(1000,1));
        curatorFramework.start();
        return curatorFramework;
    }


    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = getSession();
        System.out.println("----------started");
        Thread.sleep(15000);

        /**
         * 节点监听，并且缓存数据
         */
        NodeCache nodeCache = new NodeCache(curatorFramework,"/node",false);
        nodeCache.start(true);
        nodeCache.getListenable().addListener(()->{
            System.out.println("ddd  +"+nodeCache.getCurrentData());
            System.out.println("节点发生了变化：path="+nodeCache.getCurrentData().getPath()+",value="+nodeCache.getCurrentData().getData());
        });

        /**
         * 监听路径下子节点的创建、删除、数据更新
         */
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework,"/node",true);
        pathChildrenCache.start();
        pathChildrenCache.getListenable().addListener(((curatorFramework1, pathChildrenCacheEvent) -> {
            System.out.println("子节点发生变化"+pathChildrenCacheEvent.getData().getPath());
        }));

        //创建驶入，如果父节点没有的话，也一起创建，持久的
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/node","333".getBytes());

        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/node/node1","333".getBytes());

        //变更数据
        curatorFramework.setData().forPath("/node/node1","333".getBytes());

        //删除数据
        curatorFramework.delete().forPath("/node/node1");

        //删除数据，如果有子目录，先删子目录
        curatorFramework.delete().deletingChildrenIfNeeded().forPath("/node");

        System.out.println("---------");
        System.in.read();
    }

}
