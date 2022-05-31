package Models;

import javafx.scene.paint.Color;

import java.util.UUID;

public class Header {
    public Header(String title, Color color){
        setTitle(title);
        setColorHex(color);
    }

    private String title;
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    private UUID headerId;
    public UUID getHeaderId() {
        return headerId;
    }
    public void setHeaderId(UUID headerId) {
        this.headerId = headerId;
    }

    private Color colorHex;
    public Color getColorHex() {
        return colorHex;
    }
    public void setColorHex(Color colorHex) {
        this.colorHex = colorHex;
    }
}
