package entities.animatedEntity;

import entities.EntitiesLayer;
import entities.Entity;
import entities.AnimatedEntity;
import entities.animatedEntity.bomb.Bomb;
import entities.animatedEntity.enemy.Enemy;
import entities.tile.Grass;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import graphics.Sprite;
import sound.Sound;

import java.util.ArrayList;
import java.util.List;

/**
 *  Người chơi.
 */
public class Bomber extends AnimatedEntity {

    private boolean left, right, up, down, bomb = false;

    private boolean bombPass = false;

    private int bomb_set;

    private int lives = 3;

    private List<Entity> stillObjects, movingObject;

    private List<Bomb> bombs = new ArrayList<>();
    private int bombX;
    private int bombY;

    private Sound bomber_move_horizontal = new Sound("src\\main\\resources\\sound\\player_move_horizontal.wav", true);
    private Sound bomber_move_vertical = new Sound("src\\main\\resources\\sound\\player_move_vertical.wav", true);
    private Sound bomber_dead = new Sound("src\\main\\resources\\sound\\player_die_1.wav", false);

    public Bomber(int x, int y, Image img, List<Entity> e, List<Bomb> bombs, List<Entity> movingObject) {
        super( x, y, img);
        stillObjects = e;
        speed = 2;
        bomb_set = 3;
        this.movingObject = movingObject;
        this.bombs = bombs;
    }

    @Override
    public void update(Scene scene) {
        if (!alive) {
            goDie();
            return;
        }
        move(scene);
        avoidBombStuck(x, y);
        setBomb();
    }

    private void setBomb() {
        if (bomb && canSetBomb(x, y) && bombs.size() < bomb_set) {
            bombs.add(new Bomb(bombX / Sprite.SCALED_SIZE, bombY / Sprite.SCALED_SIZE, Sprite.bomb.getFxImage(), stillObjects, movingObject, bombs));
            bomb = false;
        }
    }

    private boolean canSetBomb(int x, int y) {
        bombX = ((x + Sprite.SCALED_SIZE * 3 / 8) / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE;
        bombY = ((y + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE;
        Entity e = searchEntity(bombX, bombY);
        if (e instanceof EntitiesLayer) {
            if (((EntitiesLayer) e).getTopEntity() instanceof Grass) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    private void move(Scene scene) {
        detectEnemy(this.x, this.y);
        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case LEFT: {
                    left = true;
                    if (!bomber_move_horizontal.isPlaying()) {
                        bomber_move_horizontal.playSound();
                    }
                    break;
                }
                case RIGHT: {
                    right = true;
                    if (!bomber_move_horizontal.isPlaying()) {
                        bomber_move_horizontal.playSound();
                    }
                    break;
                }
                case UP: {
                    up = true;
                    if (!bomber_move_vertical.isPlaying()) {
                        bomber_move_vertical.playSound();
                    }
                    break;
                }
                case DOWN: {
                    down = true;
                    if (!bomber_move_vertical.isPlaying()) {
                        bomber_move_vertical.playSound();
                    }
                    break;
                }
                case SPACE: bomb = true; break;
            }
        });

        scene.setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode()) {
                case LEFT:  {
                    left = false;
                    if (bomber_move_horizontal.isPlaying()) {
                        bomber_move_horizontal.stopSound();
                    }
                    break;
                }
                case RIGHT: {
                    right = false;
                    if (bomber_move_horizontal.isPlaying()) {
                        bomber_move_horizontal.stopSound();
                    }
                    break;
                }
                case UP: {
                    up = false;
                    if (bomber_move_vertical.isPlaying()) {
                        bomber_move_vertical.stopSound();
                    }
                    break;
                }
                case DOWN: {
                    down = false;
                    if (bomber_move_vertical.isPlaying()) {
                        bomber_move_vertical.stopSound();
                    }
                    break;
                }
                case SPACE: bomb = false;   break;
            }
        });

        this.setCanMoveUP(true);
        if (!searchEntity(((this.getX() / Sprite.SCALED_SIZE)) * Sprite.SCALED_SIZE, (((this.getY() - 1) / Sprite.SCALED_SIZE) + 1) * Sprite.SCALED_SIZE - Sprite.SCALED_SIZE).collide(this)
                || !searchEntity((((this.getX() + (int)(Sprite.SCALED_SIZE * 3 / 4) - 1) / Sprite.SCALED_SIZE)) * Sprite.SCALED_SIZE, (((this.getY() - 1) / Sprite.SCALED_SIZE) + 1) * Sprite.SCALED_SIZE - Sprite.SCALED_SIZE).collide(this)){
            this.setCanMoveUP(false);
        }
        this.setCanMoveDOWN(true);
        if (!searchEntity((this.getX()/Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE, (this.getY() / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE + Sprite.SCALED_SIZE).collide(this)
                || !searchEntity(((this.getX() + (int)(Sprite.SCALED_SIZE * 3 / 4) - 1) / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE, (this.getY() / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE + Sprite.SCALED_SIZE).collide(this)){
            this.setCanMoveDOWN(false);
        }
        this.setCanMoveLEFT(true);
        if (!searchEntity((((this.getX() - 1) / Sprite.SCALED_SIZE) + 1) * Sprite.SCALED_SIZE - Sprite.SCALED_SIZE, (this.getY() / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE).collide(this)
                || !searchEntity((((this.getX() - 1) / Sprite.SCALED_SIZE) + 1) * Sprite.SCALED_SIZE - Sprite.SCALED_SIZE, ((this.getY() + (int)Sprite.SCALED_SIZE - 1) / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE).collide(this)){
            this.setCanMoveLEFT(false);
        }
        this.setCanMoveRIGHT(true);
        if (!searchEntity(((this.getX() + Sprite.SCALED_SIZE * 3 / 4) / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE, (this.getY() / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE).collide(this)
                || !searchEntity(((this.getX() + Sprite.SCALED_SIZE * 3 / 4) / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE, ((this.getY() + (int)Sprite.SCALED_SIZE - 1) / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE).collide(this)){
            this.setCanMoveRIGHT(false);
        }

        if (up && !down)     goUp();
        else if (down && !up)   goDown();
        if (left && !right)   goLeft();
        else if (right && !left)  goRight();
    }

    public boolean collide(Entity e) {
        return false;
    }
    public void goUp() {

        animate();
        if (!this.canMoveUP) {
            if (((double)this.x / Sprite.SCALED_SIZE) % 2 > 1.0 && ((double)this.x / Sprite.SCALED_SIZE) % 2 < 1.8 && this.canMoveLEFT) {
                x -= speed;
                img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, frame, FRAME_CYCLE).getFxImage();
                return;
            }
            else if (((double)this.x / Sprite.SCALED_SIZE) % 2 > 0.4 && ((double)this.x / Sprite.SCALED_SIZE) % 2 < 1.0 && this.canMoveRIGHT){
                x += speed;
                img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, frame, FRAME_CYCLE).getFxImage();
                return;
            }
            else {
                img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, frame, FRAME_CYCLE).getFxImage();
                return;
            }
        }
        y -= speed;
        img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, frame, FRAME_CYCLE).getFxImage();
    }
    public void goDown() {
        animate();
        if (!this.canMoveDOWN) {
            if (((double)this.x / Sprite.SCALED_SIZE) % 2 > 1.0 && ((double)this.x / Sprite.SCALED_SIZE) % 2 < 1.8 && this.canMoveLEFT) {
                x -= speed;
                img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, ++frame, FRAME_CYCLE).getFxImage();
                return;
            }
            else if (((double)this.x / Sprite.SCALED_SIZE) % 2 > 0.4 && ((double)this.x / Sprite.SCALED_SIZE) % 2 < 1.0 && this.canMoveRIGHT){
                x += speed;
                img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, ++frame, FRAME_CYCLE).getFxImage();
                return;
            }
            else {
                img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, ++frame, FRAME_CYCLE).getFxImage();
                return;
            }
        }
        y += speed;
        img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, ++frame, FRAME_CYCLE).getFxImage();
    }
    public void goLeft() {
        animate();
        if (!this.canMoveLEFT) {
            if (((double)this.y / Sprite.SCALED_SIZE) % 2 > 1.0 && ((double)this.y / Sprite.SCALED_SIZE) % 2 < 1.8 && this.canMoveUP) {
                y -= speed;
                img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, ++frame, FRAME_CYCLE).getFxImage();
                return;
            }
            else if (((double)this.y / Sprite.SCALED_SIZE) % 2 > 0.4 && ((double)this.y / Sprite.SCALED_SIZE) % 2 < 1.0 && this.canMoveDOWN){
                y += speed;
                img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, ++frame, FRAME_CYCLE).getFxImage();
                return;
            }
            else {
                img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, ++frame, FRAME_CYCLE).getFxImage();
                return;
            }
        }
        x -= speed;
        img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, ++frame, FRAME_CYCLE).getFxImage();
    }
    public void goRight() {
        animate();
        if (!this.canMoveRIGHT) {
            if (((double)this.y / Sprite.SCALED_SIZE) % 2 > 1.0 && ((double)this.y / Sprite.SCALED_SIZE) % 2 < 1.8 && this.canMoveUP) {
                y -= speed;
                img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, ++frame, FRAME_CYCLE).getFxImage();
                return;
            }
            else if (((double)this.y / Sprite.SCALED_SIZE) % 2 > 0.4 && ((double)this.y / Sprite.SCALED_SIZE) % 2 < 1.0 && this.canMoveDOWN){
                y += speed;
                img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, ++frame, FRAME_CYCLE).getFxImage();
                return;
            }
            else {
                img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, ++frame, FRAME_CYCLE).getFxImage();
                return;
            }
        }
        x += speed;
        img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, ++frame, FRAME_CYCLE).getFxImage();
    }

    private void goDie() {
        bomber_dead.playSound();
        if (dead_animation_tick < 0) {
            img = Sprite.player_dead1.getFxImage();
            ++dead_animation_tick;
        } else if (dead_animation_tick < FRAME_CYCLE / 2) {
            img = Sprite.movingSprite(Sprite.player_dead2, Sprite.player_dead3, dead_animation_tick, FRAME_CYCLE / 2).getFxImage();
            ++dead_animation_tick;
        } else {
            remove();
        }
    }

    public void avoidBombStuck(int x, int y) {
        for (Bomb bomb : bombs) {
            if (Math.abs(x - bomb.getX()) < (Sprite.SCALED_SIZE - 12) && Math.abs(y - bomb.getY()) < (Sprite.SCALED_SIZE - 4)) {
                bombPass = true;
                return;
            }
            bombPass = false;
        }
    }

    public Entity searchEntity(int x, int y) {
        if (!bombPass) {
            for (Bomb bomb : bombs) {
                if (bomb.getX() == x && bomb.getY() == y) {
                    return bomb;
                }
            }
        }
        for (Entity res : stillObjects) {
            if (res.getX() == x && res.getY() == y) {
                return res;
            }
        }
        return null;
    }

    public boolean isBombHere(int x, int y) {
        for (Bomb current_bomb :bombs) {
            if (current_bomb.getX() == x && current_bomb.getY() == y) {
                return true;
            }
        }
        return false;
    }

    public boolean detectEnemy(int x, int y) {
        for (Entity res : movingObject) {
            if (res instanceof Enemy && Math.abs(x - res.getX()) < (Sprite.SCALED_SIZE - 12) && Math.abs(y - res.getY()) < (Sprite.SCALED_SIZE - 4)) {
                alive = false;
                return true;
            }
        }
        return false;
    }

}
