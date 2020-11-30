package entities.tile;

import entities.Entity;
import entities.animatedEntity.Bomber;
import entities.animatedEntity.bomb.Bomb;
import entities.animatedEntity.enemy.Enemy;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import level.LevelLoader;

import java.util.ArrayList;
import java.util.List;

public class Portal extends Tile {

    private List<Entity> entities;
    private List<Entity> stillObjects;
    private List<Bomb> bombs;

    public Portal(int xUnit, int yUnit, Image img, List<Entity> e, List<Entity> s, List<Bomb> b) {
        super(xUnit, yUnit, img);
        entities = e;
        stillObjects = s;
        bombs = b;
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Bomber) {
            if (isAllEnemyDead()) {
                ((Bomber) e).resetProperties();
                LevelLoader.loadLevel(++LevelLoader.level, entities, stillObjects, bombs);
            }
        }
        return true;
    }

    private boolean isAllEnemyDead() {
        Entity e;
        for (int i = 0; i < entities.size(); ++i) {
            e = entities.get(i);
            if (e instanceof Enemy) {
                return false;
            }
        }
        return true;
    }
}
