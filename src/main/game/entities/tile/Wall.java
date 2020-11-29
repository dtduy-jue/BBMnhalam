package entities.tile;

import entities.Entity;
import javafx.scene.image.Image;

public class Wall extends Tile {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
    }

    public boolean collide(Entity e) {
        return false;
    }
}
