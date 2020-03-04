package luolastogeneraattori.ui;

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
import luolastogeneraattori.utils.RoomList;

public class Graph extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    private Stage stage;
    private Pane screen;
    private Scene menu;

    private static CorridorList corridors;
    private static RoomList rooms;
    private static RoomList largeRooms;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Delaunay");
        this.stage = stage;
        screen = new Pane();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.TEAL);
        gc.setFill(Color.TEAL);
        gc.setLineWidth(1);

        for (Room r : rooms.toArray()) {
            int x = r.getCenter().getX() * 10;
            int y = r.getCenter().getY() * 10;
            int width = r.getWidth() * 10;
            int height = r.getHeight() * 10;
            x -= width / 2;
            y -= height / 2;
            if (r.getWidth() % 2 == 0) {
                x -= 4;
            }
            if (r.getHeight() % 2 == 0) {
                y -= 4;
            }
            //gc.strokeRect(x, y, width, height);
            gc.fillRect(x, y, width, height);
        }

        gc.setStroke(Color.MAGENTA);
        gc.setLineWidth(2);
//
//        for (Room r : largeRooms.toArray()) {
//            int x = r.getCenter().getX() * 10;
//            int y = r.getCenter().getY() * 10;
//            int width = r.getWidth() * 10;
//            int height = r.getHeight() * 10;
//            x -= width / 2;
//            y -= height / 2;
//            if (r.getWidth() % 2 == 0) {
//                x -= 4;
//            }
//            if (r.getHeight() % 2 == 0) {
//                y -= 4;
//            }
//            x--;
//            y--;
//            gc.strokeRect(x, y, width, height);
//        }

        gc.setStroke(Color.GREEN);
        gc.setLineWidth(2);
//
//        for (Corridor c : corridors.toArray()) {
//            gc.strokeLine(c.getFrom().getCenter().getX() * 10, c.getFrom().getCenter().getY() * 10, c.getTo().getCenter().getX() * 10, c.getTo().getCenter().getY() * 10);
//        }
        screen.getChildren().add(canvas);
        this.menu = new Scene(screen, WIDTH, HEIGHT, Color.BLACK);
        stage.setScene(menu);
        stage.show();
    }

    public static void main(RoomList param1, RoomList param2, CorridorList param3) {
        rooms = param1;
        largeRooms = param2;
        corridors = param3;
        launch();
    }
}
