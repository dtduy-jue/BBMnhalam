package entities.animatedEntity.bomb;

import entities.AnimatedEntity;
import entities.Entity;
import entities.tile.Wall;
import graphics.Sprite;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

/*
    Lớp explosion: Thảm lửa dải ra từ vụ nổ
 */
public class Explosion extends AnimatedEntity {

    //Hướng nổ
    protected int direction;
    //Độ dài
    protected int length;
    //Toạ độ của tâm vụ nổ
    protected int anotherX, anotherY;
    //Các vệt lửa Flame của vụ nổ
    Flame[] flames;

    public Explosion(int x, int y, int length, int direction, List<Entity> stillObjects) {
        super(x, y);
        anotherX = x;
        anotherY = y;
        this.length = 0;
        while (this.length < length) {
            switch (direction) {
                case 1: anotherY -= Sprite.SCALED_SIZE; break;
                case 2: anotherX += Sprite.SCALED_SIZE; break;
                case 3: anotherY += Sprite.SCALED_SIZE; break;
                case 4: anotherX -= Sprite.SCALED_SIZE; break;
            }
            Entity e = null;
            for (Entity res : stillObjects) {
                if (res.getX() == anotherX && res.getY() == anotherY) {
                    e = res;
                    break;
                }
            }
            if (e instanceof Wall)    break;
            ++this.length;
        }
        anotherX = x;
        anotherY = y;

        flames = new Flame[this.length];
        for (int i = 0; i < flames.length; i++) {
            switch (direction) {
                case 1: anotherY -= Sprite.SCALED_SIZE;   break;
                case 2: anotherX += Sprite.SCALED_SIZE;   break;
                case 3: anotherY += Sprite.SCALED_SIZE;   break;
                case 4: anotherX -= Sprite.SCALED_SIZE;   break;
            }
            flames[i] = new Flame(anotherX, anotherY, i == flames.length - 1 ? true : false, direction);
        }
    }

    public void update(Scene scene) {
        for (int i = 0; i < flames.length; i++) {
            flames[i].update(scene);
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
        for (int i = 0; i < flames.length; i++) {
            flames[i].render(gc);
        }
    }

    public boolean collide(Entity e) {
        return true;
    }

}

