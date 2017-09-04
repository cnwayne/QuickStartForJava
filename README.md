
### 必要条件
---

1. 必须确定机器中已经安装了JDK和Maven，以及一点点使用它们的知识；
2. WEB服务端需要有关系型数据库才能启动；
3. Java后台需要Hive、Elasticsearch、HBase。


### 运行样例代码
---

service-sample是一个样例程序，其中包含控制器、实体类、VO、持久层的样例代码。

在运行样例代码前，需要在publish-startup工程的pom.xml的dependences中加入如下代码
```xml
<dependency>
	<groupId>com.wayneleo.quickstart</groupId>
	<artifactId>service-sample</artifactId>
	<version>${project.version}</version>
</dependency>
```
如果遇到找不到service-sample-xxxx.jar的错误就按照一下顺序执行命令：
1. 在Service目录中执行`mvn clean install`命令；
2. 在Service->SampleCode目录中执行`mvn clean install`命令。


### 怎样创建子工程
---

在services中创建子工程时必须做一下操作：
1. 在services的pom.xml文件的`<modules></modules>`之间加入`<module>新工程目录名</module>`，否则在父工程执行`mvn clean package`时将不能编译新创建的子工程；
2. 在publish-startup的pom.xml文件的`<dependences></dependences>`之间加入对新创建的子工程的依赖。

