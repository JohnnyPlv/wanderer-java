package wanderer;

import java.awt.*;

public interface Drawable {
    void draw(Graphics graphics);
    void positionedImage(String filename, int posX, int posY);
    Graphics2D drawUI(Graphics graphics, int posX, int posY);
}
