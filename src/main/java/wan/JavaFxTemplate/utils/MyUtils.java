package wan.JavaFxTemplate.utils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.stage.Stage;
import wan.Main;

/**
 * @author StarsOne
 * @date Create in  2019/6/5 0005 14:01
 * @description
 */
public class MyUtils {
    private static Class main = Main.class;

    /**
     * 创建stage并显示
     *
     * @param o            this 传入主界面Main
     * @param primaryStage 舞台，为空的话之后会创建一个新的；如果是初始界面则直接传入对应的primaryStage
     * @param title        标题
     * @param fxmlName     新窗口对应的fxml文件名，不需要扩展名
     * @param iconName     图标名字，需要扩展名
     * @param width        宽度
     * @param height       高度
     * @throws IOException
     */
    public static void showMainStage(Object o, Stage primaryStage, String title, String fxmlName, String iconName, int width, int height) throws IOException {
        main = o.getClass();
        FXMLLoader loader = new FXMLLoader();    // 创建对象
        loader.setBuilderFactory(new JavaFXBuilderFactory());    // 设置BuilderFactory
        loader.setLocation(getFxmlPath(fxmlName));
        InputStream inputStream = getFxmlFile(fxmlName);
        Object object = loader.load(inputStream);
        Parent root = (Parent) object;
        if (iconName != null) {
            Image image = getImg(iconName);
            primaryStage.getIcons().add(image);
        }
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();
    }

    /**
     * 设置每次点击链接之后，链接不会变成灰色以及点击之后执行的操作
     *
     * @param hyperlink hyperlink
     * @param hander    点击监听器，点击hyperlink之后进行的操作
     */
    public static void setLinkAction(Hyperlink hyperlink, LinkActionHander hander) {

        hyperlink.setBorder(Border.EMPTY);
        hyperlink.setOnMouseClicked(event -> {
            hander.setAction();
            hyperlink.setVisited(false);
        });
    }

    /**
     * 设置链接可以自动跳转资源管理器，浏览器或者打开QQ添加好友界面（保证hyperlink的文字是正确的目录路径，网址，QQ号）
     * QQ添加好友测试不通过，还有bug
     *
     * @param hyperlink hyperlink
     */
    public static void setLinkAutoAction(Hyperlink hyperlink) {
        String text = hyperlink.getText();
        //使用默认的浏览器打开
        if (text.contains("www") || text.contains("com") || text.contains(".")) {
            try {
                Desktop.getDesktop().browse(new URI(text));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        } else if (text.contains(File.separator)) {
            try {
                Desktop.getDesktop().open(new File(text));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //打开QQ
            try {
                Desktop.getDesktop().browse(new URI("http://wpa.qq.com/msgrd?v=3&amp;uin=" + text + "&amp;site=qq&amp;menu=yes"));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭当前窗口
     *
     * @param control 一个控件
     */
    public static void closeWindow(Control control) {
        Stage stage = (Stage) control.getScene().getWindow();
        stage.close();
    }

    /**
     * 获得图片文件
     *
     * @param fileName 图片名+扩展名
     * @return 图片image
     */
    public static Image getImg(String fileName) {
        InputStream resourceAsStream = main.getResourceAsStream("img/" + fileName);
        if (resourceAsStream != null) {
            return new Image(resourceAsStream);
        }
        return null;
    }


    /**
     * 获得fxml文件路径
     *
     * @param fileName 文件名
     * @return
     */
    public static URL getFxmlPath(String fileName) {
        return main.getResource("fxml/" + fileName + ".fxml");
    }

    /**
     * 获得输出流
     *
     * @param fileName
     * @return
     */
    public static InputStream getFxmlFile(String fileName) {
        return main.getResourceAsStream("fxml/" + fileName + ".fxml");
    }

    /**
     * 获得当前jar包所在的文件夹
     * @return 路径
     */
    public static String getCurrentPath() {
        String decode = null;
        try {
            decode = URLDecoder.decode(main.getProtectionDomain()
                    .getCodeSource().getLocation().getFile(), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (decode != null) {
            return new File(decode).getParent();
        } else {
            return null;
        }

    }

    public interface LinkActionHander {
        void setAction();
    }
}
