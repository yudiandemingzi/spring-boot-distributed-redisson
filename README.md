# Redisson实现分布式锁

有关Redisson实现分布式锁前面写了三篇博客作为该项目落地的铺垫。

1、[Redisson实现分布式锁(1)---原理](https://www.cnblogs.com/qdhxhz/p/11046905.html)

2、[Redisson实现分布式锁(2)—RedissonLock](https://www.cnblogs.com/qdhxhz/p/11055426.html)

3、[Redisson实现分布式锁(3)—项目落地实现](https://www.cnblogs.com/qdhxhz/p/11059200.html)

该项目可以直接运用于实际开发中，作为分布式锁使用。


## 一、项目概述

#### 1、技术架构

项目总体技术选型

```
SpringBoot2.1.5 + Maven3.5.4 + Redisson3.5.4 + lombok(插件)
```

#### 2、加锁方式

该项目支持 `自定义注解加锁` 和 ` 常规加锁` 两种模式

**自定义注解加锁**

```java
 @DistributedLock(value="goods", leaseTime=5)
  public String lockDecreaseStock(){
    //业务逻辑
  }
```

**常规加锁**

```java
 //1、加锁
 redissonLock.lock("redisson", 10);
 //2、业务逻辑
 //3、解锁
 redissonLock.unlock("redisson");
```

#### 3、Redis部署方式

该项目支持四种Redis部署方式

```
1、单机模式部署
2、集群模式部署
3、主从模式部署
4、哨兵模式部署
```

该项目已经实现支持上面四种模式，你要采用哪种只需要修改配置文件`application.properties`，项目代码不需要做任何修改。

#### 4、项目整体结构

```makefile
redis-distributed-lock-core # 核心实现
|
---src
      |
      ---com.oujiong.redisson
                           |# 通过注解方式 实现分布式锁
                           ---annotation
                           |# 配置类实例化RedissonLock
                           ---config
                           |# 放置常量信息
                           ---constant
                           |# 读取application.properties信息后，封装到实体
                           ---entity    
                           |# 支持单机、集群、主从、哨兵 代码实现
                           ---strategy

redis-distributed-lock-web-test # 针对上面实现类的测试类
|
---src
      |
      ---java
            |
            ---com.oujiong.controller
                                 |# 测试 基于注解方式实现分布式锁
                                 ---AnnotatinLockController.java
                                 |# 测试 基于常规方式实现分布式锁
                                 ---LockController.java
      ---resources                
           | # 配置端口号 连接redis信息(如果确定部署类型，那么将连接信息放到core项目中)
            ---application.properties
```

<br>


