package NotePage;

import Base.Enumerations.BorderStyle;
import Base.Helper.ColorHelper;
import Base.Style.RootPopUp;
import NotePage.HTMLHelper.CSSBorder;
import NotePage.HTMLHelper.DOMController;
import NotePage.HTMLHelper.WebHelper;
import NotePage.Tabs.ElementsTab;
import NotePage.Tabs.TableTab;
import NotePage.Tabs.WriteTab;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.w3c.dom.*;

import java.text.MessageFormat;
import java.util.UUID;

public class CustomEditor {

    public CustomEditor(WriteTab writeTab, ElementsTab elTab, TableTab taTab, Stage stage){
        usedStage = stage;
        view = new WebView();
        pane = new StackPane(view);
        elementsTab = elTab;
        writeOptionsTab = writeTab;
        tableTab = taTab;

        cursor = new CustomEditorCursor(getEngine());

        cursor.setNewPosition(UUID.randomUUID(),0);

        getEngine().loadContent("<body></body>" );
        view.addEventHandler(MouseEvent.MOUSE_RELEASED,mouseReleasedEvent);
        view.addEventHandler(KeyEvent.KEY_PRESSED,keyboardPressed);
        view.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseClickedEvent);

        defMethodesWriteTab();
        domController = new DOMController(getEngine());
    }

    // Used Webview
    private WebView view;
    private WriteTab writeOptionsTab;
    private ElementsTab elementsTab;
    private TableTab tableTab;
    private DOMController domController;

    private Stage usedStage;

    private CustomEditorCursor cursor;
    private Element selectedNode;

    private StackPane pane;
    public WebEngine getEngine(){
        return view.getEngine();
    }


    // region Properties
    // Text Alignment
    private String textAlignment = "left";
    public void setTextAlignment(String textAlignment) {
        this.textAlignment = textAlignment;
    }
    public String getTextAlignment() {
        return textAlignment;
    }

    // Text Background
    private Color textBackgroundColor = Color.TRANSPARENT;
    public void setTextBackgroundColor(Color textBackgroundColor) {
        this.textBackgroundColor = textBackgroundColor;
    }
    public Color getTextBackgroundColor() {
        return textBackgroundColor;
    }

    // Text Border
    private CSSBorder textBorder = null;
    public void setTextBorder(CSSBorder textBorder) {
        this.textBorder = textBorder;
    }
    public CSSBorder getTextBorder() {
        return textBorder;
    }

    // Text Decoration Color
    private Color textDecorationColor = Color.BLACK;
    public void setTextDecorationColor(Color textDecorationColor) {
        this.textDecorationColor = textDecorationColor;
    }
    public Color getTextDecorationColor() {
        return textDecorationColor;
    }

    // Text Decoration Line
    private String textDecorationLine = "";
    public void setTextDecorationLine(String textDecorationLine) {
        this.textDecorationLine = textDecorationLine;
    }
    public String getTextDecorationLine() {
        return textDecorationLine;
    }

    // Text Decoration Style
    private String textDecorationStyle = "wavy";
    public void setTextDecorationStyle(String textDecorationStyle) {
        this.textDecorationStyle = textDecorationStyle;
    }
    public String getTextDecorationStyle() {
        return textDecorationStyle;
    }

    // Text Decoration Thickness
    private int textDecorationThickness = 5;
    public void setTextDecorationThickness(int textDecorationThickness) {
        this.textDecorationThickness = textDecorationThickness;
    }
    public int getTextDecorationThickness() {
        return textDecorationThickness;
    }

    // Text Font Family
    private String textFontFamily = "arial";
    public void setTextFontFamily(String textFontFamily) {
        this.textFontFamily = textFontFamily;
    }
    public String getTextFontFamily() {
        return textFontFamily;
    }

    // Text Foreground
    private Color textForegroundColor = Color.BLACK;
    public void setTextForegroundColor(Color textForegroundColor) {
        this.textForegroundColor = textForegroundColor;
    }
    public Color getTextForegroundColor() {
        return textForegroundColor;
    }

    // Text Size
    private int textSize = 25;
    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }
    public int getTextSize() {
        return textSize;
    }

    // Text Format
    private String textFormat = "";
    public void setTextFormat(String textFormat) {
        this.textFormat = textFormat;
    }
    public String getTextFormat() {
        return textFormat;
    }

    // Text Transformation
    private String textTransformation = "";
    public void setTextTransformation(String textTransformation) {
        this.textTransformation = textTransformation;
    }
    public String getTextTransformation() {
        return textTransformation;
    }

    // Text Weight
    private String textWeight = "normal";
    public void setTextWeight(String textWeight) {
        this.textWeight = textWeight;
    }
    public String getTextWeight(){
        return textWeight;
    }

    // Font Style
    private String fontStyle = "normal";
    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }
    public String getFontStyle() {
        return fontStyle;
    }

    // Text Decorations
    private String textDecoration = "normal";
    public String getTextDecoration() {
        return textDecoration;
    }
    public void setTextDecoration(String textDecoration) {
        this.textDecoration = textDecoration;
    }

    // Table Border Styles
    private BorderStyle tableBorderStyle = BorderStyle.dotted;
    public BorderStyle getTableBorderStyle(){
        return tableBorderStyle;
    }
    public void setTableBorderStyle(BorderStyle tableBorderStyle) {
        this.tableBorderStyle = tableBorderStyle;
    }

    // Table Border Color
    private Color tableBorderColor = Color.BLACK;
    public Color getTableBorderColor() {
        return tableBorderColor;
    }
    public void setTableBorderColor(Color tableBorderColor) {
        this.tableBorderColor = tableBorderColor;
    }

    // endregion
    private String getCss(String tag){
        String result = "";
        System.out.println(tag);
        switch (tag){
            case "table":{
                System.out.println("why?");
                result += MessageFormat.format("border-style: {0};",tableBorderStyle.name());
                result += MessageFormat.format("border-color: {0};", ColorHelper.toHexString(tableBorderColor));
                result += MessageFormat.format("color:{0};", ColorHelper.toHexString(textForegroundColor));
                result += MessageFormat.format("background-color:{0};",ColorHelper.toHexString(textBackgroundColor));
                result += MessageFormat.format("font-family:{0};",textFontFamily);
                result += MessageFormat.format("text-align:{0};font-size:{1}px;",textAlignment,textSize);
                result += MessageFormat.format("font-weight:{0};",textWeight);
                result += MessageFormat.format("font-style:{0};",fontStyle);
                result += MessageFormat.format("text-decoration:{0};", textDecoration);
                if (!textDecorationLine.isEmpty()){
                    result += MessageFormat.format("text-decoration-line:{0};text-decoration-color:rgb({1},{2},{3});text-decoration-style:{4};text-decoration-thickness:{5};",textDecorationLine,textDecorationColor.getRed(),textDecorationColor.getGreen(),textDecorationColor.getBlue(),textDecorationStyle,textDecorationThickness);
                }
                if (!textTransformation.isEmpty()){
                    result += MessageFormat.format("text-transform:{0};",textTransformation);
                }
            }
            case "p":{
                result += MessageFormat.format("color:{0};", ColorHelper.toHexString(textForegroundColor));
                result += MessageFormat.format("background-color:{0};",ColorHelper.toHexString(textBackgroundColor));
                result += MessageFormat.format("font-family:{0};",textFontFamily);
                result += MessageFormat.format("text-align:{0};font-size:{1}px;",textAlignment,textSize);
                result += MessageFormat.format("font-weight:{0};",textWeight);
                result += MessageFormat.format("font-style:{0};",fontStyle);
                result += MessageFormat.format("text-decoration:{0};", textDecoration);
                if (!textDecorationLine.isEmpty()){
                    result += MessageFormat.format("text-decoration-line:{0};text-decoration-color:rgb({1},{2},{3});text-decoration-style:{4};text-decoration-thickness:{5};",textDecorationLine,textDecorationColor.getRed(),textDecorationColor.getGreen(),textDecorationColor.getBlue(),textDecorationStyle,textDecorationThickness);
                }
                if (!textTransformation.isEmpty()){
                    result += MessageFormat.format("text-transform:{0};",textTransformation);
                }
            }
        }
        return result;
    }
    private void applyChanges(String tag){
        selectedNode.setAttribute("style",getCss(tag));
    }

    public EventHandler<MouseEvent> mouseReleasedEvent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {

        }
    };

    public EventHandler<MouseEvent> mouseClickedEvent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            // Handles double CLick
            if (mouseEvent.getClickCount() == 2){
                if (selectedNode.getTagName().equals("TH")){
                    selectedNode = WebHelper.getTableofCell(getEngine(),selectedNode.getAttribute("id"));
                    domController.setActiveElement(selectedNode.getAttribute("id"));
                }
                System.out.println(selectedNode.getAttribute("id") + " " + selectedNode.getTagName());
            }
            else {
                Element activeElement = WebHelper.getActiveElement(getEngine());
                selectedNode = activeElement;
                System.out.println(selectedNode.getAttribute("id") + " " + selectedNode.getTagName());
            }
        }
    };

    // region Input Handling
    public EventHandler<KeyEvent> keyboardPressed = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent keyEvent) {
            switch (keyEvent.getCode()){
                case ENTER:{
                    if (selectedNode.getTagName().equals("LI")){
                        domController.appendListElement(selectedNode.getAttribute("id"),"li");
                    }
                }
            }
        }
    };

    // endregion
    public StackPane getPane() {
        return pane;
    }

    private void defMethodesWriteTab(){
        writeOptionsTab.setFontFamilyChangedListener(((event) ->{
            String selectedFont = writeOptionsTab.getFontFamilySelection().getSelectionModel().getSelectedItem();
            textFontFamily = selectedFont;
            applyChanges(selectedNode.getTagName().toLowerCase());
        }));

        writeOptionsTab.setFontSizeChangedListener((event -> {
            String selectedSize = writeOptionsTab.getFontSizeSelection().getSelectionModel().getSelectedItem();
            textSize = Integer.parseInt(selectedSize);
            applyChanges(selectedNode.getTagName().toLowerCase());
        }));

        writeOptionsTab.setFontForegroundListener((event -> {
            Color selectedColor = writeOptionsTab.getFontForegroundSelection().getValue();
            textForegroundColor = selectedColor;
            applyChanges(selectedNode.getTagName().toLowerCase());
        }));

        writeOptionsTab.setFontBackgroundListener((event -> {
            Color selectedColor = writeOptionsTab.getFontBackgroundPicker().getValue();
            textBackgroundColor = selectedColor;
            applyChanges(selectedNode.getTagName().toLowerCase());
        }));

        writeOptionsTab.setFontBoldListener((event -> {
            boolean toogleResult = writeOptionsTab.getFontBoldToggle().isSelected();
            if (toogleResult){
                textWeight = "bold";
            }
            else {
                textWeight = "normal";
            }
            applyChanges(selectedNode.getTagName().toLowerCase());
        }));

        writeOptionsTab.setFontItalicListener((event -> {
            boolean toggleResult = writeOptionsTab.getFontItalicToggle().isSelected();
            if (toggleResult){
                fontStyle = "italic";
            }
            else {
                fontStyle = "normal";
            }
            applyChanges(selectedNode.getTagName().toLowerCase());
        }));

        writeOptionsTab.setFontStrikeThroughListener((event -> {
            boolean toggleStrikeResult = writeOptionsTab.getFontStrikeThroughToggle().isSelected();
            boolean toggleUnderlineResult = writeOptionsTab.getFontUnderlineToggle().isSelected();
            if (toggleStrikeResult && toggleUnderlineResult){
                textDecoration = "line-through underline";
            }
            else if (toggleStrikeResult){
                textDecoration = "line-through";
            }
            else if (toggleUnderlineResult){
                textDecoration = "underline";
            }
            else {
                textDecoration = "normal";
            }
            applyChanges(selectedNode.getTagName().toLowerCase());
        }));

        writeOptionsTab.setFontUnderlineListener((event -> {
            boolean toggleStrikeResult = writeOptionsTab.getFontStrikeThroughToggle().isSelected();
            boolean toggleUnderlineResult = writeOptionsTab.getFontUnderlineToggle().isSelected();
            if (toggleStrikeResult && toggleUnderlineResult){
                textDecoration = "line-through underline";
            }
            else if (toggleStrikeResult){
                textDecoration = "line-through";
            }
            else if (toggleUnderlineResult){
                textDecoration = "underline";
            }
            else {
                textDecoration = "normal";
            }
            applyChanges(selectedNode.getTagName().toLowerCase());
        }));

        // Appends a new Table Element to the document
        elementsTab.setAddTableListener((event -> {
            getEngine().executeScript("var table = document.createElement('table');" +
                    "table.setAttribute('id', '" + UUID.randomUUID().toString() +"' );" +
                    "table.setAttribute('style', '" + "border: 1px solid;" + "');" +
                    "var firstRow = document.createElement('tr');" +
                    "firstRow.setAttribute('class', 'tr');" +
                    "firstRow.setAttribute('id', '" + UUID.randomUUID().toString() +"' );" +
                    "var firstCell = document.createElement('th');" +
                    "firstCell.setAttribute('id', '" + UUID.randomUUID().toString() +"' );" +
                    "firstCell.setAttribute('contenteditable', '" + "true" +"' );" +
                    "firstCell.setAttribute('class','th');" +
                    "firstCell.innerHTML += 'Extra stuff';" +
                    "firstRow.appendChild(firstCell);" +
                    "table.appendChild(firstRow);" +
                    "document.body.appendChild(table);");
        }));

        // Appends a new Paragraph to the Document
        elementsTab.setAddParagraphListener((event -> {
            getEngine().executeScript("var p = document.createElement('p');" +
                    "p.setAttribute('id', '" + UUID.randomUUID().toString() +"' );" +
                    "p.setAttribute('contenteditable', '" + "true" +"' );" +
                    "document.body.appendChild(p);");
        }));

        // Appends a new Enumeration to the Document
        elementsTab.setAddEnumerationListener((event -> {
            getEngine().executeScript("var ul = document.createElement('ul');" +
                    "ul.setAttribute('id', '" + UUID.randomUUID().toString() +"' );" +
                    "ul.setAttribute('type','circle');" +
                    "var li = document.createElement('li');" +
                    "li.setAttribute('id', '" + UUID.randomUUID().toString() +"' );" +
                    "li.setAttribute('contenteditable', '" + "true" +"' );" +
                    "ul.appendChild(li);" +
                    "document.body.appendChild(ul);");
        }));

        elementsTab.setAddSpliterListener((event -> {
            getEngine().executeScript("var sep = document.createElement('hr');" +
                    "document.body.appendChild(sep);");
        }));

        elementsTab.setAddImageListener((event -> {
            RootPopUp rootPopUp = new RootPopUp(new BorderPane(),usedStage);
        }));

        // Adds a new Column to an existing Table
        tableTab.setAddColumnListener((event -> {
            if (selectedNode.getTagName().equals("TH")){
                Element tableElement = WebHelper.getTableofCell(getEngine(),selectedNode.getAttribute("id"));
                NodeList list = tableElement.getChildNodes();

                for (int i = 0; i < list.getLength(); i++) {
                    Element nodeElement = (Element) list.item(i);
                    getEngine().executeScript("var row = document.getElementById('"+ nodeElement.getAttribute("id") +"');" +
                            "var newCell = document.createElement('th');" +
                            "newCell.setAttribute('id', '" + UUID.randomUUID().toString() +"' );" +
                            "newCell.setAttribute('contenteditable', '" + "true" + "' );" +
                            "newCell.innerHTML += 'Stuff';" +
                            "row.appendChild(newCell);");
                }
            }
        }));

        // Adds a new Row to an existing Table
        tableTab.setAddRowListener((event -> {
            if (selectedNode.getTagName().equals("TH")){
                Element tableElement = WebHelper.getTableofCell(getEngine(),selectedNode.getAttribute("id"));

                int cellLength = tableElement.getFirstChild().getChildNodes().getLength();
                String newRowUUID = UUID.randomUUID().toString();

                getEngine().executeScript("var table = document.getElementById('"+ tableElement.getAttribute("id") +"');" +
                        "var newRow = document.createElement('tr');" +
                        "newRow.setAttribute('id','"+ newRowUUID +"');" +
                        "table.appendChild(newRow);");

                for (int i = 0; i < cellLength; i++) {
                    getEngine().executeScript("var row = document.getElementById('"+ newRowUUID +"');" +
                            "var newCell = document.createElement('th');" +
                            "newCell.setAttribute('id', '" + UUID.randomUUID().toString() +"' );" +
                            "newCell.setAttribute('contenteditable', '" + "true" + "' );" +
                            "newCell.innerHTML += 'Stuff';" +
                            "row.appendChild(newCell);");
                }
            }
        }));

        tableTab.setBorderColorListener((event -> {
            Color selectedColor = tableTab.getBorderColorPicker().getValue();
            tableBorderColor = selectedColor;
            applyChanges(selectedNode.getTagName().toLowerCase());
        }));

        tableTab.setBorderStyleListener((event -> {
            BorderStyle selectedStyle = tableTab.getBorderStyleCombobox().getSelectionModel().getSelectedItem();
            tableBorderStyle = selectedStyle;
            applyChanges(selectedNode.getTagName().toLowerCase());
        }));
    }
}


