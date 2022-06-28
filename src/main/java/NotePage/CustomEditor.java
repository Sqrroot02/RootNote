package NotePage;

import Base.Helper.ColorHelper;
import NotePage.HTMLHelper.CSSBorder;
import NotePage.HTMLHelper.WebHelper;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
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
    private TextSelectionChangedListener selectionChangedListener = new TextSelectionChangedListener() {
        @Override
        public void onTextChanged(String s) {
            System.out.println(s);
        }
    };

    private CustomEditorCursor cursor;
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

    // Returns a bool Value whether a text is currently selected or not

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

    // endregion

    private String getCss(){
        String result = "";
        result += MessageFormat.format("color:{0};", ColorHelper.toHexString(textForegroundColor));
        result += MessageFormat.format("background-color:{0};",ColorHelper.toHexString(textBackgroundColor));
        result += MessageFormat.format("font-family:{0};",textFontFamily);
        result += MessageFormat.format("text-align:{0};font-size:{1}px;",textAlignment,textSize);
        if (!textDecorationLine.isEmpty()){
            result += MessageFormat.format("text-decoration-line:{0};text-decoration-color:rgb({1},{2},{3});text-decoration-style:{4};text-decoration-thickness:{5};",textDecorationLine,textDecorationColor.getRed(),textDecorationColor.getGreen(),textDecorationColor.getBlue(),textDecorationStyle,textDecorationThickness);
        }
        if (!textTransformation.isEmpty()){
            result += MessageFormat.format("text-transform:{0};",textTransformation);
        }
        System.out.print(result);
        return result;
    }

    private void applyChanges(){
        String selectedItem = WebHelper.getSelectedNode(getEngine());
        Element selectedElement = WebHelper.getElementById(selectedItem,getEngine());
        selectedElement.setAttribute("style",getCss());
    }

    public CustomEditor(WriteTab writeTab){
        view = new WebView();
        pane = new StackPane(view);
        writeOptionsTab = writeTab;

        cursor = new CustomEditorCursor(getEngine());

        cursor.setNewPosition(UUID.randomUUID(),0);

        getEngine().loadContent("<body><p id='" + cursor.getSelectedUUID().toString() + "' style='"+ getCss() +"' contenteditable='true'></p></body>" );
        view.addEventHandler(MouseEvent.MOUSE_RELEASED,mouseReleasedEvent);
        view.addEventHandler(KeyEvent.KEY_PRESSED,keyboardPressed);
        defMethodesWriteTab();
    }

    public void changePartSettings(){

    }

    public EventHandler<MouseEvent> mouseReleasedEvent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {

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
            System.out.println("yee");
            applyChanges();
        }));
    }

}


