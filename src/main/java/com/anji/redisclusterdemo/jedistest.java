package com.anji.redisclusterdemo;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Description:
 * author: chenqiang
 * date: 2018/6/20 10:38
 */
public class jedistest {
    public static void main(String[] args) {
        JedisPoolConfig poolConfig=new JedisPoolConfig();
        poolConfig.setMaxTotal(1);
        poolConfig.setMaxIdle(1);

        poolConfig.setMaxWaitMillis(1000);

        Set<HostAndPort> nodes=new LinkedHashSet<HostAndPort>();
        nodes.add(new HostAndPort("192.168.181.100",7000));
        nodes.add(new HostAndPort("192.168.181.100",7001));
        nodes.add(new HostAndPort("192.168.181.100",7002));
        nodes.add(new HostAndPort("192.168.181.101",7003));
        nodes.add(new HostAndPort("192.168.181.101",7004));
        nodes.add(new HostAndPort("192.168.181.101",7005));

        JedisCluster cluster=new JedisCluster(nodes,poolConfig);
        String name=cluster.get("name");
        System.out.println(name);

        cluster.set("AK","47");
        System.out.println(cluster.get("AK"));

        try{
            cluster.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
