package entities;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import graphics.Sprite;
import javafx.scene.input.KeyEvent;

public class Bomber extends Entity {

    private int frame = -1;

    // Cứ FRAME_CYCLE khung hình thì cập nhật con nhân vật lắc lư 1 lần, FRAME_CYCLE nên chia hết cho 3 để movingSprite chạy ngon//
    private static final int FRAME_CYCLE = 18;

    private boolean left, right, up, down = false;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update(Scene scene) {
        move(scene);
    }

    public void animate() {
        if (frame >= FRAME_CYCLE) {
            frame = 0;
        }
        img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, ++frame, FRAME_CYCLE).getFxImage();
    }

    public void move(Scene scene) {
        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()){
                case UP:    up = true; break;
                case DOWN:  down = true; break;
                case LEFT:  left = true; break;
                case RIGHT: right = true; break;
            }
        });

        scene.setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode()){
                case UP:    up = false; break;
                case DOWN:  down = false;   break;
                case LEFT:  left = false;   break;
                case RIGHT: right = false;  break;
            }
        });

        if (left && !right)   goLeft();
        if (right && !left)  goRight();
        if (up && !down)     goUp();
        if (down && !up)   goDown();
    }


    public void goUp() {
        y--;
        if (frame >= FRAME_CYCLE) {
            frame = 0;
        }
        img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, ++frame, FRAME_CYCLE).getFxImage();
    }
    public void goDown() {
        y++;
        if (frame >= FRAME_CYCLE) {
            frame = 0;
        }
        img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, ++frame, FRAME_CYCLE).getFxImage();
    }
    public void goLeft() {
        x--;
        if (frame >= FRAME_CYCLE) {
            frame = 0;
        }
        img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, ++frame, FRAME_CYCLE).getFxImage();
    }
    public void goRight() {
        x++;
        if (frame >= FRAME_CYCLE) {
            frame = 0;
        }
        img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, ++frame, FRAME_CYCLE).getFxImage();
    }
}
