package com.oujiong.redisson.strategy.impl;


import com.oujiong.redisson.constant.GlobalConstant;
import com.oujiong.redisson.entity.RedissonProperties;
import com.oujiong.redisson.strategy.RedissonConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.config.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:  主从部署Redisson配置
 *       连接方式:  主节点,子节点,子节点
 *         格式为:  127.0.0.1:6379,127.0.0.1:6380,127.0.0.1:6381
 * @author xub
 * @date 2019/6/19 下午9:21
 */

@Slf4j
public class MasterslaveConfigImpl implements RedissonConfigService {

    @Override
    public Config createRedissonConfig(RedissonProperties redissonProperties) {
        Config config = new Config();
        try {
            String address = redissonProperties.getAddress();
            String password = redissonProperties.getPassword();
            int database = redissonProperties.getDatabase();
            String[] addrTokens = address.split(",");
            String masterNodeAddr = addrTokens[0];
            //设置主节点ip
            config.useMasterSlaveServers().setMasterAddress(masterNodeAddr);
            if (StringUtils.isNotBlank(password)) {
                config.useMasterSlaveServers().setPassword(password);
            }
            config.useMasterSlaveServers().setDatabase(database);
            //设置从节点，移除第一个节点，默认第一个为主节点
            List<String> slaveList = new ArrayList<>();
            for (String addrToken : addrTokens) {
                slaveList.add(GlobalConstant.REDIS_CONNECTION_PREFIX.getConstant_value() + addrToken);
            }
            slaveList.remove(0);

            config.useMasterSlaveServers().addSlaveAddress((String[]) slaveList.toArray());
            log.info("初始化[主从部署]方式Config,redisAddress:" + address);
        } catch (Exception e) {
            log.error("主从部署 Redisson init error", e);
            e.printStackTrace();
        }
        return config;
    }

}
