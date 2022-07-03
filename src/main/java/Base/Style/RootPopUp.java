package Base.Style;

import com.example.rootnote.StaticApplication;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class RootPopUp extends Popup {

    private HBox headerPart;
    private Node contentPart;
    private VBox mainContainer;
    private Button closeButton;
    private Label titleLabel;

    public RootPopUp(Node content, Stage showedStage){
        super();
        contentPart = content;

        super.setWidth(300);
        super.setHeight(200);

        headerPart = new HBox();
        headerPart.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        closeButton = new Button();
        closeButton.setOnAction(closeEventHandler);
        closeButton.setMinHeight(30);
        closeButton.setMinWidth(30);
        closeButton.setText("x");

        headerPart.getChildren().add(closeButton);

        mainContainer = new VBox();
        mainContainer.setMinWidth(300);
        mainContainer.setMinHeight(200);
        mainContainer.getChildren().add(headerPart);
        mainContainer.getChildren().add(contentPart);
        mainContainer.setStyle("-fx-background-color : #bfbfbf;" +
                "-fx-border-radius: 10px;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.8), 10, 0, 0, 0);");

        super.getContent().add(mainContainer);
        super.show(StaticApplication.usedStage);
    }

    public void setWidth(int width){
        mainContainer.setMinWidth(width);
    }

    public void setHeight(int height){
        mainContainer.setMinHeight(height);
    }

    private EventHandler closeEventHandler = (event -> {
        super.hide();
    });
}
