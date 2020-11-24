package entities.tile;

import entities.Entity;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Portal extends Tile {
    public Portal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update(Scene scene) {

    }

    @Override
    public boolean collide(Entity e) {
        return true;
    }
}