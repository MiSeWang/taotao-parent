package com.mrlv.taotao.jedis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.HashSet;

public class JedisTest {

    /**
     * 单机版
     */
    @Test
    public void testJedisSingle(){
        //创建一个jedis的对象
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //调用jedis对象的方法，方法名和redis一样
        jedis.set("one", "初号机");
        //关闭jedis
        String one = jedis.get("one");
        System.out.println(one);
        jedis.close();
    }

    /**
     * 使用连接池
     */
    @Test
    public void testJedisPool(){
        //创建jedis的连接池对象
        JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
        //从连接池中获得Jedis对象
        Jedis jedis = jedisPool.getResource();
        jedis.set("two", "二号机");
        String two = jedis.get("two");
        System.out.println(two);
        //关闭jedis对象
        jedis.close();
        jedisPool.close();
    }

    /**
     * 集群版测试
     */
//    @Test
    public void testJedisCluster(){
        //创建节点集合
        HashSet<HostAndPort> nodes = new HashSet<>();
        //添加节点
        nodes.add(new HostAndPort("127.0.0.1", 6379));
        nodes.add(new HostAndPort("127.0.0.1", 6379));
        nodes.add(new HostAndPort("127.0.0.1", 6379));
        nodes.add(new HostAndPort("127.0.0.1", 6379));

        JedisCluster cluster = new JedisCluster(nodes);

        cluster.set("three", "三号机");

        String three = cluster.get("three");
        System.out.println(three);
        cluster.close();
    }
}

