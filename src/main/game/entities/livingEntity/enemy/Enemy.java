package entities.livingEntity.enemy;

import entities.AnimatedEntity;
import entities.livingEntity.enemy.AI.AI;
import javafx.scene.image.Image;

public abstract class Enemy extends AnimatedEntity {

    protected int point;

    protected AI enemyAI;

    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }


}
