package wan.JavaFxTemplate.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author StarsOne
 * @date Create in  2019/6/21 0021 22:31
 * @description
 */
public class Intent {
    private Object o;
    private String fxmlName;
    private Stage stage;
    private Scene scene;
    private BaseController controller;
    private int width = 600, height = 400;
    private String title;
    private String iconName;
    private Map<String, Object> conveyData = new HashMap<>();


    public Map<String, Object> getConveyData() {
        return conveyData;
    }

    public void setConveyData(Map<String, Object> conveyData) {
        this.conveyData = conveyData;
    }

    public void addData(String key, int data) {
        conveyData.put(key, data);
    }

    public void addData(String key, boolean data) {
        conveyData.put(key, data);
    }

    public void addData(String key, String data) {
        conveyData.put(key, data);
    }

    public void addData(String key, List<?> data) {
        conveyData.put(key, data);
    }

    public void addData(String key, Object data) {
        conveyData.put(key, data);
    }

    public Integer getDataInteger(String key) {
        return (Integer) conveyData.get(key);
    }

    public boolean getDataBoolean(String key) {
        return (boolean) conveyData.get(key);
    }

    public String getDataString(String key) {
        return (String) conveyData.get(key);
    }

    public Object getDataObject(String key){
        return conveyData.get(key);
    }

    public <T> List<T> getDataList(String key, Class<T> clazz) {
        return ((List<T>) conveyData.get(key));
    }


    public Intent(Object o, String fxmlName, String title, String iconName,int width, int height ) {
        this.o = o;
        this.fxmlName = fxmlName;
        this.width = width;
        this.height = height;
        this.title = title;
        this.iconName = iconName;
    }

    public Intent(Object o, String fxmlName, String title,String iconName) {
        this.o = o;
        this.fxmlName = fxmlName;
        this.title = title;
        this.iconName = iconName;
    }
    public Intent(Object o, String fxmlName, String title) {
        this.o = o;
        this.fxmlName = fxmlName;
        this.title = title;
        this.iconName = "";
    }
    public Intent(Object o, String fxmlName, int width, int height, String title) {
        this.o = o;
        this.fxmlName = fxmlName;
        this.width = width;
        this.height = height;
        this.title = title;
        this.iconName = "";
    }

    public Intent(Object o, String fxmlName, int width, int height) {
        this.o = o;
        this.fxmlName = fxmlName;
        this.width = width;
        this.height = height;
        this.title = "";
        this.iconName = null;
    }

    public Intent(Object o, String fxmlName) {
        this.o = o;
        this.fxmlName = fxmlName;
    }

    public void start() throws IOException {
        FXMLLoader loader = new FXMLLoader();    // 创建对象
        loader.setBuilderFactory(new JavaFXBuilderFactory());    // 设置BuilderFactory
        loader.setLocation(MyUtils.getFxmlPath(o, fxmlName));
        InputStream inputStream = MyUtils.getFxmlFile(o, fxmlName);
        Object object = loader.load(inputStream);

        //        Parent root = FXMLLoader.load(MyUtils.getFxmlPath(this,"scene_main"));
        Parent root = (Parent) object;

        Stage primaryStage = new Stage();
        if (iconName != null) {
            primaryStage.getIcons().add(MyUtils.getImg(iconName));
        }

        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();
        Object controller = loader.getController();
        if (controller instanceof BaseController) {
            this.controller = ((BaseController) controller);
        }
        if (conveyData.size() != 0) {
            getController().setIntent(this);
        }
    }

    public String getFxmlName() {
        return fxmlName;
    }

    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return scene;
    }

    public BaseController getController() {
        return controller;
    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }

    public void setFxmlName(String fxmlName) {
        this.fxmlName = fxmlName;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }
}
