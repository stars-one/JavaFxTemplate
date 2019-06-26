package wan.JavaFxTemplate.utils;

import javafx.fxml.Initializable;

/**
 * @author StarsOne
 * @date Create in  2019/6/21 0021 16:41
 * @description
 */
public abstract class BaseController implements Initializable {
    private Intent intent;

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
        initialize(null,null);
    }
}
