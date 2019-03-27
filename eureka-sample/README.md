# eureka-sample
一个例子快速熟悉eureka
运行环境：
1. windows10
2. jdk8 
3. sts 3.9.5.RELEASE


# 1.修改本地hosts文件

```
#路径 c:\Windows\System32\drivers\etc\hosts
127.0.0.1 master
127.0.0.1 slave
127.0.0.1 slave2
```
 
# 2.启动eurekaserver工程
pull eurekaserver工程到本地，使用maven ```clean install```,运行该项目，浏览器访问http://master:8761即可查看master的eureka管理平台。


下述方法启动一个主从架构的eurka管理平台，通过两个url均可访问。

```
#application.properties，端口改为8761，hostname改为master，defaultZone改为http://slave:8762/eureka启动新的application

spring.application.name=eurekaserver
server.port=8762
eureka.instance.hostname=slave
eureka.client.service-url.defaultZone=http://master:8761/eureka
```