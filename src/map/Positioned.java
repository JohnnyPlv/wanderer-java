package map;

import java.awt.*;

public interface Positioned {
    void draw(Graphics graphics);
    void positionedImage(String filename, int posX, int posY);
}
