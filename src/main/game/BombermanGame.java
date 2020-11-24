import entities.livingEntity.Bomber;
import entities.livingEntity.enemy.Oneal;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import entities.livingEntity.enemy.Balloom;
import entities.Entity;
import entities.tile.Grass;
import entities.tile.Wall;
import graphics.Sprite;
import level.LevelLoader;

import java.util.ArrayList;
import java.util.List;

// Các thay đổi code của ae//

public class BombermanGame extends Application {
    
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    
    private GraphicsContext gc;
    private Canvas canvas;
    private static List<Entity> entities = new ArrayList<>();
    private static List<Entity> stillObjects = new ArrayList<>();



    private final long[] frameTimes = new long[100];
    private int frameTimeIndex = 0 ;
    private boolean arrayFilled = false ;


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        Label label = new Label();
        Font font = Font.font("Consolas", 25);
        label.setFont(font);

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);
        root.getChildren().add(label);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        LevelLoader.loadLevel(0, entities, stillObjects);

        Entity bomberman1 = new Bomber(1, 1, Sprite.player_right.getFxImage(), stillObjects);
        entities.add(bomberman1);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                long oldFrameTime = frameTimes[frameTimeIndex];
                frameTimes[frameTimeIndex] = l;
                frameTimeIndex = (frameTimeIndex + 1) % frameTimes.length;
                if (frameTimeIndex == 0) {
                    arrayFilled = true;
                }
                if (arrayFilled) {
                    long elapsedNanos = l - oldFrameTime;
                    long elapsedNanosPerFrame = elapsedNanos / frameTimes.length;
                    double frameRate = 1_000_000_000.0 / elapsedNanosPerFrame;
                    stage.setTitle(String.format("Bomberman is running | FPS: %.3f", frameRate));
                    label.setText(String.format("%.3f", frameRate));
                }
                render();
                update(scene);


            }
        };
        timer.start();

        //createMap();







        /*
        Entity bomberman1 = new Bomber(1, 1, Sprite.player_right.getFxImage());
        Entity balloom1 = new Balloom(1, 2, Sprite.balloom_right1.getFxImage());
        Entity oneal1 = new Oneal(2, 3, Sprite.oneal_right1.getFxImage());
        entities.add(bomberman1);
        entities.add(balloom1);
        entities.add(oneal1);

         */
    }

    public void createMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                }
                else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }
    }


    public void update(Scene scene) {
        for (Entity entity : entities) {
            entity.update(scene);
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }

}
