package NotePage;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class WriteTab extends Tab {

    public WriteTab(){
        initComponents();
    }

    private GridPane container;

    private ComboBox<String> fontFamilySelection;
    private ColorPicker fontForegroundSelection;
    private ComboBox<String> fontSizeSelection;

    private ToggleGroup fontAlignmentToggle;
    private ToggleButton fontLeftAlignmentToggle;
    private ToggleButton fontRightAlignmentToggle;
    private ToggleButton fontCenterToggle;
    private ToggleButton fontJustifyToggle;

    private ToggleButton fontBoldToggle;
    private ToggleButton fontStrikeThroughToggle;
    private ToggleButton fontUnderlineToggle;
    private ToggleButton fontItalicToggle;

    private ToggleGroup fontScriptPlacementToggle;
    private ToggleButton fontSuperScriptToggle;
    private ToggleButton fontSubScriptToggle;

    public GridPane getContainer(){
        return container;
    }

    private void initComponents(){
        container = new GridPane();

        fontFamilySelection = new ComboBox<>();
        fontFamilySelection.setItems(FXCollections.observableList(Font.getFamilies()));
        container.add(fontFamilySelection,0,0);

        fontSizeSelection = new ComboBox<>();
        fontSizeSelection.setEditable(true);
        fontSizeSelection.setMinWidth(50);
        fontSizeSelection.setPrefWidth(50);
        container.add(fontSizeSelection,1,0);

        fontBoldToggle = new ToggleButton();
        fontBoldToggle.setPrefWidth(50);
        fontBoldToggle.setPrefWidth(50);
        fontBoldToggle.setText("B");

        fontItalicToggle = new ToggleButton();
        fontItalicToggle.setPrefWidth(50);
        fontItalicToggle.setPrefWidth(50);
        fontItalicToggle.setText("I");

        HBox subToggleContainer = new HBox();
        subToggleContainer.getChildren().add(fontBoldToggle);
        subToggleContainer.getChildren().add(fontItalicToggle);
        container.add(subToggleContainer,0,1);

        setContent(container);
    }

}
