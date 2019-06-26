# JavaFxTemplate

## 使用说明
项目基于maven，请确保maven配置成功，否则，可能会出现问题

项目内置了commons-io的jar包，Jfoenix的jar包以及常用的工具类`JFxUtils`，具体使用请看注释！

原本是想将`JFxUtils`单独出来，打成一个jar包，不过，项目引用之后一起打成jar包，项目的jar包跑不起来，报找不到类的错误。

复制该项目，修改项目名，之后修改pom.xml中的 groupId,artifactId,version。
打开IDEA，Main.java的里面需要重新导入一下包名，之后即可成功运行。

**打jar包的时候，需要注意，把META-INF的路径设置在out目录下，否则，运行jar包会失败，如下图**

![](https://img2018.cnblogs.com/blog/1210268/201906/1210268-20190626204654321-305300461.png)

## 关于工具类使用说明
[JFxUtils使用](https://github.com/Stars-One/JavaFxTemplate/blob/master/JFxUtils_Use.md)