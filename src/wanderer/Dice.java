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
    protected int valueForSp;
    protected int valueForDp;

    public Dice() { // TODO have to finish the pictures of Dice, this switch in constructor is not in use yet..
    }

    public int rollDiceOnSp () {
        valueForSp = ((int) (Math.random() * 6) + 1 );
        return valueForSp;
    }

    public int rollDiceOnDp () {
        valueForDp = ((int) (Math.random() * 6) + 1 );
        return valueForDp;
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
