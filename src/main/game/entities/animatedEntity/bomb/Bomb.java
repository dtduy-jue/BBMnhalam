package entities.animatedEntity.bomb;

import entities.AnimatedEntity;
import entities.EntitiesLayer;
import entities.Entity;
import entities.animatedEntity.Bomber;
import entities.animatedEntity.enemy.Enemy;
import entities.tile.Brick;
import graphics.Sprite;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends AnimatedEntity {

    public static int flame_length = 1;

    private int remaining_tick = 120;

    private List<Explosion> explosion = new ArrayList<>();

    private static List<Entity> stillObjects;

    private List<Entity> entities;

    public Bomb(int xUnit, int yUnit, Image img, List<Entity> e, List<Entity> entities) {
        super(xUnit, yUnit, img);
        dead_animation_tick = FRAME_CYCLE / 2;
        stillObjects = e;
        this.entities = entities;
    }

    @Override
    public void update(Scene scene) {
        if (alive) {
            if (remaining_tick > 0) {
                --remaining_tick;
            } else {
                explode();
            }
        } else {
            if (dead_animation_tick > 0) {
                updateFlame(scene);
                updateEntities(scene);
                --dead_animation_tick;
            } else {
                remove();
            }
        }
        animate();
    }

    private void updateEntities(Scene scene) {
        int i, j;
        for (i = 0; i < explosion.size(); ++i) {
            for (j = 0; j < explosion.get(i).flames.length; ++j) {
                Flame current_flame = explosion.get(i).flames[j];
                int current_flame_X = explosion.get(i).flames[j].getX();
                int current_flame_Y = explosion.get(i).flames[j].getY();
                Entity e = searchEntity(current_flame_X, current_flame_Y);
                if (e instanceof EntitiesLayer) {
                    if (((EntitiesLayer) e).getTopEntity() instanceof Brick) {
                        e.collide(current_flame);
                    }
                }
                for (Entity res : entities) {
                    if (res instanceof Enemy && Math.abs(current_flame_X - res.getX()) < (Sprite.SCALED_SIZE - 4) && Math.abs(current_flame_Y - res.getY()) < (Sprite.SCALED_SIZE - 4)) {
                        ((Enemy) res).kill();
                    } else if (res instanceof Bomber && Math.abs(current_flame_X - res.getX()) < (Sprite.SCALED_SIZE - 12) && Math.abs(current_flame_Y - res.getY()) < (Sprite.SCALED_SIZE - 4)) {
                        ((Bomber) res).kill();
                    }
                }
            }
        }
    }

    static boolean isBrickHere(int current_flame_x, int current_flame_y) {
        Entity e = searchEntity(current_flame_x, current_flame_y);
        if (e instanceof EntitiesLayer) {
            if (((EntitiesLayer) e).getTopEntity() instanceof Brick) {

                return true;
            }
        }
        return false;
    }


    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
        for (int i = 0; i < explosion.size(); ++i) {
            explosion.get(i).render(gc);
        }
    }

    @Override
    protected void animate() {
        super.animate();
        chooseSprite();
    }

    private void chooseSprite() {
        if (alive) {
            img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, frame, FRAME_CYCLE).getFxImage();
        } else {
            img = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, frame, FRAME_CYCLE / 2).getFxImage();;
        }
    }

    public boolean collide (Entity e) {
        return false;
    }

    private void updateFlame(Scene scene) {
        for (int i = 0; i < explosion.size(); ++i) {
            explosion.get(i).update(scene);
        }
    }

    private void explode() {
        alive = false;
        for (int i = 0; i < 4; ++i) {
            explosion.add(new Explosion(this.x, this.y, flame_length, i + 1, stillObjects));
        }
    }

    public static Entity searchEntity(int x, int y) {

        for (Entity res : stillObjects) {
            if (res.getX() == x && res.getY() == y) {
                return res;
            }
        }
        return null;
    }
}
