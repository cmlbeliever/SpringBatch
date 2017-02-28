# 工程说明
------
项目将SpringBatch整合到SpringBoot中，去除配置化信息，基于约定优于配置的思想实现。
涉及到的主要框架有：SpringBoot，SpringBatch,Mybatis,DBCP ...

**/db目录下的sql为当前项目所用到的ddl**

**项目运行参数说明**

 - batchName(required) ： 该batch名称，例如运行bat00X，则参数为:batchName=bat00X
 - logging.config(optional) : log配置所在的位置，通常本地运行时不需要关心此参数。在发布到开发机或本番时需要传入。格式：logging.config=${filePath}，例如运行bat00X,则参数为：logging.config=classpath:logback-bat00X.xml

**src/main/java工程结构说明**

 -  com.cml.learning.framework 为框架基础配置包，通常情况不做修改
 - com.cml.learning.module 为每个batch对应的模块信息，需要开发者自己实现

**src/main/resource工程结构说明**

 - application.properties 全局配置properties
 - logback.xml 默认的log配
 -  config/jdbc-r.properties 只读db配置
 -  config/jdbc-rw.properties 读写db配置
 -  config/log 每个batch对应的log配置
 -  config/module 每个batch对应的properties配置信息


  
**项目约定说明**

 - 每个batch类都需要添加注解@BatchAnnotation用于标识batch运行环境
 - 每个batch对应有自己的Configuration，用于配置SpringBatch运行信息

 -  所有batch统一在包(com.cml.learning.module)下，根据batch名称定义包名
> 如: com.cml.learning.module.batch001

 -  每个batch继承com.cml.learning.framework.module.BaseModule并且实现接口 com.cml.learning.framework.constant.ModuleConst
 - 只读Mapper需要继承com.cml.learning.framework.mybatis.marker.ReadMapper，并且Mapper名称以**ReadMaper结尾**
 - 只读Mapper对应的xml以**read.sql.xml结尾**，且名称为当前batch名，例如bat001的只读xml为：bat001.read.sql.xml
 - 读写Mapper需要继承com.cml.learning.framework.mybatis.marker.WriteMapper,并且Mapper名称以**WriteMapper结尾**
 - 读写Mapper对应的xml以**write.sql.xml结尾**,并且名称为当前batch名，，例如bat001的读写xml为：bat001.write.sql.xml
 - DB所对应的bean在每个batch下的beans包下，如bat001 对应的bean在包:com.cml.learning.module.bat001.beans下，Mybatis 会自动扫描此包下对应的bean
 - processor,reader,writer都有对应的基类，每个batch下对应的模块都需要继承对应的基类。
 
# 开发问题
  - 多数据源下启动报错问题总结地址：http://blog.csdn.net/cml_blog/article/details/58604485




