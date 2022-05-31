package NotePage;

import Base.Helper.ColorHelper;
import NotePage.HTMLHelper.CSSBorder;
import javafx.css.converter.ColorConverter;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.w3c.dom.*;
import org.w3c.dom.ranges.Range;

import java.text.MessageFormat;
import java.util.UUID;

public class CustomEditor {

    public interface TextSelectionChangedListener{
        public void onTextChanged(String s);
    }

    WebView view;

    private TextSelectionChangedListener selectionChangedListener = new TextSelectionChangedListener() {
        @Override
        public void onTextChanged(String s) {
            System.out.println(s);
        }
    };

    private CustomEditorCursor cursor = new CustomEditorCursor();
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
    public Boolean getIsTextSelected(){
        if (selectedString != null && !selectedString.isEmpty()){
            return true;
        }
        else {
            return false;
        }

    }

    private Color textForegroundColor = Color.BLACK;
    private Color textBackgroundColor = Color.TRANSPARENT;
    private CSSBorder textBorder = null;
    private Boolean hasTextBorder = false;
    private String textFontFamily = "arial";
    private int textSize = 25;
    private String textAlignment = "left";
    private String textDecorationLine = "";
    private Color textDecorationColor = Color.BLACK;
    private String textDecorationStyle = "wavy";
    private int textDecorationThickness = 5;
    private String textTransformation = "";
    private String textFormat = "";

    public String getCss(){
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

    public CustomEditor(){
        view = new WebView();
        pane = new StackPane(view);

        cursor.setNewPosition(UUID.randomUUID(),0);

        view.getEngine().loadContent("<body><p id='" + cursor.getSelectedUUID().toString() + "' style='"+ getCss() +"'></p></body>" );
        view.addEventHandler(MouseEvent.MOUSE_RELEASED,mouseReleasedEvent);
        view.addEventHandler(KeyEvent.KEY_PRESSED,keyboardPressed);
    }

    public EventHandler<MouseEvent> mouseReleasedEvent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            selectedString = (String) getEngine().executeScript("window.getSelection().toString()");

            String readId = (String) getEngine().executeScript("window.getSelection().getRangeAt(0).commonAncestorContainer.parentNode.getAttribute('id')");
            int offset = (int) getEngine().executeScript("window.getSelection().anchorOffset");
            if (readId != null) {
                System.out.println(readId + " | " + offset);
                cursor.setNewPosition(UUID.fromString(readId), offset);
                selectionChangedListener.onTextChanged(selectedString);
            }
        }
    };

    // region Input Handling
    public EventHandler<KeyEvent> keyboardPressed = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent keyEvent) {
            // Appends new Paragraph on using ENTER
            if (keyEvent.getCode() == KeyCode.ENTER){
                Element el = getEngine().getDocument().createElement("p");
                el.setAttribute("style",getCss());
                el.setTextContent("");
                UUID newId = UUID.randomUUID();

                cursor.setNewPosition(newId,0);

                el.setAttribute("id",newId.toString());
                getBody().appendChild(el);
            }
            // Removes left Character on using BACKSPACE
            else if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
                if (getIsTextSelected()){
                    getEngine().executeScript("window.getSelection().deleteFromDocument()");
                }
                else if (cursor.getSelectedPosition() > 0){
                    String oldString = getCurrentElement().getTextContent();
                    String left = oldString.substring(0,cursor.getSelectedPosition()-1);
                    String right = oldString.substring(cursor.getSelectedPosition());

                    getCurrentElement().setTextContent(left + right);
                    cursor.setSelectedPosition(cursor.getSelectedPosition() - 1);
                }
            }
            else if (keyEvent.getCode() == KeyCode.CAPS) {
                // Nothing should happen here
            }
            // Appends to the TextContent a Char
            else {
                String character = keyEvent.getText();

                String oldString = getCurrentElement().getTextContent();
                String left = oldString.substring(0,cursor.getSelectedPosition());
                String right = oldString.substring(cursor.getSelectedPosition());

                getCurrentElement().setTextContent(left + character + right);
                cursor.setSelectedPosition(cursor.getSelectedPosition() + 1);
            }
        }
    };
    // endregion
    public StackPane getPane() {
        return pane;
    }
}


