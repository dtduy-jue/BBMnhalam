package entities.livingEntity;

import entities.Entity;
import entities.LivingEntity;
import entities.tile.Brick;
import entities.tile.Wall;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 *  Người chơi.
 */
public class Bomber extends LivingEntity {

    private boolean left, right, up, down = false;

    int a = 0;

    private List<Entity> stillObjects = new ArrayList<>();

    public Bomber(int x, int y, Image img, List<Entity> e) {
        super( x, y, img);
        stillObjects = e;
    }

    @Override
    public void update(Scene scene) {
        move(scene);
        if (a == 15) {
            System.out.println((x / 48) + " " + x + "  " + (y / 48)+ " " + y);
            a = 0;
        }
        ++a;
    }

    public void move(Scene scene) {
        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()){
                case LEFT:  left = true; break;
                case RIGHT: right = true; break;
                case UP:    up = true; break;
                case DOWN:  down = true; break;
            }
        });

        scene.setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode()){
                case LEFT:  left = false;   break;
                case RIGHT: right = false;  break;
                case UP:    up = false; break;
                case DOWN:  down = false;   break;
            }
        });


        if (up && !down)     goUp();
        else if (down && !up)   goDown();
        else if (left && !right)   goLeft();
        else if (right && !left)  goRight();
    }

    public boolean collide(Entity e) {
        return false;
    }
    public void goUp() {
        this.setCanMoveUP(true);
        if (!searchEntity(((this.getX() / Sprite.SCALED_SIZE)) * Sprite.SCALED_SIZE, (((this.getY() - 1) / Sprite.SCALED_SIZE) + 1) * Sprite.SCALED_SIZE - Sprite.SCALED_SIZE).collide(this)
                || !searchEntity((((this.getX() + (int)(Sprite.SCALED_SIZE * 3 / 4) - 1) / Sprite.SCALED_SIZE)) * Sprite.SCALED_SIZE, (((this.getY() - 1) / Sprite.SCALED_SIZE) + 1) * Sprite.SCALED_SIZE - Sprite.SCALED_SIZE).collide(this)){
            this.setCanMoveUP(false);
        }
        animate();
        if (!this.canMoveUP) {
            return;
        }
        y -= speed;
        img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, frame, FRAME_CYCLE).getFxImage();
    }
    public void goDown() {
        this.setCanMoveDOWN(true);
        if (!searchEntity((this.getX()/Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE, (this.getY() / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE + Sprite.SCALED_SIZE).collide(this)
                || !searchEntity(((this.getX() + (int)(Sprite.SCALED_SIZE * 3 / 4) - 1) / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE, (this.getY() / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE + Sprite.SCALED_SIZE).collide(this)){
            this.setCanMoveDOWN(false);
        }
        animate();
        if (!this.canMoveDOWN) {
            img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, frame, FRAME_CYCLE).getFxImage();
            return;
        }
        y += speed;
        img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, ++frame, FRAME_CYCLE).getFxImage();
    }
    public void goLeft() {
        this.setCanMoveLEFT(true);
        if (!searchEntity((((this.getX() - 1) / Sprite.SCALED_SIZE) + 1) * Sprite.SCALED_SIZE - Sprite.SCALED_SIZE, (this.getY() / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE).collide(this)
                || !searchEntity((((this.getX() - 1) / Sprite.SCALED_SIZE) + 1) * Sprite.SCALED_SIZE - Sprite.SCALED_SIZE, ((this.getY() + (int)Sprite.SCALED_SIZE - 1) / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE).collide(this)){
            this.setCanMoveLEFT(false);
        }
        animate();
        if (!this.canMoveLEFT) {
            img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, ++frame, FRAME_CYCLE).getFxImage();
            return;
        }
        x -= speed;
        if (frame >= FRAME_CYCLE) {
            frame = 0;
        }
        img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, ++frame, FRAME_CYCLE).getFxImage();
    }
    public void goRight() {
        this.setCanMoveRIGHT(true);
        if (!searchEntity(((this.getX() + Sprite.SCALED_SIZE * 3 / 4) / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE, (this.getY() / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE).collide(this)
                || !searchEntity(((this.getX() + Sprite.SCALED_SIZE * 3 / 4) / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE, ((this.getY() + (int)Sprite.SCALED_SIZE - 1) / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE).collide(this)){
            this.setCanMoveRIGHT(false);
        }
        animate();
        if (!this.canMoveRIGHT) {
            img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, ++frame, FRAME_CYCLE).getFxImage();
            return;
        }
        x += speed;
        if (frame >= FRAME_CYCLE) {
            frame = 0;
        }
        img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, ++frame, FRAME_CYCLE).getFxImage();
    }
    public Entity searchEntity(int x, int y) {
        for (Entity res : stillObjects) {
            if (res.getX() == x && res.getY() == y) {
                return res;
            }
        }
        return new Brick(x, y, Sprite.brick_exploded1.getFxImage());//code sai, de tam
    }

}
