import entities.animatedEntity.Bomber;
import entities.animatedEntity.bomb.Bomb;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import entities.Entity;
import graphics.Sprite;
import level.LevelLoader;
import sound.Sound;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


public class BombermanGame extends Application {

    private Scene levelScene;
    private Scene inGameScene;

    public double timer_change = 1;
    
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    
    private GraphicsContext gc;
    private Canvas canvas;
    private static List<Entity> entities = new ArrayList<>();
    private static List<Entity> stillObjects = new ArrayList<>();
    private static List<Bomb> bombs = new ArrayList<>();

    private final long[] frameTimes = new long[100];
    private int frameTimeIndex = 0 ;
    private boolean arrayFilled = false;

    private final Sound sound_background = new Sound("src\\main\\resources\\sound\\title_screen.mp3", true);
    private final Sound stage_start = new Sound("src\\main\\resources\\sound\\stage_start.mp3", false);
    private final Sound stage_theme = new Sound("src\\main\\resources\\sound\\stage_theme.mp3", true);
    private final Sound game_over = new Sound("src\\main\\resources\\sound\\game_over.mp3", true);
    private final Sound all_enemy_dead = new Sound("src\\main\\resources\\sound\\all_enemies_slain.wav", false);
    private final Sound find_the_exit = new Sound("src\\main\\resources\\sound\\find_the_exit.mp3", true);

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws MalformedURLException {
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

        /* Tao menuScene */
        //Anh menu
        File file = new File("src\\main\\resources\\textures\\menu.png");
        Image image = new Image(file.toURI().toURL().toString());
        ImageView imageView = new ImageView(image);
        //Anh nut play
        File file1 = new File("src\\main\\resources\\textures\\start.jpg");
        Image image1 = new Image(file1.toURI().toURL().toString());
        ImageView imageView1 = new ImageView(image1);
        //Nut play
        Button button = new Button("", imageView1);
        button.setStyle("-fx-background-color: #FF6347");
        button.setTranslateX(300);
        button.setTranslateY(300);
        button.setScaleX(0.5);
        button.setScaleY(0.5);
        //Scene cho menu
        AnchorPane an = new AnchorPane(imageView,button);
        Scene menuScene = new Scene(an);
        // Them scene vao stage
        stage.setScene(menuScene);
        stage.setResizable(false);
        stage.setX(250);
        stage.setY(50);
        stage.setTitle("Bomberman nhà làm");
        stage.show();
        //Chạy sound menu
        sound_background.playSound();

        /* Tao scene LevelLoader.level va scene ingame */
        Group root1 = new Group();
        levelScene = new Scene(root1,900, 700, Color.BLACK);
        final Text text1 = new Text(350, 350, "LEVEL " + LevelLoader.level);
        text1.setFill(Color.CHOCOLATE);
        text1.setFont(Font.font ("Verdana", 50));
        root1.getChildren().add(text1);
        //Bam nut play
        button.setOnAction(event -> {
            inGameScene = new Scene(root);
            sound_background.stopSound();
            stage_start.playSound();
            stage.setScene(levelScene);
            LevelLoader.loadLevel(entities, stillObjects, bombs);
            update(inGameScene);
            AnimationTimer timer1 = new AnimationTimer() {
                @Override
                public void handle(long l) {
                    timer_change -= 0.005;
                    if (timer_change <= 0) {
                        stop();
                        stage.setX(50);
                        timer_change = 1;
                        stage_start.stopSound();
                        stage_theme.playSound();
                        stage.setScene(inGameScene);
                        stage.show();
                    }
                }
            };
            timer1.start();
        });
        //Tao game over scene
        Scene gameOver;
        Group group2 = new Group();
        gameOver = new Scene(group2,900, 700, Color.BLACK);
        final Text text2 = new Text(275, 350, "GAME OVER!");
        text2.setFill(Color.TAN);
        text2.setFont(Font.font("Verdana", 50));

        Button button1 = new Button("PLAY AGAIN");
        button1.setTranslateX(400);
        button1.setTranslateY(500);
        button1.setScaleX(2);
        button1.setScaleY(2);
        group2.getChildren().addAll(button1, text2);
        button1.setOnAction(event -> {
            text1.setText("LEVEL " + LevelLoader.level);
            game_over.stopSound();
            stage_start.playSound();
            stage.setScene(levelScene);
            update(inGameScene);
            AnimationTimer timer1 = new AnimationTimer() {
                @Override
                public void handle(long l) {
                    timer_change -= 0.005;
                    if (timer_change <= 0) {
                        stop();
                        stage.setX(50);
                        timer_change = 1;
                        stage_start.stopSound();
                        stage_theme.playSound();
                        stage.setScene(inGameScene);
                        stage.show();
                        LevelLoader.loadLevel(entities, stillObjects, bombs);
                    }
                }
            };
            timer1.start();
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (stage.getScene() == inGameScene) {
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
                        stage.setTitle(String.format("Bomberman nhà làm       FPS: %.0f", frameRate));
                        label.setText("LEVEL " + LevelLoader.level);
                    }
                    update(inGameScene);
                    render();

                    if (Bomber.isAllEnemyDead(entities)) {
                        stage_theme.stopSound();
                        all_enemy_dead.playSound();
                        find_the_exit.playSound();
                    } else {
                        find_the_exit.stopSound();
                        stage_theme.playSound();
                    }

                    if (!Bomber.isAlive) {
                        stage.setX(250);
                        stage.setScene(gameOver);
                        Bomber.isAlive = true;
                        stage_theme.stopSound();
                        game_over.playSound();
                    }
                }
            }
        };
        timer.start();
    }

    public void update(Scene scene) {
        for (int i = 0; i < entities.size(); ++i) {
            Entity entity = entities.get(i);
            if (entity.isExist()) {
                entity.update(scene);
            } else {
                entities.remove(entity);
            }
        }
        for (int i = 0; i < bombs.size(); ++i) {
            Bomb bomb = bombs.get(i);
            if (bomb.isExist()) {
                bomb.update(scene);
            } else {
                bombs.remove(bomb);
            }
        }
        for (int i = 0; i < stillObjects.size(); ++i) {
            Entity still_object = stillObjects.get(i);
            if (still_object.isExist()) {
                still_object.update(scene);
            } else {
                stillObjects.remove(still_object);
            }
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        bombs.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }


}
