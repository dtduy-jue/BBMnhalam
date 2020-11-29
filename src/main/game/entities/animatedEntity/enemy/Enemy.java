package entities.animatedEntity.enemy;

import entities.AnimatedEntity;
import entities.animatedEntity.enemy.AI.AI;
import graphics.Sprite;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import sound.Sound;

public abstract class Enemy extends AnimatedEntity {

    protected int point;

    protected AI enemyAI;

    public int xUnit, yUnit;

    protected Sound enemy_dead = new Sound("src\\main\\resources\\sound\\enemy_die.wav", false);

    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        xUnit = x / Sprite.SCALED_SIZE;
        yUnit = y / Sprite.SCALED_SIZE;
    }

    public void update(Scene scene) {
    }

    protected void goDie() {
        enemy_dead.playSound();
    }

}
