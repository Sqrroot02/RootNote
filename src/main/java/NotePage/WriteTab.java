package NotePage;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
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
    public ComboBox<String> getFontFamilySelection() {
        return fontFamilySelection;
    }

    private ColorPicker fontForegroundSelection;
    private ColorPicker fontBackgroundPicker;
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

    // Initializes all Components fore the Textmanipulation mode
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

        fontStrikeThroughToggle = new ToggleButton();
        fontStrikeThroughToggle.setPrefWidth(50);
        fontStrikeThroughToggle.setPrefWidth(50);
        fontStrikeThroughToggle.setText("S");

        fontUnderlineToggle = new ToggleButton();
        fontUnderlineToggle.setPrefWidth(50);
        fontUnderlineToggle.setPrefWidth(50);
        fontUnderlineToggle.setText("U");

        HBox subToggleContainer = new HBox();
        subToggleContainer.getChildren().add(fontBoldToggle);
        subToggleContainer.getChildren().add(fontItalicToggle);
        subToggleContainer.getChildren().add(fontStrikeThroughToggle);
        subToggleContainer.getChildren().add(fontUnderlineToggle);

        fontForegroundSelection = new ColorPicker();
        fontForegroundSelection.setPrefWidth(50);

        fontBackgroundPicker = new ColorPicker();
        fontBackgroundPicker.setPrefWidth(50);

        HBox subColorbox = new HBox();
        subColorbox.getChildren().add(fontForegroundSelection);
        subColorbox.getChildren().add(fontBackgroundPicker);

        fontScriptPlacementToggle = new ToggleGroup();
        HBox subscriptPlacementBox = new HBox();

        fontSuperScriptToggle = new ToggleButton();
        fontSuperScriptToggle.setPrefWidth(50);
        fontSuperScriptToggle.setText("x²");
        fontSuperScriptToggle.setToggleGroup(fontScriptPlacementToggle);
        subscriptPlacementBox.getChildren().add(fontSuperScriptToggle);

        fontSubScriptToggle = new ToggleButton();
        fontSubScriptToggle.setText("x");
        fontSubScriptToggle.setPrefWidth(50);
        fontSubScriptToggle.setToggleGroup(fontScriptPlacementToggle);

        subscriptPlacementBox.getChildren().add(fontSubScriptToggle);

        container.add(subToggleContainer,0,1);
        container.add(subColorbox,1,0);
        container.add(subscriptPlacementBox,1,1);


        setContent(container);
    }

    private EventHandler fontFamilyChangedListener;
    public void setFontFamilyChangedListener(EventHandler handler){
        fontFamilyChangedListener = handler;
        fontFamilySelection.setOnAction(fontFamilyChangedListener);
    }

    private EventHandler fontSizeChangedListener;
    public void setFontSizeChangedListener(EventHandler handler){
        fontSizeChangedListener = handler;
        fontSizeSelection.setOnAction(fontSizeChangedListener);
    }
}
