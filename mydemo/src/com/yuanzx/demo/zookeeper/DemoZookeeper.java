package com.yuanzx.demo.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by yuanzx on 2019/3/29.
 */
public class DemoZookeeper {

    private static final String ADDRESS = "192.168.236.128:2181,192.168.236.129:2181,192.168.236.130:2181,192.168.236.131:2181";

    private static Stat stat = new Stat();

    private static ZooKeeper zooKeeper = null;
    private static final CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {

        ZooKeeper zooKeeper = DemoZookeeper.createZookeeperCon();

        /**
         * 判断路径/yuanzx 是否存在，并且进行监听
         */
        stat = zooKeeper.exists("/yuanzx",true);
        if(stat==null){
            /**
             * 创建一个节点
             * /yuanzx = 路径
             * 123 = 值
             * ZooDefs.Ids.OPEN_ACL_UNSAFE = 权限为开放的
             * CreateMode.EPHEMERAL = 临时节点，本次会话断开，数据就会清除
             */
            zooKeeper.create("/yuanzx","123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            Thread.sleep(2000);
        }

        /**
         * 更新一条数据，version = -1 就是不设置版本，也就是不乐观锁
         */
        zooKeeper.setData("/yuanzx","234".getBytes(),-1);
        Thread.sleep(2000);

        zooKeeper.setData("/yuanzx","345".getBytes(),-1);
        Thread.sleep(2000);

        zooKeeper.delete("/yuanzx",-1);
        Thread.sleep(2000);


        /**
         * 创建一个持久化节点
         */
        stat = zooKeeper.exists("/yzx",true);
        zooKeeper.create("/yzx","123".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        Thread.sleep(2000);

        /**
         * 为/yzx1 创建一个子节点
         */
        zooKeeper.create("/yzx/yzx-1","234".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        Thread.sleep(2000);

        zooKeeper.setData("/yzx/yzx-1","555".getBytes(),-1);

        /**
         * 获取所有子节点
         */
        List<String> nodes = zooKeeper.getChildren("/yzx",true);
        System.out.println(nodes);

        /**
         * 删除子节点 和yzx节点
         */
        zooKeeper.delete("/yzx/yzx-1",-1);
        zooKeeper.delete("/yzx",-1);
        Thread.sleep(2000);



    }


    /**
     * 创建连接
     * @return
     */
    public static ZooKeeper createZookeeperCon(){
        try {
            zooKeeper = new ZooKeeper(ADDRESS, 5000, new Watcher() {
                public void process(WatchedEvent watchedEvent) {
                    try {
                        DemoZookeeper.watcher(watchedEvent);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    }
                }
            });
            countDownLatch.await();
            return zooKeeper;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 监听 - 示例
     * @param watchedEvent
     * @throws InterruptedException
     * @throws KeeperException
     */
    public static void watcher(WatchedEvent watchedEvent) throws InterruptedException, KeeperException {
        System.out.println(watchedEvent.getType());
        if(watchedEvent.getState().equals(Watcher.Event.KeeperState.SyncConnected)){
            if(Watcher.Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()){
                System.out.println(watchedEvent.getState());
                countDownLatch.countDown();
            }else if(watchedEvent.getType().equals(Watcher.Event.EventType.NodeCreated)){//创建数据监听事件
                /**
                 * 数据被创建监听事件，wathch=true表示继续监听，否则监听这一次就会失效
                 */
                System.out.println(String.format("-->Date is create,path=%s,value=%s",watchedEvent.getPath(),new String(zooKeeper.getData(watchedEvent.getPath(),true,stat))));
            }else if(watchedEvent.getType().equals(Watcher.Event.EventType.NodeDataChanged)){//数据变化监听事件
                /**
                 * 数据改变监听事件
                 */
                System.out.println(String.format("-->Data is change,path=%s,value=%s",watchedEvent.getPath(),
                        new String(zooKeeper.getData(watchedEvent.getPath(),true,stat))));
            }else if(watchedEvent.getType().equals(Watcher.Event.EventType.NodeChildrenChanged)){//子路径数据变化监听事件
                System.out.println(String.format("-->(Chile node) Data is change,path=%s,value=%s",watchedEvent.getPath(),
                        new String(zooKeeper.getData(watchedEvent.getPath(),true,stat))));
            }else if(watchedEvent.getType().equals(Watcher.Event.EventType.NodeDeleted)){//删除数据监听事件
                /**
                 * 数据删除监听事件
                 */
                System.out.println(String.format("-->Data is delete,path=%s",watchedEvent.getPath()));

            }
        }

    }

}
