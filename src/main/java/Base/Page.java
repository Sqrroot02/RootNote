package Base;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Page {
    public Scene getScene(){
        return usedScene;
    }
    public void setUsedScene(Scene scene){
        usedScene = scene;
    }
    private Scene usedScene;
}
