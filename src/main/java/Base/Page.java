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

    private Stage usedStage;
    public void setStage(Stage stage){
        usedStage = stage;
    }
    public Stage getUsedStage(){
        return  usedStage;
    }
    private Scene usedScene;
}
