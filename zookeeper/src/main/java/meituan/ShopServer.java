package meituan;

import org.apache.zookeeper.*;

public class ShopServer {

    //    zookeeper的端口和集群ip
    private String connStr = "192.168.80.130:2181,192.168.80.135:2181,192.168.80.136:2181";
    //    session超时时间
    private int sessionTimeout = 60*1000;
    //    zookeeper对象
    ZooKeeper zkClient;



    public ZooKeeper createLink() throws Exception{

        ZooKeeper zooKeeper = new ZooKeeper(connStr, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
        return zooKeeper;
    }

//    注册到zookeeper
    public void register(String shopName) throws InterruptedException, KeeperException {
//        最后一个参数短暂临时有序
        String s = zkClient.create("/meituan", shopName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("开始营业"+s);
    }

    public static void main(String[] args) throws Exception {
//        开饭店
        ShopServer shopServer = new ShopServer();
//        连接zookeeper集群
//         = shopServer.createLink();
//        将服务节点注册到zookeeper集群

//        业务逻辑处理


    }
}
