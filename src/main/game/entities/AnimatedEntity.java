package entities;

import graphics.Sprite;
import javafx.scene.image.Image;

/**
 *  Vật thể có thể chuyển động.
 */
public abstract class AnimatedEntity extends Entity {

    protected int speed;
    protected boolean alive = true;
    //
    protected int dead_animation_tick = -60;

    protected int frame = 0;

    // Cứ FRAME_CYCLE khung hình thì cập nhật con nhân vật lắc lư 1 lần, FRAME_CYCLE nên chia hết cho 3 để movingSprite chạy ngon//
    public static final int FRAME_CYCLE = 30;


    protected AnimatedEntity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public AnimatedEntity(int xUnit, int yUnit, Image img) {
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

    public void kill() {
        alive = false;
    }
}
