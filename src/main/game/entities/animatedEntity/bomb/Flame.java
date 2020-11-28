package entities.animatedEntity.bomb;

import entities.AnimatedEntity;
import entities.Entity;
import graphics.Sprite;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Flame extends AnimatedEntity {

    private boolean bound;
    private int direction;

    public Flame(int x, int y, boolean bound, int direction) {
        super(x, y);
        frame = 1;
        this.bound = bound;
        this.direction = direction;

        switch (direction) {
            case 1:
                if (bound) {
                    img = Sprite.explosion_vertical_top_last.getFxImage();
                } else {
                    img = Sprite.explosion_vertical.getFxImage();
                }
                break;
            case 3:
                if (bound) {
                    img = Sprite.explosion_vertical_down_last.getFxImage();
                } else {
                    img = Sprite.explosion_vertical.getFxImage();
                }
                break;
            case 4:
                if (bound) {
                    img = Sprite.explosion_horizontal_left_last.getFxImage();
                } else {
                    img = Sprite.explosion_horizontal.getFxImage();
                }
                break;
            case 2:
                if (bound) {
                    img = Sprite.explosion_horizontal_right_last.getFxImage();
                } else {
                    img = Sprite.explosion_horizontal.getFxImage();
                }
                break;

        }
    }

    @Override
    public void update(Scene scene) {
        if (frame <= FRAME_CYCLE / 2) {
            animate();
        } else {
            remove();
        }
    }

    @Override
    protected void animate() {
        super.animate();
        chooseSprite();
    }

    private void chooseSprite() {
        switch (direction) {
            case 1:
                if (Bomb.isBrickHere(x, y)) {
                    img = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, ++frame, FRAME_CYCLE).getFxImage();
                } else if (bound) {
                    img = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2, frame, FRAME_CYCLE / 2).getFxImage();
                } else {
                    img = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2, frame, FRAME_CYCLE / 2).getFxImage();
                }
                break;
            case 3:
                if (Bomb.isBrickHere(x, y)) {
                    img = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, ++frame, FRAME_CYCLE).getFxImage();
                } else if (bound) {
                    img = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, frame, FRAME_CYCLE / 2).getFxImage();
                } else {
                    img = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2, frame, FRAME_CYCLE / 2).getFxImage();
                }
                break;
            case 4:
                if (Bomb.isBrickHere(x, y)) {
                    img = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, ++frame, FRAME_CYCLE).getFxImage();
                } else if (bound) {
                    img = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2, frame, FRAME_CYCLE / 2).getFxImage();
                } else {
                    img = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, frame, FRAME_CYCLE / 2).getFxImage();
                }
                break;
            case 2:
                if (Bomb.isBrickHere(x, y)) {
                    img = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, ++frame, FRAME_CYCLE).getFxImage();
                } else if (bound) {
                    img = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2, frame, FRAME_CYCLE / 2).getFxImage();
                } else {
                    img = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, frame, FRAME_CYCLE / 2).getFxImage();
                }
                break;
        }
    }

    @Override
    public boolean collide(Entity e) {
        return true;
    }
}
