package entities.livingEntity.enemy;

import entities.LivingEntity;
import entities.livingEntity.enemy.AI.AI;
import javafx.scene.image.Image;

public abstract class Enemy extends LivingEntity {

    protected int point;

    protected AI enemyAI;

    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }


}
