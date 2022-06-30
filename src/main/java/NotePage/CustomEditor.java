package NotePage;

import Base.Enumerations.BorderStyle;
import Base.Helper.ColorHelper;
import NotePage.HTMLHelper.CSSBorder;
import NotePage.HTMLHelper.WebHelper;
import NotePage.Tabs.ElementsTab;
import NotePage.Tabs.TableTab;
import NotePage.Tabs.WriteTab;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.w3c.dom.*;

import java.text.MessageFormat;
import java.util.UUID;

public class CustomEditor {

    public interface TextSelectionChangedListener{
        public void onTextChanged(String s);
    }

    // Used Webview
    private WebView view;
    private WriteTab writeOptionsTab;
    private ElementsTab elementsTab;
    private TableTab tableTab;

    private TextSelectionChangedListener selectionChangedListener = new TextSelectionChangedListener() {
        @Override
        public void onTextChanged(String s) {
            System.out.println(s);
        }
    };

    private CustomEditorCursor cursor;
    private Element selectedNode;
    public EventHandler selectedNodeChangedListener;


    private StackPane pane;
    private String selectedString = null;
    public Element getCurrentElement(){
        return view.getEngine().getDocument().getElementById(cursor.getSelectedUUID().toString());
    }
    public Node getBody(){
        return getEngine().getDocument().getElementsByTagName("body").item(0);
    }
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
    private BorderStyle tableBorderStyle = BorderStyle.solid;
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
    private String getCss(){
        String result = "";
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
        System.out.print(result);
        return result;
    }

    private String getTableCss(){
        String result = "th, td {";
        result += MessageFormat.format("border-style: {0};",tableBorderStyle.name());
        result += MessageFormat.format("border-color: {0};", ColorHelper.toHexString(tableBorderColor));
        result += "}";
        return result;
    }

    private void applyChanges(){
        String selectedItem = WebHelper.getSelectedNode(getEngine());
        Element selectedElement = WebHelper.getElementById(selectedItem,getEngine());
        selectedElement.setAttribute("style",getCss());
    }

    public CustomEditor(WriteTab writeTab, ElementsTab elTab, TableTab taTab){
        view = new WebView();
        pane = new StackPane(view);
        elementsTab = elTab;
        writeOptionsTab = writeTab;
        tableTab = taTab;

        cursor = new CustomEditorCursor(getEngine());

        cursor.setNewPosition(UUID.randomUUID(),0);

        getEngine().loadContent("<body><div id='" + UUID.randomUUID().toString() + "' style='"+ getCss() +"' contenteditable='true'></div></body>" );
        view.addEventHandler(MouseEvent.MOUSE_RELEASED,mouseReleasedEvent);
        view.addEventHandler(KeyEvent.KEY_PRESSED,keyboardPressed);
        defMethodesWriteTab();
    }

    public EventHandler<MouseEvent> mouseReleasedEvent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Element activeElement = WebHelper.getActiveElement(getEngine());
            selectedNode = activeElement;
            //selectedNodeChangedListener.notifyAll();
        }
    };

    // region Input Handling
    public EventHandler<KeyEvent> keyboardPressed = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent keyEvent) {

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
            applyChanges();
        }));

        writeOptionsTab.setFontSizeChangedListener((event -> {
            String selectedSize = writeOptionsTab.getFontSizeSelection().getSelectionModel().getSelectedItem();
            textSize = Integer.parseInt(selectedSize);
            applyChanges();
        }));

        writeOptionsTab.setFontForegroundListener((event -> {
            Color selectedColor = writeOptionsTab.getFontForegroundSelection().getValue();
            textForegroundColor = selectedColor;
            applyChanges();
        }));

        writeOptionsTab.setFontBackgroundListener((event -> {
            Color selectedColor = writeOptionsTab.getFontBackgroundPicker().getValue();
            textBackgroundColor = selectedColor;
            applyChanges();
        }));

        writeOptionsTab.setFontBoldListener((event -> {
            boolean toogleResult = writeOptionsTab.getFontBoldToggle().isSelected();
            if (toogleResult){
                textWeight = "bold";
            }
            else {
                textWeight = "normal";
            }
            applyChanges();
        }));

        writeOptionsTab.setFontItalicListener((event -> {
            boolean toggleResult = writeOptionsTab.getFontItalicToggle().isSelected();
            if (toggleResult){
                fontStyle = "italic";
            }
            else {
                fontStyle = "normal";
            }
            applyChanges();
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
            applyChanges();
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
            applyChanges();
        }));

        // Appends a new Table Element to the document
        elementsTab.setAddTableListener((event -> {
            getEngine().executeScript("var table = document.createElement('table');" +
                    "table.setAttribute('id', '" + UUID.randomUUID().toString() +"' );" +
                    "table.setAttribute('style', '" + getTableCss() + "');" +
                    "var firstRow = document.createElement('tr');" +
                    "firstRow.setAttribute('id', '" + UUID.randomUUID().toString() +"' );" +
                    "var firstCell = document.createElement('th');" +
                    "firstCell.setAttribute('id', '" + UUID.randomUUID().toString() +"' );" +
                    "firstCell.setAttribute('contenteditable', '" + "true" +"' );" +
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
    }
}


