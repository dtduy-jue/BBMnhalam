package entities.tile.item;

import entities.tile.Tile;
import javafx.scene.image.Image;

public abstract class Item extends Tile {
    protected int duration = -1; //thoi gian cua item

    protected boolean active = false;

    public Item(int x, int y, Image img) {
        super(x, y, img);
    }
}
