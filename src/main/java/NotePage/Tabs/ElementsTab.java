package NotePage.Tabs;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class ElementsTab extends Tab {

    private final int fixPrefWidth = 50;
    private final int fixPrefHeight = 30;

    public ElementsTab(){
        initComponents();
        setText("Add");
    }

    // region UI Components

    private GridPane container;
    public GridPane getContainer() {
        return container;
    }

    private Button addTableButton;
    public Button getAddTableButton() {
        return addTableButton;
    }

    private Button addParagraphButton;
    public Button getAddParagraphButton() {
        return addParagraphButton;
    }

    private Button addImageButton;
    public Button getAddImageButton() {
        return addImageButton;
    }

    private Button addEnumerationButton;
    public Button getAddEnumerationButton() {
        return addEnumerationButton;
    }

    private Button addInlinePageButton;
    public Button getAddInlinePageButton() {
        return addInlinePageButton;
    }

    private Button addVideoButton;
    public Button getAddVideoButton() {
        return addVideoButton;
    }

    private Button addLinkButton;
    public Button getAddLinkButton() {
        return addLinkButton;
    }

    private Button addChecklist;
    public Button getAddChecklist() {
        return addChecklist;
    }

    private Button addFileButton;
    public Button getAddFileButton() {
        return addFileButton;
    }

    private Button addEquationButton;
    public Button getAddEquationButton() {
        return addEquationButton;
    }

    private Button addAudioButton;
    public Button getAddAudioButton() {
        return addAudioButton;
    }

    private Button addCanvasButton;
    public Button getAddCanvasButton() {
        return addCanvasButton;
    }

    private Button addSpliterButton;
    public Button getAddSpliterButton() {
        return addSpliterButton;
    }
    // endregion

    private void initComponents(){
        container = new GridPane();

        // Inner Containers
        HBox textElementsBox = new HBox();
        HBox mediaElementsBox = new HBox();
        HBox pageElementsBox = new HBox();

        // region Text Elements inner Container
        addParagraphButton = new Button();
        addParagraphButton.setPrefHeight(fixPrefHeight);
        addParagraphButton.setMinWidth(fixPrefWidth);
        addParagraphButton.setText("Paragraph");

        addTableButton = new Button();
        addTableButton.setPrefHeight(fixPrefHeight);
        addTableButton.setMinWidth(fixPrefWidth);
        addTableButton.setText("Table");

        addEnumerationButton = new Button();
        addEnumerationButton.setPrefHeight(fixPrefHeight);
        addEnumerationButton.setMinWidth(fixPrefWidth);
        addEnumerationButton.setText("List");

        addEquationButton = new Button();
        addEquationButton.setPrefHeight(fixPrefHeight);
        addEquationButton.setMinWidth(fixPrefWidth);
        addEquationButton.setText("Equation");

        textElementsBox.getChildren().add(addParagraphButton);
        textElementsBox.getChildren().add(addTableButton);
        textElementsBox.getChildren().add(addEnumerationButton);
        textElementsBox.getChildren().add(addEquationButton);
        // endregion

        // region Media Elements inner Container
        addImageButton = new Button();
        addImageButton.setPrefHeight(fixPrefHeight);
        addImageButton.setMinWidth(fixPrefWidth);
        addImageButton.setText("Image");

        addVideoButton = new Button();
        addVideoButton.setPrefHeight(fixPrefHeight);
        addVideoButton.setMinWidth(fixPrefWidth);
        addVideoButton.setText("Video");

        addAudioButton = new Button();
        addAudioButton.setPrefHeight(fixPrefHeight);
        addAudioButton.setMinWidth(fixPrefWidth);
        addAudioButton.setText("Audio");

        addFileButton = new Button();
        addFileButton.setMinWidth(fixPrefWidth);
        addFileButton.setPrefHeight(fixPrefHeight);
        addFileButton.setText("File");

        mediaElementsBox.getChildren().add(addImageButton);
        mediaElementsBox.getChildren().add(addVideoButton);
        mediaElementsBox.getChildren().add(addAudioButton);
        mediaElementsBox.getChildren().add(addFileButton);
        // endregion

        // region Page Elements inner Container
        addInlinePageButton = new Button();
        addInlinePageButton.setMinWidth(fixPrefWidth);
        addInlinePageButton.setPrefHeight(fixPrefHeight);
        addInlinePageButton.setText("Page");

        addLinkButton = new Button();
        addLinkButton.setMinWidth(fixPrefWidth);
        addLinkButton.setPrefHeight(fixPrefHeight);
        addLinkButton.setText("Link");

        addChecklist = new Button();
        addChecklist.setMinWidth(fixPrefWidth);
        addChecklist.setPrefHeight(fixPrefHeight);
        addChecklist.setText("Checklist");

        addSpliterButton = new Button();
        addSpliterButton.setMinWidth(fixPrefWidth);
        addSpliterButton.setPrefHeight(fixPrefHeight);
        addSpliterButton.setText("Seperator");

        addCanvasButton = new Button();
        addCanvasButton.setMinWidth(fixPrefWidth);
        addCanvasButton.setPrefHeight(fixPrefHeight);
        addCanvasButton.setText("Canvas");

        pageElementsBox.getChildren().add(addInlinePageButton);
        pageElementsBox.getChildren().add(addLinkButton);
        pageElementsBox.getChildren().add(addChecklist);
        pageElementsBox.getChildren().add(addCanvasButton);
        pageElementsBox.getChildren().add(addSpliterButton);
        // endregion

        container.add(textElementsBox,0,0);
        container.add(mediaElementsBox,0,1);
        container.add(pageElementsBox,0,2);

        GridPane.setMargin(container,new Insets(3,3,3,3));
        setContent(container);
    }

    //region Listener
    private EventHandler addParagraphListener;
    public void setAddParagraphListener(EventHandler handler) {
        addParagraphListener = handler;
        addParagraphButton.setOnAction(addParagraphListener);
    }

    private EventHandler addTableListener;
    public void setAddTableListener(EventHandler handler) {
        addTableListener = handler;
        addTableButton.setOnAction(addTableListener);
    }

    private EventHandler addEnumerationListener;
    public void setAddEnumerationListener(EventHandler handler) {
        addEnumerationListener = handler;
        addEnumerationButton.setOnAction(addEnumerationListener);
    }

    private EventHandler addEquationListener;
    public void setAddEquationListener(EventHandler handler){
        addEquationListener = handler;
        addEquationButton.setOnAction(addEquationListener);
    }

    private EventHandler addImageListener;
    public void setAddImageListener(EventHandler handler){
        addImageListener = handler;
        addImageButton.setOnAction(addImageListener);
    }

    private EventHandler addVideoListener;
    public void setAddVideoListener(EventHandler handler){
        addVideoListener = handler;
        addVideoButton.setOnAction(addVideoListener);
    }

    private EventHandler addAudioListener;
    public void setAddAudioListener(EventHandler handler){
        addAudioListener = handler;
        addAudioButton.setOnAction(addAudioListener);
    }

    private EventHandler addFileListener;
    public void setAddFileListener(EventHandler handler){
        addFileListener = handler;
        addFileButton.setOnAction(addFileListener);
    }

    private EventHandler addInlinePageListener;
    public void setAddInlinePageListener(EventHandler handler){
        addInlinePageListener = handler;
        addInlinePageButton.setOnAction(addInlinePageListener);
    }

    private EventHandler addLinkListener;
    public void setAddLinkListener(EventHandler handler){
        addLinkListener = handler;
        addLinkButton.setOnAction(addLinkListener);
    }

    private EventHandler addChecklistListener;
    public void setAddChecklistListener(EventHandler handler){
        addChecklistListener = handler;
        addChecklist.setOnAction(addChecklistListener);
    }

    private EventHandler addSpliterListener;
    public void setAddSpliterListener(EventHandler handler){
        addSpliterListener = handler;
        addSpliterButton.setOnAction(addSpliterListener);
    }

    private EventHandler addCanvasListener;
    public void setAddCanvasListener(EventHandler handler){
        addCanvasListener = handler;
        addCanvasButton.setOnAction(addCanvasListener);
    }
    // endregion
}
