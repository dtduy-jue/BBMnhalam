package entities;

import javafx.scene.image.Image;
import graphics.Sprite;

public class Bomber extends Entity {

    private int frame = -1;

    // Cứ FRAME_CYCLE khung hình thì cập nhật con nhân vật lắc lư 1 lần, FRAME_CYCLE nên chia hết cho 3 để movingSprite chạy ngon//
    private static final int FRAME_CYCLE = 18;

    public Bomber(int x, int y, Image img) {
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
        if (frame >= FRAME_CYCLE) {
            frame = 0;
        }
        img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, ++frame, FRAME_CYCLE).getFxImage();
    }

}
