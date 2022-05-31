package NotePage;

import Models.Header;
import javafx.scene.control.ListCell;

public class NoteHeaderItem extends ListCell<Header> {

    public NoteHeaderItem(){

    }
    @Override
    protected void updateItem(Header header, boolean empty) {
        super.updateItem(header,empty);
        if (header == null || empty){
            setText(null);
        }
        else {
            setText(header.getTitle());
        }
    }

}


