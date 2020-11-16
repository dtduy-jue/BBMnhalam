package entities.enemy;

import entities.Entity;
import javafx.scene.image.Image;
import graphics.Sprite;

public class Balloom extends Entity {

    private int frame = -1;

    // Cứ FRAME_CYCLE khung hình thì cập nhật con nhân vật lắc lư 1 lần, FRAME_CYCLE nên chia hết cho 3 để movingSprite chạy ngon//
    private static final int FRAME_CYCLE = 18;


    public Balloom(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {
        /*
        if (nhan vat di chuyen) {
            if (di chuyen trai) {
                animate(trai);
            } phai tren duoi ...
        }
         */
        animate(); // cho con nv lac lu khi di chuyen
    }

    public void animate() {
        if (frame == FRAME_CYCLE) {
            frame = 0;
        }
        img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, ++frame, FRAME_CYCLE).getFxImage();
    }

}