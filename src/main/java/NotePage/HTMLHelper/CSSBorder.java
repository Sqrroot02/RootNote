package NotePage.HTMLHelper;

import com.almasb.fxgl.scene.CSS;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;

public class CSSBorder {
    private CSSBorderStyle borderStyle;
    private int borderWidth;
    private Color borderColor;
    private int borderRadius;

    public CSSBorderStyle getBorderStyle() {
        return borderStyle;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public int getBorderRadius() {
        return borderRadius;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
    }

    public void setBorderStyle(CSSBorderStyle borderStyle) {
        this.borderStyle = borderStyle;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

}
