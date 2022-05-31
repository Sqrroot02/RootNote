package NotePage;

import Models.Header;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class NoteHeaderFactory implements Callback<ListView<Header>, ListCell<Header>> {

    @Override
    public ListCell<Header> call(ListView<Header> headerListView) {
        return new NoteHeaderItem();
    }
}
