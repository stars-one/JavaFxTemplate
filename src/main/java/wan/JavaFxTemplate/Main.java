package wan.JavaFxTemplate;

import javafx.application.Application;
import javafx.stage.Stage;
import wan.JavaFxTemplate.utils.MyUtils;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        MyUtils.createAndShowStage(this,primaryStage,"测试模板","scene_main",null,600,400);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
