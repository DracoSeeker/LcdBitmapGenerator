package inc.draco;

import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

import java.util.LinkedList;

public class Block extends VBox {
    private LinkedList<Cell> cells;

    public Block(DoubleProperty width) {
        super();
        cells = new LinkedList<>();
        int spacing = 0;
        setSpacing(spacing * 2);
        setPadding(new Insets(spacing));
        for (int i = 0; i < 8; i++) {
            Cell c = new Cell(width.subtract(80.0 + spacing * 2 * 128.0));
            getChildren().add(c);
            cells.add(0,c);
        }
    }

    public String getHex() {
        int num = 0;
        for (Cell cell : cells) {
            num = num << 1 | cell.isBlack();
        }
        return "0x" + Integer.toHexString(num);
    }

    public void clear() {
        for (Cell cell : cells) {
            cell.clear();
        }
    }
}
