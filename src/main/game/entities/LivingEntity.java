package entities;

import entities.Entity;
import graphics.Sprite;
import javafx.scene.image.Image;

/**
 *  Vật thể có thể chuyển động.
 */
public abstract class LivingEntity extends Entity {

    protected int frame = 0;

    // Cứ FRAME_CYCLE khung hình thì cập nhật con nhân vật lắc lư 1 lần, FRAME_CYCLE nên chia hết cho 3 để movingSprite chạy ngon//
    protected final int FRAME_CYCLE = 36;

    protected int speed = 2;

    public LivingEntity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    protected void animate() {
        if (frame >= FRAME_CYCLE) {
            frame = 0;
        } else {
            ++frame;
        }
    }
}
