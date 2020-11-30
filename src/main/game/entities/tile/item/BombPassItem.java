package entities.tile.item;

import entities.Entity;
import entities.animatedEntity.Bomber;
import javafx.scene.image.Image;

public class BombPassItem extends Item{
    public BombPassItem(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public boolean collide(Entity e) {

        if (e instanceof Bomber) {
            ((Bomber) e).bombPassItem();
            remove();
        }
        return false;
    }
}
