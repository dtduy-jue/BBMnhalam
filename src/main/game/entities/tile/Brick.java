package entities.tile;

import entities.Entity;
import javafx.scene.image.Image;

public class Brick extends Tile {

    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public boolean collide(Entity e) {
        return false;
    }
}
