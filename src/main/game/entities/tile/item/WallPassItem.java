package entities.tile.item;

import entities.Entity;
import entities.animatedEntity.Bomber;
import javafx.scene.image.Image;

public class WallPassItem extends Item {
    public WallPassItem(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Bomber) {
            ((Bomber) e).wallPassItem();
            remove();
        }
        return true;
    }
}
