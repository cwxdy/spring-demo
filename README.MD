#框架介绍
####所用到的环境及中间件：
#####1、JDK:1.8
#####2、springBoot 2.0.2
#####3、spring 5.0.6
#####4、redis 缓存
#####5、jackson 2.9.8
#####6、mysql  关系型数据库
#####7、activeMQ   消息队列
#####8、swagger2   api框架
####框架基本结构：
##### springBoot+srping-data-jpa+mysql
##### base:基础的封装类
##### config:基础的配置类
##### controller:控制层
##### cronJon:定时任务
##### dao:持久层
##### dto:报文
##### entity:实体类，表
##### mq:消息中间件
##### serviceImp:业务层
##### utils:工具包
####框架基本功能：
#####1、基本的增删改查
#####2、RedisUtil已封装，可使用
#####3、MQ已配置，可使用
#####4、JdbcTemplate已封装，可使用，用于执行原生SQL
#####5、Session分布式实现，会对在线用户过期认证
#####6、基于Session共享，通过SessionUtil直接获取当前登录用户信息
#####7、数据源主从配置，目前只配置了主库
#####8、swagger2  已整合
#####9、统一的报文处理，异常处理
#####10、纳入了非常丰富的工具类 hutool  https://github.com/changyu3590/hutool.git
#####11、纳入了Excel工具类  https://github.com/changyu3590/ExcelUtil.git
