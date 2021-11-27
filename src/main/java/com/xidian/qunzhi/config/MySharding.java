package com.xidian.qunzhi.config;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

public class MySharding implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> shardingValue) {
        Long id=shardingValue.getValue();
        //根据hashcode进行取模来确定存在哪个数据库
         long mode= id %collection.size();
        mode=Math.abs(mode);
        String[] strings=collection.toArray(new String[0]);
        System.out.println(strings[0]+"---------"+strings[1]);
        System.out.println("mode="+mode);
        return strings[(int)mode];
    }
}
