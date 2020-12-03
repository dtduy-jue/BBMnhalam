package entities.tile;

import entities.AnimatedEntity;
import entities.Entity;
import entities.animatedEntity.Bomber;
import entities.animatedEntity.bomb.Flame;
import entities.animatedEntity.enemy.Minvo;
import graphics.Sprite;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Brick extends AnimatedEntity {
    public static boolean wallPassItem = false;
    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        dead_animation_tick = FRAME_CYCLE / 2;
    }

    public void update(Scene scene) {
        if (!alive) {
            if (dead_animation_tick > 0) {
                img = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, frame, FRAME_CYCLE / 2).getFxImage();
                animate();
                --dead_animation_tick;
            } else {
                remove();
            }
        }

    }

    public boolean collide(Entity e) {
        if (e instanceof Flame) {
            alive = false;
        }
        if (wallPassItem && e instanceof Bomber)
            return true;
        return e instanceof Minvo;
    }
}
