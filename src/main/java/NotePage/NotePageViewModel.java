package NotePage;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class NotePageViewModel {

    private SettingsFrame setiingsFrame;
    private CustomEditor writeeditor;

    public NotePageViewModel(SettingsFrame frame, CustomEditor editor){
        setiingsFrame = frame;
        writeeditor = editor;

    }
}
