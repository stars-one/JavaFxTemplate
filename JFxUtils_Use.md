# JFxUtils
[中文文档](https://github.com/Stars-One/JFXUtils/blob/master/readme_cn.md)
## Introduction
This is a library for JavaFx.
## Use
This library is use with JavaFxTemplate.Please download the JavaFxTemplate if you want to use it.
---

[JavaFxTemplate](https://github.com/Stars-One/JavaFxTemplate)

**Following steps is finished in JavaFxTemplate!! You can quickly open your JavaFx project with JavaFxTemplate!**

At first,download the release zip.
There is a jar in the zip file.
Add the jar as your project library.
### Intent
You can use this to open new window.Also,you can let it give the data to the controller java file of new window.
### How to Use Intent?
#### Use Intent to open new windows
First,new a instance of Intent.And then invoke the method called start.
there are six construction method for Intent.

- Intent(Object o, String fxmlName)
- Intent(Object o, String fxmlName, String title)
- Intent(Object o, String fxmlName, String title,String iconName) 
- Intent(Object o, String fxmlName, int width, int height)
- Intent(Object o, String fxmlName, int width, int height, String title)
- Intent(Object o, String fxmlName, int width, int height, String title, String iconName)
 
**PS:**
- `o` is current controller	
- `fxmlName` is the name of fxml,this don't write the extensions	
- `iconName` is the name of icon,remeber write the extensions	

**Example:**
- simple example
```
//this is in a controller java file
//have a fxml file called "test".
//default width is 600px, height is 400px;
Intent intent = new Intent(this,"test");
//open the new window
intent.start();
```
- set the title
```
Intent intent = new Intent(this,"test","hello world");
intent.start();
```
- set the width and height
```
Intent intent = new Intent(this,"test",800,400);
intent.start();
```

#### Use Intent to open new windows and transmit some data
There is a Java File called `BaseController` in the library.First,let the controller extend `BaseController`.And then,new a instance of Intent.Invoke the method named `addData`.The last,invoke the start method.

You can add the data whose type is `integer`,`boolean`,`Object`,`List`,`String` into intent.
- addData(String key, int data)
- addData(String key, boolean data)
- addData(String key, String data)
- addData(String key, List<?> data)
- addData(String key, Object data)

You can choose following method to get your data.remeber the type of your data.
- getDataInteger(String key)
- getDataBoolean(String key)
- getDataString(String key)
- getDataList(String key,Class<T> clazz)
- getDataObject(String key) **should be cast the type**

**Example:**
- get the list data

```
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
		//must be judge the intent is not null,else throw a pointnull exception
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
A way for JavaFx and based on Jfoenix to generate a diglog

### How to Use DialogBuilder?
- this is generate a diglog to show user the tips of login success
```
//tfOutPath is a controller of current fxml file
new DialogBuilder(tfOutPath).setTitle("tips").setMessage("login success").setNegativeBtn("ok").create();
```

- this is generate a diglog with two button
```
new DialogBuilder(tfOutPath).setNegativeBtn("cancel", new DialogBuilder.OnClickListener() {
            @Override
            public void onClick() {
                //action when user clicks the cancel button
            }
        }).setPositiveBtn("ok", new DialogBuilder.OnClickListener() {
            @Override
            public void onClick() {
                //action when user clicks the confirm button
            }
        }).setTitle("tips").setMessage("hello world").create();
```
- also you can change the text color of cancel button and confirm button
```
new DialogBuilder(startBtn).setTitle("提示").setMessage("hello world").setPositiveBtn("确定", "#ff3333").setNegativeBtn("取消", "#00ff00").create();
```

