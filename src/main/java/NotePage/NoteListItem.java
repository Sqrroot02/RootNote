package NotePage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import java.util.UUID;

public class NoteListItem {
    public NoteListItem(String title, UUID id){
        setTitle(title);
        setNoteId(id);
    }

    private String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    private UUID noteId;
    public UUID getNoteId() {
        return noteId;
    }
    public void setNoteId(UUID noteId) {
        this.noteId = noteId;
    }

    private ObservableList<TreeItem<NoteListItem>> subItems = FXCollections.observableArrayList();
    public ObservableList<TreeItem<NoteListItem>> getSubItems() {
        return subItems;
    }
    public void setSubItems(ObservableList<TreeItem<NoteListItem>> subItems) {
        this.subItems = subItems;
    }
}
