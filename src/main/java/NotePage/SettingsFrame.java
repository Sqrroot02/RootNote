package NotePage;

import NotePage.Tabs.ElementsTab;
import NotePage.Tabs.TableTab;
import NotePage.Tabs.WriteTab;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;

public class SettingsFrame extends HBox {
    private TabPane pane;

    private WriteTab writeTab;
    private ElementsTab elementsTab;
    private TableTab tableTab;
    private Tab drawTab;
    private Tab layoutTab;
    private Tab mediaTab;

    public WriteTab getWriteTab() {
        return writeTab;
    }
    public ElementsTab getElementsTab() { return elementsTab; }
    public TableTab getTableTab() {
        return tableTab;
    }

    public SettingsFrame(){
        InitControls();
    }

    private void InitControls(){
        pane = new TabPane();
        pane.setMinHeight(80);
        InitWriteTab();
        initElementsTab();
        initTableTab();
        getChildren().add(pane);
    }

    private void InitWriteTab(){
        writeTab = new WriteTab();
        pane.getTabs().add(writeTab);
    }

    private void initElementsTab(){
        elementsTab = new ElementsTab();
        pane.getTabs().add(elementsTab);
    }

    private void initTableTab(){
        tableTab = new TableTab();
        pane.getTabs().add(tableTab);
    }

}
