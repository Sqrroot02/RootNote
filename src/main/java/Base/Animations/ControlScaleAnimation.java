package Base.Animations;

import javafx.animation.FillTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class ControlScaleAnimation {
    public static void Animate(Node node){
        ScaleTransition scale = new ScaleTransition(Duration.millis(200),node);
        scale.setToX(1.05);
        scale.setToY(1.05);
        scale.setAutoReverse(true);

        ScaleTransition scaleBack = new ScaleTransition(Duration.millis(200),node);
        scaleBack.setToX(1);
        scaleBack.setToY(1);
        scaleBack.setAutoReverse(true);

        SequentialTransition sequence = new SequentialTransition();
        sequence.getChildren().addAll(scale,scaleBack);
        sequence.play();
    }

    public static void AnimateIn(Node node){
        ScaleTransition scale = new ScaleTransition(Duration.millis(200),node);
        scale.setToX(1.05);
        scale.setToY(1.05);
        scale.setAutoReverse(true);
        scale.play();
    }

    public static void AnimateOut(Node node){
        ScaleTransition scaleBack = new ScaleTransition(Duration.millis(200),node);
        scaleBack.setToX(1);
        scaleBack.setToY(1);
        scaleBack.setAutoReverse(true);
        scaleBack.play();
    }

    public static EventHandler<MouseEvent> mouseEnterEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            AnimateIn((Node)mouseEvent.getSource());
        }
    };

    public static EventHandler<MouseEvent> mouseLeaveEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            AnimateOut((Node)mouseEvent.getSource());
        }
    };
}
