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
eurekaserver是服务管理平台，供服务调用方和服务提供方进行注册。
pull eurekaserver工程到本地，使用maven ```clean install```,运行该项目，浏览器访问 http://master:8761 即可查看master的eureka管理平台。

![eureka控制台](https://github.com/jiangyang118/springcloudalibaba-starter/blob/master/eureka-sample/pict/eureka%E6%8E%A7%E5%88%B6%E5%8F%B0.png)
下述方法启动一个主从架构的eurka管理平台，通过两个url均可访问。

```
#application.properties
#port改为8761，hostname改为master，defaultZone改为http://slave:8762/eureka启动新的application
spring.application.name=eurekaserver
server.port=8762
eureka.instance.hostname=slave
eureka.client.service-url.defaultZone=http://master:8761/eureka
```

# 3.启动order工程
order工程是服务提供方。
pull order工程到本地，使用maven ```clean install```,运行该项目

在eureka控制台可以查看具体细节，参见下图。
![启动order](https://github.com/jiangyang118/springcloudalibaba-starter/blob/master/eureka-sample/pict/启动order.png)

可以通过访问http://master:8100/order/create?name=a查看服务已经启动。

# 4.启动custom工程
custom工程是服务调用方。
pull custom工程到本地，使用maven ```clean install```,运行该项目

在eureka控制台可以查看具体细节，参见下图。
![启动custom](https://github.com/jiangyang118/springcloudalibaba-starter/blob/master/eureka-sample/pict/启动custom.png)

可以通过访问http://master:8200/test/test2或 http://master:8200/test/test查看服务已经启动。