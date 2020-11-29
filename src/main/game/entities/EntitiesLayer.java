package entities;

import entities.tile.Brick;
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
        this.x = x * Sprite.SCALED_SIZE;
        this.y = y * Sprite.SCALED_SIZE;

        layered_entity.addAll(Arrays.asList(entities));
    }

    @Override
    public void update(Scene scene) {
        if (getTopEntity().isExist()) {
            getTopEntity().update(scene);
        } else {
            layered_entity.remove(getTopEntity());
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        for (int i = 0; i < layered_entity.size(); ++i) {
            layered_entity.get(i).render(gc);
        }
    }

    public Entity getTopEntity() {
        return layered_entity.get(layered_entity.size() - 1);
    }

    @Override
    public void remove() {
        getTopEntity().remove();
    }

    @Override
    public boolean collide(Entity e) {
        return getTopEntity().collide(e);
    }


}
