package luolastogeneraattori.ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import luolastogeneraattori.cavegenerators.TinyKeepish;
import luolastogeneraattori.objects.Cave;
import luolastogeneraattori.objects.Corridor;
import luolastogeneraattori.objects.Room;
import luolastogeneraattori.utils.Raport;
import luolastogeneraattori.utils.CorridorList;
import luolastogeneraattori.utils.RoomList;

public class Graph extends Application {

    private static final int WIDTH = 900;
    private static final int HEIGHT = 660;

    private int scale = 5;

    private Stage stage;
    private Pane screen;
    private Scene menu;

    Canvas canvas;
    GraphicsContext gc;

    VBox box;
    VBox demobox;

    private Text text;

    private static CorridorList corridors;
    private static RoomList rooms;
    private static RoomList largeRooms;

    private void drawMap() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.DARKGREEN);
        char[][] map = Cave.getInstance().getMap();
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[row].length; column++) {
                if (map[row][column] == '.'
                        || map[row][column] == '+'
                        || map[row][column] == '#') {
                    int x = column * scale;
                    int y = row * scale;
                    gc.fillRect(x, y, scale, scale);
                }
            }
        }
    }

    private void drawDemo() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.DARKGREEN);
        for (Room r : rooms.toArray()) {
            int x = r.getCenter().getX() * scale;
            int y = r.getCenter().getY() * scale;
            int width = r.getWidth() * scale;
            int height = r.getHeight() * scale;
            x -= width / 2;
            y -= height / 2;
            if (r.getWidth() % 2 == 0) {
                x -= 4;
            }
            if (r.getHeight() % 2 == 0) {
                y -= 4;
            }
            gc.strokeRect(x, y, width, height);
        }

        gc.setStroke(Color.MAGENTA);
        for (Room r : largeRooms.toArray()) {
            int x = r.getCenter().getX() * scale;
            int y = r.getCenter().getY() * scale;
            int width = r.getWidth() * scale;
            int height = r.getHeight() * scale;
            x -= width / 2;
            y -= height / 2;
            if (r.getWidth() % 2 == 0) {
                x -= 4;
            }
            if (r.getHeight() % 2 == 0) {
                y -= 4;
            }
            x--;
            y--;
            gc.strokeRect(x, y, width, height);
        }
        gc.setStroke(Color.GREEN);
        for (Corridor c : corridors.toArray()) {
            gc.strokeLine(c.getFrom().getCenter().getX() * scale, c.getFrom().getCenter().getY() * scale, c.getTo().getCenter().getX() * scale, c.getTo().getCenter().getY() * scale);
        }
    }

    private void demoTinyKeepIsh(int roomsToGenerate, String generationMethod, String collisionMethod, int largeCutoff, String spanningTreeType, int treeCutOff) {
        demobox.getChildren().clear();
        TinyKeepish gen = new TinyKeepish();
        Cave c = new Cave();
        gen.generateRooms(roomsToGenerate, generationMethod);
        largeRooms = new RoomList();
        corridors = new CorridorList();
        rooms = gen.getRooms();
        drawDemo();
        phase = "COLLISION";
        Button nxt = new Button("Spam This");
        nxt.setTranslateX(0);
        nxt.setTranslateY(-10);
        nxt.setOnAction(e -> {
            demoTinyKeepishNext(roomsToGenerate, collisionMethod, gen, largeCutoff, spanningTreeType, treeCutOff);
        });
        demobox.getChildren().add(nxt);
    }

    private String phase;

    private void demoTinyKeepishNext(int roomsToGenerate, String collisionMethod, TinyKeepish gen, int largeCutoff, String spanningTreeType, int treeCutOff) {
        switch (phase) {
            case "COLLISION":
                int collisionCount = gen.checkCollisions(collisionMethod);
                if (collisionCount == 0) {
                    phase = "LARGEROOMS";
                    demobox.getChildren().remove(0);
                    Button nxt = new Button("Next");
                    nxt.setTranslateX(75);
                    nxt.setTranslateY(-10);
                    nxt.setOnAction(e -> {
                        demoTinyKeepishNext(roomsToGenerate, collisionMethod, gen, largeCutoff, spanningTreeType, treeCutOff);
                    });
                    demobox.getChildren().add(nxt);
                }
                rooms = gen.getRooms();
                drawDemo();
                break;
            case "LARGEROOMS":
                gen.findLargeRooms(largeCutoff);
                largeRooms = gen.getLargeRooms();
                drawDemo();
                phase = "DELAUNAY";
                break;
            case "DELAUNAY":
                gen.runDelaunay();
                corridors = gen.getCorridors();
                drawDemo();
                phase = "SPANNINGTREE";
                break;
            case "SPANNINGTREE":
                gen.generateCorridors(roomsToGenerate, spanningTreeType, treeCutOff);
                corridors = gen.getCorridors();
                drawDemo();
                phase = "FILLGAPS";
                break;
            case "FILLGAPS":
                gen.fillGaps(roomsToGenerate);
                gen.solveRoomLocations();
                drawDemo();
                phase = "GENERATERESULT";
                break;
            case "GENERATERESULT":
                gen.generateResult();
                rooms = gen.getRooms();
                drawDemo();
                phase = "GENERATEMAP";
                break;
            case "GENERATEMAP":
                drawMap();
                phase = "DONE";
                demobox.getChildren().remove(0);
                break;
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

    Text collisionMethodBoxHeader;

    ObservableList<String> spanningTrees;
    ComboBox<String> spanningTreeBox;
    ObservableList<String> generationMethod;
    ComboBox<String> generationMethodBox;
    ObservableList<String> collisionMethod;
    ComboBox<String> collisionMethodBox;

    private void setParameters(String type) {
        box.getChildren().clear();
        Button generate = new Button("Generate");
        generate.setTranslateY(10);
        Button demo = new Button("Demo");
        demo.setTranslateX(75);
        demo.setTranslateY(-15);
        switch (type) {
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

                param1.setText("2000");
                param2.setText("10");
                param3.setText("3");

                generate.setOnAction(e -> {
                    Raport raport = Cave.generateUsingRandomWalk(Integer.parseInt(param1.getText()),
                             Integer.parseInt(param2.getText()),
                             Integer.parseInt(param3.getText())
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

                param1.setText("45");
                param2.setText("3");
                param3.setText("1");
                param4.setText("5");
                param5.setText("3");
                param6.setText("4");

                generate.setOnAction(e -> {
                    Raport raport = Cave.generateUsingCellularAutomata(Integer.parseInt(param1.getText()),
                             Integer.parseInt(param2.getText()),
                             Integer.parseInt(param3.getText()),
                             Integer.parseInt(param4.getText()),
                             Integer.parseInt(param5.getText()),
                             Integer.parseInt(param6.getText())
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
                box.getChildren().add(collisionMethodBoxHeader);
                box.getChildren().add(collisionMethodBox);
                box.getChildren().add(param2Header);
                box.getChildren().add(spanningTreeBox);
                box.getChildren().add(param3Header);
                box.getChildren().add(param3);

                param1Header.setText("Rooms");
                collisionMethodBoxHeader.setText("Collision");
                param2Header.setText("Spanning Tree");
                param3Header.setText("Tree Cutoff");

                param1.setText("40");
                collisionMethodBox.setValue("SPHERE");
                spanningTreeBox.setValue("BASIC");
                param3.setText("15");

                generate.setOnAction(e -> {
                    Raport raport = Cave.generateUsingCaveGenerator(Integer.parseInt(param1.getText()),
                             collisionMethodBox.getValue(),
                             spanningTreeBox.getValue(),
                             Integer.parseInt(param3.getText())
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
                box.getChildren().add(generationMethodBox);
                box.getChildren().add(collisionMethodBoxHeader);
                box.getChildren().add(collisionMethodBox);
                box.getChildren().add(param3Header);
                box.getChildren().add(param3);
                box.getChildren().add(param4Header);
                box.getChildren().add(spanningTreeBox);
                box.getChildren().add(param5Header);
                box.getChildren().add(param5);

                param1Header.setText("Rooms");
                param2Header.setText("Generation");
                collisionMethodBoxHeader.setText("Collision");
                param3Header.setText("Large Cutoff");
                param4Header.setText("Spanning Tree");
                param5Header.setText("Tree Cutoff");

                param1.setText("100");
                generationMethodBox.setValue("SPIRAL");
                collisionMethodBox.setValue("SQUARE");
                param3.setText("30");
                spanningTreeBox.setValue("BASIC");
                param5.setText("15");

                generate.setOnAction(e -> {
                    Raport raport = Cave.generateUsingTinyKeepish(Integer.parseInt(param1.getText()),
                             generationMethodBox.getValue(),
                             collisionMethodBox.getValue(),
                             Integer.parseInt(param3.getText()),
                             spanningTreeBox.getValue(),
                             Integer.parseInt(param5.getText())
                    );
                    System.out.println(raport);
                    text.setText("Time: " + raport.time + "ms\nMemory: " + raport.memory + "b");
                    drawMap();
                });
                demo.setOnAction(e -> {
                    demoTinyKeepIsh(Integer.parseInt(param1.getText()),
                             generationMethodBox.getValue(),
                             collisionMethodBox.getValue(),
                             Integer.parseInt(param3.getText()),
                             spanningTreeBox.getValue(),
                             Integer.parseInt(param5.getText()));
                });

                box.getChildren().add(generate);
                box.getChildren().add(demo);
                box.getChildren().add(demobox);
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
        demobox = new VBox();
        box.setTranslateX(WIDTH);
        box.setTranslateY(185);
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
        collisionMethodBoxHeader = new Text();
        param1Header.setFill(Color.TEAL);
        param2Header.setFill(Color.TEAL);
        param3Header.setFill(Color.TEAL);
        param4Header.setFill(Color.TEAL);
        param5Header.setFill(Color.TEAL);
        param6Header.setFill(Color.TEAL);
        collisionMethodBoxHeader.setFill(Color.TEAL);
        param1Header.setFont(Font.font("Verdana", 20));
        param2Header.setFont(Font.font("Verdana", 20));
        param3Header.setFont(Font.font("Verdana", 20));
        param4Header.setFont(Font.font("Verdana", 20));
        param5Header.setFont(Font.font("Verdana", 20));
        param6Header.setFont(Font.font("Verdana", 20));
        collisionMethodBoxHeader.setFont(Font.font("Verdana", 20));
        spanningTrees = FXCollections.observableArrayList("BASIC", "RANDOM");
        spanningTreeBox = new ComboBox<>(spanningTrees);
        generationMethod = FXCollections.observableArrayList("SPIRAL", "STACK");
        generationMethodBox = new ComboBox<>(generationMethod);
        collisionMethod = FXCollections.observableArrayList("SPHERE", "SQUARE");
        collisionMethodBox = new ComboBox<>(collisionMethod);
        VBox buttonbox = new VBox();
        buttonbox.setLayoutX(WIDTH);
        buttonbox.setLayoutY(20);
        buttonbox.setPrefWidth(140);
        Button start = new Button("Random Walk");
        start.setStyle("-fx-font-size: 15");
        start.setPadding(new Insets(10, 10, 10, 10));
        start.setMinWidth(140);
        start.setOnAction(e -> {
            setParameters("RANDOMWALK");
        });
        Button start2 = new Button("CellularAutomata");
        start2.setStyle("-fx-font-size: 15");
        start2.setPadding(new Insets(10, 10, 10, 10));
        start2.setMinWidth(140);
        start2.setOnAction(e -> {
            setParameters("CELLULARAUTOMATA");
        });
        Button start3 = new Button("TBD");
        start3.setStyle("-fx-font-size: 15");
        start3.setPadding(new Insets(10, 10, 10, 10));
        start3.setMinWidth(140);
        start3.setOnAction(e -> {
            setParameters("TBD");
        });
        Button start4 = new Button("Tiny Keep-ish");
        start4.setStyle("-fx-font-size: 15");
        start4.setPadding(new Insets(10, 10, 10, 10));
        start4.setMinWidth(140);
        start4.setOnAction(e -> {
            setParameters("TINYKEEPISH");
        });

        text = new Text(WIDTH, 600, "Time: 0ms\nMemory: 0b");
        text.setFill(Color.GREEN);

        screen.getChildren().add(canvas);
        buttonbox.getChildren().add(start);
        buttonbox.getChildren().add(start2);
        buttonbox.getChildren().add(start3);
        buttonbox.getChildren().add(start4);
        screen.getChildren().add(buttonbox);
        screen.getChildren().add(box);
        screen.getChildren().add(text);
        this.menu = new Scene(screen, 1060, HEIGHT, Color.BLACK);
        stage.setScene(this.menu);
        stage.show();
        screen.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
