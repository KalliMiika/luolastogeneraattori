package luolastogeneraattori.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import luolastogeneraattori.objects.Cave;
import luolastogeneraattori.objects.Raport;
import luolastogeneraattori.utils.CorridorList;
import luolastogeneraattori.utils.RoomList;

public class Graph extends Application {

    private static final int WIDTH = 900;
    private static final int HEIGHT = 800;

    private Stage stage;
    private Pane screen;
    private Scene menu;
    
    Canvas canvas;
    GraphicsContext gc;
    
    VBox box;
        
    private Text text;

    private static CorridorList corridors;
    private static RoomList rooms;
    private static RoomList largeRooms;

    private void drawMap() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.TEAL);
        gc.setFill(Color.TEAL);
        char[][] map = Cave.getInstance().getMap();
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[row].length; column++) {
                if (map[row][column] == '.' 
                        || map[row][column] == '+' 
                        || map[row][column] == '#') {
                    int x = column * 10;
                    int y = row * 10;
                    gc.fillRect(x, y, 10, 10);
                }
            }
        }
    }
    
    TextField param1;
    TextField param2;
    TextField param3;
    TextField param4;
    TextField param5;
    TextField param6;
    
    Text param1Header;
    Text param2Header;
    Text param3Header;
    Text param4Header;
    Text param5Header;
    Text param6Header;
    
    private void setParameters(String type) {
        box.getChildren().clear();
        Button generate = new Button("Generate");
        generate.setTranslateY(10);
        switch(type) {
            case "RANDOMWALK":
                box.getChildren().add(param1Header);
                box.getChildren().add(param1);
                box.getChildren().add(param2Header);
                box.getChildren().add(param2);
                box.getChildren().add(param3Header);
                box.getChildren().add(param3);
                
                param1Header.setText("Tunnels");
                param2Header.setText("Max Length");
                param3Header.setText("Min Length");
                
                param1.setText("100");
                param2.setText("20");
                param3.setText("3");
                
                generate.setOnAction(e -> {
                    Raport raport = Cave.generateUsingRandomWalk(Integer.parseInt(param1.getText())
                            , Integer.parseInt(param2.getText())
                            , Integer.parseInt(param3.getText())
                    );
                    System.out.println(raport);
                    text.setText("Time: " + raport.time + "ms\nMemory: " + raport.memory + "b");
                    drawMap();
                });
                box.getChildren().add(generate);
                break;
            case "CELLULARAUTOMATA":
                box.getChildren().add(param1Header);
                box.getChildren().add(param1);
                box.getChildren().add(param2Header);
                box.getChildren().add(param2);
                box.getChildren().add(param3Header);
                box.getChildren().add(param3);
                box.getChildren().add(param4Header);
                box.getChildren().add(param4);
                box.getChildren().add(param5Header);
                box.getChildren().add(param5);
                box.getChildren().add(param6Header);
                box.getChildren().add(param6);
                
                param1Header.setText("P(Wall)");
                param2Header.setText("Iterations");
                param3Header.setText("Range 1");
                param4Header.setText("Cutoff 1");
                param5Header.setText("Range 2");
                param6Header.setText("Cutoff 2");
                
                param1.setText("40");
                param2.setText("15");
                param3.setText("1");
                param4.setText("5");
                param5.setText("3");
                param6.setText("4");
                
                generate.setOnAction(e -> {
                    Raport raport = Cave.generateUsingCellularAutomata(Integer.parseInt(param1.getText())
                            , Integer.parseInt(param2.getText())
                            , Integer.parseInt(param3.getText())
                            , Integer.parseInt(param4.getText())
                            , Integer.parseInt(param5.getText())
                            , Integer.parseInt(param6.getText())
                    );
                    System.out.println(raport);
                    text.setText("Time: " + raport.time + "ms\nMemory: " + raport.memory + "b");
                    drawMap();
                });
                
                box.getChildren().add(generate);
                break;
            case "TBD":
                box.getChildren().add(param1Header);
                box.getChildren().add(param1);
                box.getChildren().add(param2Header);
                box.getChildren().add(param2);
                box.getChildren().add(param3Header);
                box.getChildren().add(param3);
                
                param1Header.setText("Rooms");
                param2Header.setText("Spanning Tree Type");
                param3Header.setText("Tree Cutoff");
                
                param1.setText("18");
                param2.setText("BASIC");
                param3.setText("15");
                
                generate.setOnAction(e -> {
                    Raport raport = Cave.generateUsingCaveGenerator(Integer.parseInt(param1.getText())
                            , param2.getText()
                            , Integer.parseInt(param3.getText())
                    );
                    System.out.println(raport);
                    text.setText("Time: " + raport.time + "ms\nMemory: " + raport.memory + "b");
                    drawMap();
                });
                
                box.getChildren().add(generate);
                break;
            case "TINYKEEPISH":
                box.getChildren().add(param1Header);
                box.getChildren().add(param1);
                box.getChildren().add(param2Header);
                box.getChildren().add(param2);
                box.getChildren().add(param3Header);
                box.getChildren().add(param3);
                box.getChildren().add(param4Header);
                box.getChildren().add(param4);
                box.getChildren().add(param5Header);
                box.getChildren().add(param5);
                
                param1Header.setText("Rooms");
                param2Header.setText("Rounds");
                param3Header.setText("Large Cutoff");
                param4Header.setText("Spanning Tree Type");
                param5Header.setText("Tree Cutoff");
                
                param1.setText("100");
                param2.setText("3");
                param3.setText("30");
                param4.setText("BASIC");
                param5.setText("15");
                
                generate.setOnAction(e -> {
                    Raport raport = Cave.generateUsingTinyKeepish(Integer.parseInt(param1.getText())
                            , Integer.parseInt(param2.getText())
                            , Integer.parseInt(param3.getText())
                            , param4.getText()
                            , Integer.parseInt(param5.getText())
                    );
                    System.out.println(raport);
                    text.setText("Time: " + raport.time + "ms\nMemory: " + raport.memory + "b");
                    drawMap();
                });
                
                box.getChildren().add(generate);
                break;
        }
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Delaunay");
        this.stage = stage;
        screen = new Pane();
        canvas = new Canvas(WIDTH, HEIGHT);
        box = new VBox();
        box.setTranslateX(WIDTH);
        box.setTranslateY(340);
        gc = canvas.getGraphicsContext2D();
        param1 = new TextField();
        param2 = new TextField();
        param3 = new TextField();
        param4 = new TextField();
        param5 = new TextField();
        param6 = new TextField();
        param1Header = new Text();
        param2Header = new Text();
        param3Header = new Text();
        param4Header = new Text();
        param5Header = new Text();
        param6Header = new Text();
        param1Header.setFill(Color.TEAL);
        param2Header.setFill(Color.TEAL);
        param3Header.setFill(Color.TEAL);
        param4Header.setFill(Color.TEAL);
        param5Header.setFill(Color.TEAL);
        param6Header.setFill(Color.TEAL);
        param1Header.setFont(Font.font ("Verdana", 20));
        param2Header.setFont(Font.font ("Verdana", 20));
        param3Header.setFont(Font.font ("Verdana", 20));
        param4Header.setFont(Font.font ("Verdana", 20));
        param5Header.setFont(Font.font ("Verdana", 20));
        param6Header.setFont(Font.font ("Verdana", 20));
        Button start = new Button("Random Walk");
        start.setStyle("-fx-font-size: 25");
        start.setPadding(new Insets(10, 10, 10, 10));
        start.setLayoutX(WIDTH);
        start.setLayoutY(20);
        start.setOnAction(e->{
            setParameters("RANDOMWALK");
        });
        Button start2 = new Button("Cellular Automata");
        start2.setStyle("-fx-font-size: 25");
        start2.setPadding(new Insets(10, 10, 10, 10));
        start2.setLayoutX(WIDTH);
        start2.setLayoutY(90);
        start2.setOnAction(e->{
            setParameters("CELLULARAUTOMATA");
        });
        Button start3 = new Button("TBD");
        start3.setStyle("-fx-font-size: 25");
        start3.setPadding(new Insets(10, 10, 10, 10));
        start3.setLayoutX(WIDTH);
        start3.setLayoutY(160);
        start3.setOnAction(e->{
            setParameters("TBD");
        });
        Button start4 = new Button("Tiny Keep-ish");
        start4.setStyle("-fx-font-size: 25");
        start4.setPadding(new Insets(10, 10, 10, 10));
        start4.setLayoutX(WIDTH);
        start4.setLayoutY(230);
        start4.setOnAction(e->{
            setParameters("TINYKEEPISH");
        });
        
        text = new Text(WIDTH, 700, "Time: 0ms\nMemory: 0b");
        text.setFill(Color.GREEN);
        
        screen.getChildren().add(canvas);
        screen.getChildren().add(start);
        screen.getChildren().add(start2);
        screen.getChildren().add(start3);
        screen.getChildren().add(start4);
        screen.getChildren().add(box);
        screen.getChildren().add(text);
        this.menu = new Scene(screen, 1200, HEIGHT, Color.BLACK);
        stage.setScene(this.menu);
        stage.show();
        screen.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
    }

    public static void main() {
        launch();
    }
}


//        for (Room r : rooms.toArray()) {
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
//            //gc.strokeRect(x, y, width, height);
//            gc.fillRect(x, y, width, height);
//        }
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
//
//        for (Corridor c : corridors.toArray()) {
//            gc.strokeLine(c.getFrom().getCenter().getX() * 10, c.getFrom().getCenter().getY() * 10, c.getTo().getCenter().getX() * 10, c.getTo().getCenter().getY() * 10);
//        }
