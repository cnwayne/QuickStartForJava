
# 一、为什么要创建这个项目

快！快！！快！！！

# 二、怎么快速开始


## 0. 重中之重

首先必须确定你使用的机器已经连接到互联网而且已经安装了JDK和Maven，以及一点点使用它们的知识。使用如下命令确定(如果你看不懂这是干嘛的，就赶紧去学习一下Java和Maven)：

```shell
java -version
mvn -version
```

## 1. 先运行起来

下面这些命令会在以后的开发过程中被经常用到。

```shell
# 先进入最外层的父工程
cd ./QuickStart

# 将所有模块编译并安装到本地Maven库
mvn clean install -Dmaven.test.skip=true

# 进入发布模块
cd ./publish/publish-embed/

# 打包
mvn clean package

# 解压并运行
cd ./target
unzip ./quickstart-bootstrap-0.2.0-SNAPSHOT-embed.zip
cd ./quickstart-bootstrap-0.2.0-SNAPSHOT
./bin/startup.sh

# 监视运行日志
tail  -fn100   log/QuickStart.log

# 停止监视
Ctrl-C
```


## 2. 热更新日志级别

```shell
# 第一个参数是级别，从低到高分别是：DEBUG | INFO | WARN | ERROR | OFF
# 第二个参数是包名，不指定的话默认为根记录器( root )
./bin/logger.sh  DEBUG  root
```

## 3. 停止服务

```shell
./bin/shutdown.sh
```


# 三、运行样例代码

service-sample是一个样例程序，其中包含控制器、实体类、VO、持久层的样例代码。

在运行样例代码前，需要在 publish 工程的pom.xml的dependences中加入如下代码

```xml
<dependency>
	<groupId>com.wayneleo.quickstart</groupId>
	<artifactId>service-sample</artifactId>
	<version>${project.version}</version>
</dependency>
```

再次执行“_先运行起来_”中的代码。

__注意__ : 关于Dubbo的样例代码，不能直接通过上面的方式执行，因为在测试中发现“provider”和“consumer”在同一个进程中启动时，“consumer”会比“provider”先启动，这将导致“consumer”因为找不到“provider”而报错(在当前版本的dubbo中，只有“provider”在“consumer”之前启动好时才会注入代理对象，否则将入住空，暂时没有找到办法让“consumer”重新注入“provider”)。


# 四、关于子工程


## 1. 什么时候需要创建子工程

理想状态下，一个模块只针对一个业务主题，并且仅通过接口解决对其它模块的依赖。简单理解这句话就是当我们写产品模块时就创建一个产品的模块(如ProductModule)，当我们写购物车的时候就创建(如CartModule)；因为要购物车要展示产品信息，所以购物车模块依赖产品模块。

把上面的场景放在这个框架中，就是创建四个子工程：ProductAPI、ProductModule、CartAPI、CartModule；我将其简化为三个子工程：Service-API、Service-Product、Service-Cart。

Service-API存放所有模块的服务接口(service层)、实体类、VO类、异常类、等一系列需要暴露给其它模块的部分，但不包括接口的具体实现。

需要重点说明的是，业务逻辑应该放在服务层中，控制层仅仅是对服务层的HTTP封装；随着项目规模增大，我们需要考虑并为以后将模块化升级为微服务预留扩展空间(目前已在框架中集成Dubbo)。


## 2. 创建子工程

在 services 中创建子工程后必须做以下操作：

1. 在 services 的 pom.xml 文件的`<modules></modules>`之间加入`<module>新工程目录名</module>`，否则在父工程执行`mvn clean package`时将不能编译新创建的子工程；

2. 在 publish 的 pom.xml 文件的`<dependences></dependences>`之间加入对新创建的子工程的依赖。

3. 再次执行“_先运行起来_”中的代码。


