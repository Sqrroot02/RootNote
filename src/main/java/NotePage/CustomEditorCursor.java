package NotePage;

import NotePage.HTMLHelper.WebHelper;
import javafx.scene.web.WebEngine;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.UUID;

public class CustomEditorCursor {
    private UUID selectedUUID;
    private int selectedPosition;
    private WebEngine contextEngine;

    public CustomEditorCursor(WebEngine engine){
        contextEngine = engine;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public UUID getSelectedUUID() {
        return selectedUUID;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public void setSelectedUUID(UUID selectedUUID) {
        this.selectedUUID = selectedUUID;
    }

    public void setNewPosition(UUID id, int index){
        selectedPosition = index;
        selectedUUID = id;
    }

    public void moveForeward(){
        Element selected = contextEngine.getDocument().getElementById(selectedUUID.toString());
        int length = selected.getTextContent().length();
        if (selectedPosition < length -1){
            selectedPosition++;
        }
        else {
            selectedPosition = 0;
            Element el = (Element)WebHelper.getNextSiblingById(selectedUUID.toString(),contextEngine);
            System.out.println(selectedPosition + " | " + selectedUUID);
            selectedUUID = UUID.fromString(el.getAttribute("id"));
        }
        System.out.println(selectedPosition + " | " + selectedUUID.toString() + " | " +length);
    }

    public void moveBackward(){
        Element selected = contextEngine.getDocument().getElementById(selectedUUID.toString());
        int length = selected.getTextContent().length();
        int currentPos = WebHelper.getOffsetAnchor(contextEngine);
        if (currentPos -1 > 0){
            selectedPosition--;
        }
        else {
            selectedPosition = 0;
            Element el = (Element)WebHelper.getPrevSiblingById(WebHelper.getSelectedNode(contextEngine),contextEngine);
            selectedUUID = UUID.fromString(el.getAttribute("id"));
        }
    }
}
