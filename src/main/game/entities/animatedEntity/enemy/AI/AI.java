package entities.animatedEntity.enemy.AI;

import graphics.Sprite;

import java.util.Random;

public abstract class AI {

    Random random = new Random();

    protected int direction = 0;
    /*  1:  UP
        2:  RIGHT
        3:  DOWN
        4:  LEFT
     */


    int tick = 0;

    private double distance = Sprite.SCALED_SIZE;

    protected boolean delay() {
        if (tick < distance - 1) {
            ++tick;
            return true;
        } else {
            tick = 0;
            distance = (random.nextInt(4) + 1) * Sprite.SCALED_SIZE;
            return false;
        }
    }

    public abstract void nextMove();

    public int getDirection() {
        return direction;
    }

    public abstract void getNewMoveInstantly();
}
