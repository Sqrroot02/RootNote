package NotePage;

import Base.Animations.ControlScaleAnimation;
import Models.Header;
import com.almasb.fxgl.cutscene.dialogue.TextNode;
import javafx.animation.Animation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import Base.Page;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.HTMLEditor;
import org.controlsfx.control.spreadsheet.SpreadsheetCellEditor;
import org.controlsfx.control.spreadsheet.SpreadsheetView;

import java.util.UUID;

public class NotePage extends Page {
    public NotePage(){
        init();
    }

    private NoteListItem selectedNoteItem;
    private Header selectedHeader;
    private ObservableList<Header> items;
    private ListView<Header> listView = new ListView<Header>(items);
    private BorderPane root = new BorderPane();
    private BorderPane noteSelectionPane = new BorderPane();
    private Button createNewButton = new Button();

    private void init(){
        items = FXCollections.observableArrayList();
        items.addListener(listChangeListener);
        items.add(new Header("test",new Color(0.9,0.4,0.5,0.3)));

        listView.setItems(items);
        listView.setCellFactory(new NoteHeaderFactory());
        listView.getSelectionModel().selectedItemProperty().addListener(selectedHeaderChanged);

        // Init Button
        createNewButton.addEventHandler(MouseEvent.MOUSE_CLICKED,createButtonClickedEvent);
        createNewButton.setText("New Note");

        noteSelectionPane.setCenter(listView);
        noteSelectionPane.setBottom(createNewButton);
        root.setLeft(noteSelectionPane);

        VBox rightContainer = new VBox(new SettingsFrame(),new CustomEditor().getPane());
        root.setRight(rightContainer);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/listviewStyles.css").toExternalForm());
        setUsedScene(scene);
    }

    private EventHandler<MouseEvent> createButtonClickedEvent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            ControlScaleAnimation.Animate(createNewButton);
            items.add(new Header("test",new Color(1,0.1,0.1,0.1)));
        }
    };

    private ListChangeListener<Header> listChangeListener = new ListChangeListener<Header>() {
        @Override
        public void onChanged(Change<? extends Header> change) {
            System.out.println(items.stream().count());
        }
    };

    // Updates the selected Header field when another item has been selected in the selection
    private ChangeListener<Header> selectedHeaderChanged = new ChangeListener<Header>() {
        @Override
        public void changed(ObservableValue<? extends Header> observableValue, Header header, Header t1) {
            selectedHeader = t1;
        }
    };

}
