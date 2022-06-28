package NotePage;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;

public class SettingsFrame extends HBox {
    private TabPane pane;

    private WriteTab writeTab;
    private Tab drawTab;
    private Tab layoutTab;
    private Tab mediaTab;

    public WriteTab getWriteTab() {
        return writeTab;
    }

    public SettingsFrame(){
        InitControls();
    }

    private void InitControls(){
        pane = new TabPane();
        pane.setMinHeight(80);
        InitWriteTab();
    }

    private void InitWriteTab(){
        writeTab = new WriteTab();
        pane.getTabs().add(writeTab);
        getChildren().add(pane);
    }

}
