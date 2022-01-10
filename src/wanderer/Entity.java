package wanderer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Entity implements Drawable {
    BufferedImage image;
    protected int posX , posY;
    protected int hp;
    protected int dp;
    protected int sp;
    protected int inspiration;
    protected int level;
    protected int dice = ((int) (Math.random() * 6) + 1 );


    @Override
    public String toString() {
        return getClass().getSimpleName() + " (Level " + level + " ) " + " HP " + hp + " DP " + dp + " SP " + sp + " Inspiration " + inspiration;
    }

    @Override
    public void draw(Graphics graphics) {
        if (image != null) {
            graphics.drawImage(image, posX, posY, null);
        }
    }

    public void drawStats (Graphics graphics, int posX, int posY) {
        graphics.drawString(this.toString(),posX,posY);
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
    public void changeImage(String filename) {
        try {
            image = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // makes NPC to move randomly around the map - //TODO - fix timing
    public void randomMoveNpc(int [][] gameBoard) {
        int random = ((int) (Math.random() * 4) + 1 );
        if (random == 1 && this.posY >= 72 && this.isFloor(gameBoard,this.posX/72,(this.posY-72) / 72)) {
            this.posY -= 72;
        } else if (random == 2 && this.posY <= 576 && this.isFloor(gameBoard, this.posX/72,(this.posY + 72)/72)){
            this.posY += 72;
        } else if ( random == 3 && this.posX >= 72 && this.isFloor(gameBoard, (this.posX - 72)/72, this.posY/72)) {
            this.posX -= 72;
        } else if (random == 4 && this.posX <= 576 && this.isFloor(gameBoard,(this.posX + 72) / 72, this.posY/72)) {
            this.posX += 72;
        }
    }

    public boolean isDead () {
        if (this.hp <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public void strike(Entity character) {
        character.hp -= this.sp - character.dp;
    }


    public boolean isFloor(int [][] gameBoard,int x, int y) {
        return gameBoard[y][x] == 0;
    }


    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
