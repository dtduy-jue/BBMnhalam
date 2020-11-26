package entities.livingEntity.enemy.AI;

public class BalloomAI extends AI {

    @Override
    public void nextMove() {
        if (!delay()) {
            direction = random.nextInt(4) + 1;
        }
    }

    public void getNewMoveInstantly() {
        direction = random.nextInt(4) + 1;
    }
}
