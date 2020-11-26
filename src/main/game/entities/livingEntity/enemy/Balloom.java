package entities.livingEntity.enemy;

import entities.Entity;
import entities.livingEntity.enemy.AI.BalloomAI;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import graphics.Sprite;

import java.util.List;

public class Balloom extends Enemy {

    List<Entity> stillObjects;

    public Balloom(int x, int y, Image img, List<Entity> e) {
        super( x, y, img);
        enemyAI = new BalloomAI();
        stillObjects = e;
        speed = 1;
    }

    @Override
    public void update(Scene scene) {
        move();
        if (x % Sprite.SCALED_SIZE == 0 && y % Sprite.SCALED_SIZE == 0 ) System.out.println(x + "YES" + y);
    }

    private void move() {
        animate();
        enemyAI.nextMove();
        switch (enemyAI.getDirection()) {
            case 0: rest();     break;
            case 1: {
                goUp();
                if (!canMoveUP) {
                    while (enemyAI.getDirection() == 1) {
                        enemyAI.getNewMoveInstantly();
                    }
                }
                break;
            }
            case 3: {
                goDown();
                if (!canMoveDOWN) {
                    while (enemyAI.getDirection() == 3) {
                        enemyAI.getNewMoveInstantly();
                    }                }
                break;
            }
            case 4: {
                goLeft();
                if (!canMoveLEFT) {
                    while (enemyAI.getDirection() == 4) {
                        enemyAI.getNewMoveInstantly();
                    }                }
                break;
            }
            case 2: {
                goRight();
                if (!canMoveRIGHT) {
                    while (enemyAI.getDirection() == 2) {
                        enemyAI.getNewMoveInstantly();
                    }
                }
                break;
            }
        }

    }

    private void rest() {
    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }

    public void goUp() {
        this.setCanMoveUP(true);
        if (!searchEntity(((this.getX() / Sprite.SCALED_SIZE)) * Sprite.SCALED_SIZE, (((this.getY() - 1) / Sprite.SCALED_SIZE) + 1) * Sprite.SCALED_SIZE - Sprite.SCALED_SIZE).collide(this)
                || !searchEntity((((this.getX() + (Sprite.SCALED_SIZE) - 1) / Sprite.SCALED_SIZE)) * Sprite.SCALED_SIZE, (((this.getY() - 1) / Sprite.SCALED_SIZE) + 1) * Sprite.SCALED_SIZE - Sprite.SCALED_SIZE).collide(this)){
            this.setCanMoveUP(false);
        }
        if (!this.canMoveUP) {
            return;
        }
        y -= speed;
    }
    public void goDown() {
        this.setCanMoveDOWN(true);
        if (!searchEntity((this.getX()/Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE, (this.getY() / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE + Sprite.SCALED_SIZE).collide(this)
                || !searchEntity(((this.getX() + (Sprite.SCALED_SIZE) - 1) / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE, (this.getY() / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE + Sprite.SCALED_SIZE).collide(this)){
            this.setCanMoveDOWN(false);
        }
        if (!this.canMoveDOWN) {
            return;
        }
        y += speed;
    }
    public void goLeft() {
        this.setCanMoveLEFT(true);
        if (!searchEntity((((this.getX() - 1) / Sprite.SCALED_SIZE) + 1) * Sprite.SCALED_SIZE - Sprite.SCALED_SIZE, (this.getY() / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE).collide(this)
                || !searchEntity((((this.getX() - 1) / Sprite.SCALED_SIZE) + 1) * Sprite.SCALED_SIZE - Sprite.SCALED_SIZE, ((this.getY() + Sprite.SCALED_SIZE - 1) / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE).collide(this)){
            this.setCanMoveLEFT(false);
        }
        if (!this.canMoveLEFT) {
            return;
        }
        x -= speed;
    }
    public void goRight() {
        this.setCanMoveRIGHT(true);
        if (!searchEntity(((this.getX() + Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE, (this.getY() / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE).collide(this)
                || !searchEntity(((this.getX() + Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE, ((this.getY() + Sprite.SCALED_SIZE - 1) / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE).collide(this)){
            this.setCanMoveRIGHT(false);
        }
        if (!this.canMoveRIGHT) {
            return;
        }
        x += speed;
    }
    public Entity searchEntity(int x, int y) {
        for (Entity res : stillObjects) {
            if (res.getX() == x && res.getY() == y) {
                return res;
            }
        }
        return null;
    }

    protected void animate() {
        super.animate();
        chooseSprite();
    }

    private void chooseSprite() {
        switch (enemyAI.getDirection()) {
            case 0:
            case 4:
                img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, frame, FRAME_CYCLE).getFxImage();     break;
            case 3: case 2:
                img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, frame, FRAME_CYCLE).getFxImage();    break;
        }
    }
}