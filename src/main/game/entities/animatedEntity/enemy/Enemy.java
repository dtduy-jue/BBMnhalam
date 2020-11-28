package entities.animatedEntity.enemy;

import entities.AnimatedEntity;
import entities.animatedEntity.enemy.AI.AI;
import graphics.Sprite;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public abstract class Enemy extends AnimatedEntity {

    protected int point;

    protected AI enemyAI;

    public int xUnit, yUnit;

    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        xUnit = x / Sprite.SCALED_SIZE;
        yUnit = y / Sprite.SCALED_SIZE;
    }

    public void update(Scene scene) {
    }

    protected abstract void goDie();

}
