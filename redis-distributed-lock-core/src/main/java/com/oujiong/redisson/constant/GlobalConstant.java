package com.oujiong.redisson.constant;


/**
 * @author xub
 * @Description: 全局常量枚举 用来拼接完整的URL
 * @date 2019/6/19 下午9:09
 */
public enum GlobalConstant {

    REDIS_CONNECTION_PREFIX("redis://", "Redis地址配置前缀");

    private final String constantValue;
    private final String constantDesc;

    GlobalConstant(String constantValue, String constantDesc) {
        this.constantValue = constantValue;
        this.constantDesc = constantDesc;
    }

    public String getConstantValue() {
        return constantValue;
    }

    public String getConstantDesc() {
        return constantDesc;
    }
}
