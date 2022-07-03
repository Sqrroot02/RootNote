package NotePage.Tabs;

import Base.Enumerations.BorderStyle;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.EnumSet;

public class TableTab extends Tab {

    private final int fixPrefWidth = 50;
    private final int fixPrefHeight = 30;

    public TableTab() {
        initComponents();
        setText("Table");
    }

    private GridPane container;

    public GridPane getContainer() {
        return container;
    }

    private Button addRowButton;

    public Button getAddRowButton() {
        return addRowButton;
    }

    private Button addColumnButton;

    public Button getAddColumnButton() {
        return addColumnButton;
    }


    private Button removeRowButton;

    public Button getRemoveRowButton() {
        return removeRowButton;
    }

    private Button removeColumnButton;

    public Button getRemoveColumnButton() {
        return removeColumnButton;
    }

    private Button removeTableButton;

    public Button getRemoveTableButton() {
        return removeTableButton;
    }

    private ComboBox<BorderStyle> borderStyleCombobox;

    public ComboBox<BorderStyle> getBorderStyleCombobox() {
        return borderStyleCombobox;
    }

    private ColorPicker borderColorPicker;

    public ColorPicker getBorderColorPicker() {
        return borderColorPicker;
    }

    private void initComponents() {
        container = new GridPane();
        HBox optionsPanel = new HBox();

        addColumnButton = new Button();
        addColumnButton.setMinWidth(fixPrefWidth);
        addColumnButton.setPrefHeight(fixPrefHeight);
        addColumnButton.setText("+ Column");

        addRowButton = new Button();
        addRowButton.setMinWidth(fixPrefWidth);
        addRowButton.setPrefHeight(fixPrefHeight);
        addRowButton.setText("+ Row");

        removeColumnButton = new Button();
        removeColumnButton.setMinWidth(fixPrefWidth);
        removeColumnButton.setPrefHeight(fixPrefHeight);
        removeColumnButton.setText("- Column");

        removeRowButton = new Button();
        removeRowButton.setMinWidth(fixPrefWidth);
        removeRowButton.setPrefHeight(fixPrefHeight);
        removeRowButton.setText("- Row");

        removeTableButton = new Button();
        removeTableButton.setPrefHeight(fixPrefHeight);
        removeTableButton.setMinWidth(fixPrefWidth);
        removeTableButton.setText("- Table");

        borderColorPicker = new ColorPicker();
        borderColorPicker.setMinWidth(fixPrefWidth);
        borderColorPicker.setPrefHeight(fixPrefHeight);

        borderStyleCombobox = new ComboBox<BorderStyle>();
        borderStyleCombobox.setItems(FXCollections.observableList(new ArrayList<>(EnumSet.allOf(BorderStyle.class))));

        optionsPanel.getChildren().add(addColumnButton);
        optionsPanel.getChildren().add(addRowButton);
        optionsPanel.getChildren().add(removeColumnButton);
        optionsPanel.getChildren().add(removeRowButton);
        optionsPanel.getChildren().add(removeTableButton);
        optionsPanel.getChildren().add(borderColorPicker);
        optionsPanel.getChildren().add(borderStyleCombobox);

        container.add(optionsPanel, 0, 0);

        setContent(container);
    }

    private EventHandler addColumnListener;
    public void setAddColumnListener(EventHandler handler) {
        addColumnListener = handler;
        addColumnButton.setOnAction(addColumnListener);
    }

    private EventHandler addRowListener;
    public void setAddRowListener(EventHandler handler) {
        addRowListener = handler;
        addRowButton.setOnAction(addRowListener);
    }

    private EventHandler borderStyleListener;
    public void setBorderStyleListener(EventHandler handler) {
        borderStyleListener = handler;
        borderStyleCombobox.setOnAction(borderStyleListener);
    }

    private EventHandler borderColorListener;
    public void setBorderColorListener(EventHandler handler) {
        borderColorListener = handler;
        borderColorPicker.setOnAction(borderColorListener);
    }
}
