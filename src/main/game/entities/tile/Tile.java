package entities.tile;

import entities.Entity;
import graphics.Sprite;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Tile extends Entity {

    public Tile( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    @Override
    public abstract boolean collide(Entity e);

    @Override
    public void update(Scene scene) {
    }

    public void render(GraphicsContext gc) {
        super.render(gc);
    }

}
