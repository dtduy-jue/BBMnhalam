package entities.tile.item;

import entities.Entity;
import entities.animatedEntity.Bomber;
import javafx.scene.image.Image;

public class FlamePassItem extends Item {
    public FlamePassItem(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public boolean collide(Entity e) {

        if (e instanceof Bomber) {
            ((Bomber) e).flamePassItem();
            remove();
        }
        return true;
    }
}
