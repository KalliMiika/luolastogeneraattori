package luolastogeneraattori.ui;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import luolastogeneraattori.objects.Corridor;
import luolastogeneraattori.objects.Room;
import luolastogeneraattori.utils.CorridorList;

public class Graph extends Application {

    private static final int WIDTH = 1400;
    private static final int HEIGHT = 1400;

    private Stage stage;
    private Pane screen;
    private Scene menu;
    
    private static CorridorList corridors;
    private static Room[] rooms;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Delaunay");
        this.stage = stage;
        screen = new Pane();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.GREEN);
        gc.setLineWidth(3);

        for (Corridor c : corridors.toArray()) {
            //System.out.println(c.getFrom().getId() + " - " + c.getTo().getId());
            gc.strokeLine(c.getFrom().getCenter().getX()*15, c.getFrom().getCenter().getY()*15, c.getTo().getCenter().getX()*15, c.getTo().getCenter().getY()*15);
        }
        screen.getChildren().add(canvas);
        this.menu = new Scene(screen, WIDTH, HEIGHT);
        stage.setScene(menu);
        stage.show();
    }
    
    public static void main(Room[] param1, CorridorList param2) {
        rooms = param1;
        corridors = param2;
        launch();
    }
}
