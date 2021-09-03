package test;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Test1 {

//    zookeeper的端口和集群ip
    private String connStr = "192.168.80.130:2181,192.168.80.135:2181,192.168.80.136:2181";
//    session超时时间
    private int sessionTimeout = 60*1000;
//    zookeeper对象
    ZooKeeper zkClient;

    @BeforeEach
    public void init() throws Exception{

        zkClient = new ZooKeeper(connStr, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("得到监听反馈");
//                监听事件的类型
                System.out.println(watchedEvent.getType());
            }
        });
    }

//    创建结点
    @Test
    public void createNode() throws InterruptedException, KeeperException {
        String str = zkClient.create("/China","beijing".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("创建节点"+str);
    }

//    获取节点的值
    @Test
    public void getNodeData() throws Exception{
        byte[] data = zkClient.getData("/China", false, new Stat());
        String s = new String(data);

    }

//    修改节点的值
    @Test
    public void update() throws Exception {
        Stat setData = zkClient.setData("/China", "bejing1".getBytes(), 0);
    }

//    删除节点
    @Test
    public void delete() throws Exception {
        zkClient.delete("/China",1);
    }

//    获取子节点
    @Test
    public void getChildren() throws Exception{
        List<String> list = zkClient.getChildren("/China", false);
    }
//    监听根节点
    @Test
    public void watch() throws Exception {
        List<String> children = zkClient.getChildren("/", true);

    }

//    判断节点是否存在
    @Test
    public void exists() throws Exception {
        Stat exists = zkClient.exists("/China", true);
    }

}
