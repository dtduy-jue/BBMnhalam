
package entities;

import graphics.State;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import graphics.Sprite;

/**
 *  Vật thể chung nhất.
 */
public abstract class Entity implements State {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected boolean existence = true;

    protected Image img;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas

    protected boolean canMoveUP = false;
    protected boolean canMoveDOWN = false;
    protected boolean canMoveLEFT = false;
    protected boolean canMoveRIGHT = false;


    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }


    public abstract void update(Scene scene);

    public void remove() {
        existence = false;
    }

    public boolean isExist() {
        return existence;
    }

    //Va cham//
    public abstract boolean collide(Entity e);

    public void setCanMoveUP(boolean canMoveUP) {
        this.canMoveUP = canMoveUP;
    }

    public void setCanMoveDOWN(boolean canMoveDOWN) {
        this.canMoveDOWN = canMoveDOWN;
    }

    public void setCanMoveLEFT(boolean canMoveLEFT) {
        this.canMoveLEFT = canMoveLEFT;
    }

    public void setCanMoveRIGHT(boolean canMoveRIGHT) {
        this.canMoveRIGHT = canMoveRIGHT;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}