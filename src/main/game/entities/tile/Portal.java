package entities.tile;

import entities.Entity;
import entities.animatedEntity.Bomber;
import entities.animatedEntity.bomb.Bomb;
import javafx.scene.image.Image;
import level.LevelLoader;

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
            if (Bomber.isAllEnemyDead(entities)) {
                ((Bomber) e).resetProperties();
                ++LevelLoader.level;
                LevelLoader.loadLevel(entities, stillObjects, bombs);
            }
        }
        return true;
    }


}
