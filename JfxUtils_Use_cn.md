# JFxUtils
## 介绍
这是一个JFX的工具库，Intent可以简单地实现打开一个新窗口并传递数据，DialogBuilder可以简单地生成对话框,MyUtils有些常用的功能
## 使用
- MyUtils 封装了一些常用的的方法
- Intent与BaseController 打开新窗口，并传递数据
- DialogBuilder 基于Jfoenix，快速生成material Design风格的对话框
### Intent的使用
使用Intent，可以打开一个新窗口，还能传递数据
### 如何使用？
#### 打开一个新窗口
步骤：new一个Intent，调用start方法即可

下面是几种不同的构造方法

- Intent(String fxmlName)
- Intent(String fxmlName, String title)
- Intent(String fxmlName, String title,String iconName) 
- Intent(String fxmlName, int width, int height)
- Intent(String fxmlName, int width, int height, String title)
- Intent(String fxmlName, int width, int height, String title, String iconName)
 
**PS:**
- `fxmlName` fxml的名字，不需要写扩展名	
- `iconName` 图标名，需要写扩展名

**例子：**
- 简单例子
```
//在一个controller中
//fxml名字为test
//默认宽高 600*400 
Intent intent = new Intent("test");
//打开新窗口
intent.start();
```
- 设置标题
```
Intent intent = new Intent("test","hello world");
intent.start();
```
- 设置宽高
```
Intent intent = new Intent("test",800,400);
intent.start();
```

#### 打开新窗口并传递数据
本工具中，有一个`BaseController`，想要传递数据，就得让`controller`继承这个`BaseController`，之后，new一个`intent`,使用`addData`方法传入数据

可以传递的数据类型有`int`,`boolean`,`String`,`List`,`Object`
- addData(String key, int data)
- addData(String key, boolean data)
- addData(String key, String data)
- addData(String key, List<?> data)
- addData(String key, Object data)

在controller中getIntent得到intent的实例，之后通过以下方法获得数据
- getDataInteger(String key)
- getDataBoolean(String key)
- getDataString(String key)
- getDataList(String key,Class<T> clazz)
- getDataObject(String key) **需要转型**

**例子:**
```
//某个controller中打开新窗口，并传递list数据
Intent intent = new Intent("test",800,400);
intent.addData("list",studentlist);
intent.start();

//test对应的controller，接收list数据
package wan.dormsystem.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import wan.dormsystem.bean.Student;

/**
 * @author StarsOne
 * @date Create in  2019/6/21 0021 16:44
 * @description
 */
public class TestController extends BaseController {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
		//这里一定要有判断，不然会有空指针异常
        if (getIntent() != null) {
            List<Student> list = getIntent().getDataList("list", Student.class);
            for (Student student : list) {
                System.out.println(student.toString());
            }
        }
    }
}
```
### DialogBuilder
[DialogBuilder使用](https://www.cnblogs.com/kexing/p/10989323.html)
### MyUtils
- closeWindow(Control control) 关闭窗口
- Image getImg(String fileName) 获得图片
- void setLinkAutoAction(Hyperlink hyperlink) 设置链接自动跳转
- void setLinkAction(Hyperlink hyperlink, LinkActionHander hander) 设置链接点击事件
- void setControlBackground(Control control,String color) 设置控件的背景颜色
- showToast(Pane pane, String text) 显示Toast
更多使用详情，可以直接去类中查看