package entities.tile.item;

import entities.Entity;
import entities.livingEntity.Bomber;
import javafx.scene.image.Image;

public class BombItem extends Item {
    public BombItem(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public boolean collide(Entity e) {

        if (e instanceof Bomber) {
            // làm gì đó để bomber đặt đc 2 quả bom
            remove();
        }
        return false;
    }
}
