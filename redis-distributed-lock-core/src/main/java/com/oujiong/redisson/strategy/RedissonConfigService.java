package com.oujiong.redisson.strategy;


import com.oujiong.redisson.entity.RedissonProperties;
import org.redisson.config.Config;

/**
 * @Description: Redisson配置构建接口
 *
 * @author xub
 * @date 2019/6/20 下午3:35
 */
public interface RedissonConfigService {

    /**
     * 根据不同的Redis配置策略创建对应的Config
     * @param redissonProperties
     * @return Config
     */
    Config createRedissonConfig(RedissonProperties redissonProperties);
}
