package NotePage.HTMLHelper;

import NotePage.Tabs.ElementsTab;
import javafx.scene.web.WebEngine;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class WebHelper {
    public static String getSelectedNode(WebEngine engine){
        return (String) engine.executeScript("window.getSelection().getRangeAt(0).commonAncestorContainer.parentNode.getAttribute('id')");
    }

    public static Element getNextSiblingById(String id, WebEngine engine){
        Element el = getElementById(id,engine);
        Element sibling = (Element)el.getNextSibling();
        return sibling;
    }

    public static Node getPrevSiblingById(String id, WebEngine engine){
        Element el = getElementById(id,engine);
        Element sibling = (Element)el.getPreviousSibling();
        return sibling;
    }

    public static int getOffsetAnchor(WebEngine engine){
        return (int)engine.executeScript("window.getSelection().anchorOffset");
    }

    public static Element getElementById(String id, WebEngine engine){
        Element root = (Element)engine.getDocument().getElementsByTagName("body").item(0);
        for (int i = 0; i < root.getChildNodes().getLength(); i++) {
            Element child = (Element)root.getChildNodes().item(i);
            System.out.println(child.getAttribute("id"));
            System.out.println(id);
            if (child.getAttribute("id").equals(id)){
                return child;
            }
        }
        return null;
    }

    public static Element getBodyElement(WebEngine engine){
        return (Element)engine.getDocument().getElementsByTagName("body").item(0);
    }

    public static Element getActiveElement(WebEngine engine){
        return (Element)engine.executeScript("document.activeElement");
    }

    public static Element getTableofCell(WebEngine engine, String cellid){
        Element cellContainer = (Element)engine.executeScript("document.getElementById('"+ cellid +"')");
        Element cell = (Element)cellContainer.getParentNode();
        Element table = (Element)cell.getParentNode();

        return table;
    }
}
