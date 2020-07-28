package inc.draco;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {

    private boolean isBlack = false;

    public Cell(DoubleBinding width) {
        super();
        setFill(Color.WHITE);
        widthProperty().bind(width.divide(128.0));
        heightProperty().bind(widthProperty());

        setOnMouseExited(event -> action2(event));
    }

    private void action() {
        if(isBlack) setFill(Color.WHITE);
        else setFill(Color.BLACK);
        isBlack = !isBlack;
    }

    private void action2(MouseEvent e) {
        if(e.isShiftDown()) {
            setFill(Color.BLACK);
            isBlack = true;
            System.out.println("black");
        }
        else if(e.isControlDown()) {
            setFill(Color.WHITE);
            isBlack = false;
            System.out.println("white");
        }
    }

    public int isBlack() {
        return  (isBlack) ? 0b1 : 0b0;
    }

    public void clear() {
        setFill(Color.WHITE);
        isBlack = false;
    }
}
