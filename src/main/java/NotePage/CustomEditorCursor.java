package NotePage;

import java.util.UUID;

public class CustomEditorCursor {
    private UUID selectedUUID;
    private int selectedPosition;

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
}
