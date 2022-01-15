package wanderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Dice implements Drawable {
    protected int posX;
    protected int posY;
    BufferedImage image;
    protected final int VALUE = ((int) (Math.random() * 6) + 1 );
    protected String appearance;

    public Dice() {
        switch (VALUE) {
            case 1:
                appearance = "img/boss.png";
                break;
            case 2:
                appearance = "img/floor.png";
                break;
            case 3:
                appearance = "img/hero-down.png";
                break;
            case 4:
                appearance = "img/hero-left.png";
                break;
            case 5:
                appearance = "img/hero-right.png";
                break;
            case 6:
                appearance = "img/hero-up.png";
                break;
        }
        this.positionedImage(appearance,725,70);
    }

    @Override
    public void draw(Graphics graphics) {
        if (image != null) {
            graphics.drawImage(image, posX, posY, null);
        }
    }

    @Override
    public void positionedImage(String filename, int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        try {
            image = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Graphics2D drawFont(Graphics graphics, int posX, int posY) {
        return null;
    }
}
