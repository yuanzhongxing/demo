package com.yuanzx.demo.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.zookeeper.CreateMode;

import java.util.List;
import java.util.TreeSet;

/**
 * Created by yuanzx on 2019/4/4.
 */
public class DemoSharedLock {

    private static final String LOCK_PARRENT_NODE = "/SHARED_LOCK";

    /**
     * 获取锁
     */
    public static void getLock(){
        try {
            CuratorFramework curatorFramework = DemoCuratorCreateSession.getSession();
            PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework,LOCK_PARRENT_NODE,true);
            pathChildrenCache.start();
            String id = curatorFramework.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(LOCK_PARRENT_NODE+"/");
            System.out.println(id);

            /**
             * 对子节点进行监听
             */
            pathChildrenCache.getListenable().addListener((curatorFramework1, curatorEvent) -> {
                List<ChildData> list = pathChildrenCache.getCurrentData();
                TreeSet<String> set = new TreeSet<>();
                for (ChildData childData : list){
                    set.add(new String(childData.getPath()));
                }

                String last = "";
                for (String key : set){
                    if(key.equals(id)){
                        if(last.equals("")){
                            System.out.println(id+"------获取锁-----------------");
                            Thread.sleep(5000);
                            DemoSharedLock.unlock(id,curatorFramework);
                            System.out.println(id+"------释放锁-----------------");
                        }else{
                            NodeCache nodeCache = new NodeCache(curatorFramework,last,true);
                            nodeCache.start();
                            nodeCache.getListenable().addListener(() ->{
                                if(nodeCache.getCurrentData() == null){
                                    System.out.println(id+"------获取锁-----------------");
                                    Thread.sleep(5000);
                                    DemoSharedLock.unlock(id,curatorFramework);
                                    System.out.println(id+"------释放锁-----------------");
                                }
                            });
                        }
                        break;
                    }
                    last = key;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放锁
     */
    public static void unlock(String path,CuratorFramework cf){
        try {
            cf.delete().forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        DemoSharedLock.getLock();
                        Thread.sleep(5*60*1000);
                        System.out.println("线程结束");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}
