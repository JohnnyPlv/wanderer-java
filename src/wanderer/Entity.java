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
    protected int currentHp;
    protected int dp;
    protected int sp;
    protected int inspiration;
    protected int level;
    protected int dice = ((int) (Math.random() * 6) + 1 );


    @Override
    public String toString() {
        return getClass().getSimpleName() + " (Level " + level + " ) " + " HP " + currentHp + "/" + hp + " DP " + dp + " SP " + sp + " Inspiration " + inspiration;
    }
    // draws image of the entity
    @Override
    public void draw(Graphics graphics) {
        if (image != null) {
            graphics.drawImage(image, posX, posY, null);
        }
    }

    public void drawHpBar (Graphics graphics,int posX, int posY, int posXwidth, int posYheight, int xBckrWidth) {
        Color brown = new Color(83, 52, 86, 255);
        graphics.setColor(brown);
        graphics.fillRect(posX - 3,posY - 3,xBckrWidth + 5,posYheight + 5); // to draw background of HP bar, using parameter xBckr
        graphics.setColor(Color.red);
        graphics.fillRect(posX,posY,posXwidth,posYheight); // to draw HP bar with red color
    }

    // general draw for Entites - enemies
    public void drawStats (Graphics graphics, int posX, int posY) {
        drawFont(graphics,posX,posY).drawString(this.toString(),posX,posY);
    }

    // takes care of setting up the String font and size and using it in other methods
    @Override
    public Graphics2D drawFont(Graphics graphics, int posX, int posY) {
        Graphics2D g2 = (Graphics2D) graphics;
        int fontSize = 20;
        Font f = new Font("Century Gothic",Font.BOLD,fontSize);
        g2.setFont(f);
        return g2;
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
        if (this.currentHp <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public void strike(Entity character) { // TODO do something with return, probly make another method which return damage
        if (!(damageDealt(character) <= 0))
        character.currentHp -= damageDealt(character);
    }

    public int damageDealt(Entity character) {
        return (this.sp + this.dice) - (character.dp + character.dice);
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
