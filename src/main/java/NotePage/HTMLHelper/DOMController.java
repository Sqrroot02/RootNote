package NotePage.HTMLHelper;

import javafx.scene.web.WebEngine;
import org.w3c.dom.Element;

import java.util.UUID;

public class DOMController {
    public DOMController(WebEngine engine){
        webEngine = engine;
    }

    private WebEngine webEngine;

    public void appendListElement(String itemID, String tag){
        Element elementItem = (Element) webEngine.executeScript("document.getElementById('"+ itemID +"');");
        Element containerElement = (Element) elementItem.getParentNode();

        webEngine.executeScript("var container = document.getElementById('" + containerElement.getAttribute("id") + "');" +
                "var newItem = document.createElement('"+ tag +"');" +
                "newItem.setAttribute('id','"+ UUID.randomUUID().toString() +"');" +
                "newItem.setAttribute('contenteditable', true);" +
                "container.appendChild(newItem);");
    }

    public void setActiveElement(String itemID){
        webEngine.executeScript("var item = document.getElementById('" + itemID + "');" +
                "item.focus();");
    }
}
