package entities.tile;

import entities.Entity;
import graphics.State;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Grass extends Tile {

    public Grass(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public boolean collide(Entity e) {
        return true;
    }
}
