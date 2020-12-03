package entities.tile.item;

import entities.tile.Tile;
import javafx.scene.image.Image;

public abstract class Item extends Tile {
    public Item(int x, int y, Image img) {
        super(x, y, img);
    }
}
