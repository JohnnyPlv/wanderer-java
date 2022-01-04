package wanderer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Entity extends JComponent implements Positioned {
    BufferedImage image;
    protected int posX , posY;
    protected int hp;


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
    public void changeImage(String filename) {
        try {
            image = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public boolean movedToWall(int [][] gameBoard, int posY,int posX) {
//        for (int i = 0; i < gameBoard.length ; i++) {
//            for (int j = 0; j < gameBoard.length ; j++) {
//                if (gameBoard[i][j] == 0) {
//                    if (i * 72 == posY && j * 72 == posX ) {
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }

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

    public boolean isFloor(int [][] gameBoard,int x, int y) {
        return gameBoard[y][x] == 0;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}