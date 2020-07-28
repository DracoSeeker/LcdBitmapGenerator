package inc.draco;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

/**
 * JavaFX App
 */
public class App extends Application {

    Stage stage;
        Scene scene;
            BorderPane root;
                MenuBar menubar;
                    Menu editMenu;
                GridPane grid;
                Block[][] blocks;

    @Override
    public void start(Stage primaryStage) {
                    grid = new GridPane();
//                    grid.setBackground(new Background(new BackgroundFill(Color.DIMGREY, null, null)));
                    grid.setAlignment(Pos.CENTER);
//                    blocks = new Block[8][128];

                    editMenu = new Menu("Edit");


                    menubar = new MenuBar(editMenu);


                root = new BorderPane();
                root.setBackground(new Background(new BackgroundFill(Color.rgb(97,133,48), CornerRadii.EMPTY, Insets.EMPTY)));
                root.setTop(menubar);
                root.setCenter(grid);


            scene = new Scene(root);

        stage = primaryStage;
        setupStage();
        stage.setScene(scene);

        scene.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                System.out.println("enter");
                getBitmap();
            }
            else if(event.getCode() == KeyCode.ESCAPE && event.isAltDown()) {
                System.out.println("esc");
                clear();
            }

        });


        grid.maxWidthProperty().bind(stage.widthProperty().subtract(20));
        grid.maxHeightProperty().bind(grid.maxWidthProperty().divide(2));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 128; j++) {
                Block b = new Block(grid.maxWidthProperty());
                grid.add(b, j, i);
                blocks[i][j] = b;
            }
        }
    }

    private void getBitmap() {
        StringBuilder out = new StringBuilder();
        out.append("uint8_t bitmap[1024] = {\n");
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                out.append(blocks[i][j].getHex() + ", ");
            }
            out.append("\n");
        }
        out.append("};");

        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(
                        new StringSelection(out.toString()),
                        null
                );
    }

    private void clear() {
        for (Block[] block : blocks) {
            for (Block block1 : block) {
                block1.clear();
            }
        }
    }

    private void setupStage() {
        stage.setHeight(600);
        stage.setWidth(1000);
        stage.setTitle("Bitmap Generator for ST7565 LCD");
        stage.show();
    }



}