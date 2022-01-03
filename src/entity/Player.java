package entity;

import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class Player extends Entity{
    public String appearance = "img/hero-right.png";
    public Player() {
        this.hp = 100;
        this.positionedImage(appearance,0,0);
    }


    @Override
    public void positionedImage(String filename, int posX, int posY) {
        super.positionedImage(filename, posX, posY);
    }


}
