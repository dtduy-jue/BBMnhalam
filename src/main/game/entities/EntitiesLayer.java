package entities;

import entities.tile.Brick;
import entities.tile.Wall;
import graphics.Sprite;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
* ở vị trí như portal hay item thì có 3 vật thể chồng lên nhau liền, vd: ô chứa item sẽ có brick chồng lên item rồi chồng lên grass
* class này sinh ra để lưu trữ và xử lý các vật thể đó
* over.
*/

public class EntitiesLayer extends Entity {

    protected List<Entity> layered_entity = new ArrayList<>();

    public EntitiesLayer (int x, int y, Entity ... entities) {
        this.x = x;
        this.y = y;

        layered_entity.addAll(Arrays.asList(entities));
    }

    @Override
    public void update(Scene scene) {
        getTopEntity().update(scene);
    }

    @Override
    public void render(GraphicsContext gc) {
        getTopEntity().render(gc);
    }

    public Entity getTopEntity() {
        return layered_entity.get(layered_entity.size() - 1);
    }

    private void clearRemoved() {
        Entity top  = getTopEntity();
        if(!top.isExist())  {
            layered_entity.remove(layered_entity.size() - 1);
        }
    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }


}
