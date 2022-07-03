package NotePage.Tabs;

import javafx.scene.control.*;

public class ListTab extends Tab {
    public ListTab(){
        initComponents();
        setText("List");
    }

    private ToggleGroup listTypeToggleGroup;
    public ToggleGroup getListTypeToggleGroup() {
        return listTypeToggleGroup;
    }

    private ToggleButton listOrderedType;
    public ToggleButton getListOrderedType() {
        return listOrderedType;
    }

    private ToggleButton listUnorderedType;
    public ToggleButton getListUnorderedType() {
        return listUnorderedType;
    }

    private ComboBox<String> listStyleTypes;
    public ComboBox<String> getListStyleTypes() {
        return listStyleTypes;
    }

    private TextField listPadding;
    public TextField getListPadding() {
        return listPadding;
    }

    private TextField listMargin;
    public TextField getListMargin() {
        return listMargin;
    }

    private void initComponents(){

    }
}
