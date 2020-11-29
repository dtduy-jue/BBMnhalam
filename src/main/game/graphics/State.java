package graphics;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

public interface State {

    void update(Scene scene);

    void render(GraphicsContext gc);

}
