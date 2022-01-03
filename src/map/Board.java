package map;

import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Board extends JComponent implements KeyListener {

    private int testBoxX;
    private int testBoxY;
    List<PositionedImage> listOfImages;
    Player hero;

    int[][] gameBoard = {
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 1, 0, 1, 1, 0},
            {0, 1, 1, 1, 0, 1, 0, 1, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 0},
            {0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 0, 1, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 1, 0, 1, 0},
            {0, 1, 1, 1, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 1, 0, 0, 0}
    };

    public Board() {
        testBoxX = 0;
        testBoxY = 0;
        listOfImages = new ArrayList<>();
        hero = new Player();
        // set the size of your draw board
        setPreferredSize(new Dimension(720, 720));
        setVisible(true);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        background(graphics);
        //graphics.fillRect(testBoxX, testBoxY, 50, 100);
        // here you have a 720x720 canvas
        // you can create and draw an image using the class below e.g.
//        PositionedImage image = new PositionedImage("img/hero-down.png", testBoxX, testBoxY);
//        listOfImages.add(image);
//        image.draw(graphics);
        hero.setPosX(testBoxX); // TODO FIX with actual stats from player class
        hero.setPosY(testBoxY);
        //hero.positionedImage("img/hero-down.png",testBoxX,testBoxY);
        hero.draw(graphics);
    }

    public static void main(String[] args) {
        // Here is how you set up a new window and adding our board to it
        JFrame frame = new JFrame("RPG Game");
        Board board = new Board();
        frame.add(board);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();

        // Here is how you can add a key event listener
        // The board object will be notified when hitting any key
        // with the system calling one of the below 3 methods
        frame.addKeyListener(board);

        // Notice (at the top) that we can only do this
        // because this Board class (the type of the board object) is also a KeyListener
    }

    public void add(PositionedImage image) {
        listOfImages.add(image);
    }

    // To be a KeyListener the class needs to have these 3 methods in it
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    // But actually we can use just this one for our goals here

    // TODO implement the actual value from player instead testBox
    @Override
    public void keyReleased(KeyEvent e) {
        // When the up or down keys hit, we change the position of our box
        if (e.getKeyCode() == KeyEvent.VK_UP && testBoxY >= 72 && isFloor(testBoxX / 72, (testBoxY - 72) / 72)) {
            hero.changeImage("img/hero-up.png");
            testBoxY -= 72;
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN && testBoxY <= 576 && isFloor(testBoxX / 72, (testBoxY + 72) / 72)) {
            hero.changeImage("img/hero-down.png");
            testBoxY += 72;
        } else if(e.getKeyCode() == KeyEvent.VK_LEFT && testBoxX >= 72 && isFloor((testBoxX - 72) / 72, testBoxY / 72)) {
            hero.changeImage("img/hero-left.png");
            testBoxX -= 72;
        } else if(e.getKeyCode() == KeyEvent.VK_RIGHT && testBoxX <= 576 && isFloor((testBoxX + 72) / 72, testBoxY / 72) ) {
            hero.changeImage("img/hero-right.png");
            testBoxX += 72;
        }
        repaint();

        // and redraw to have a new picture with the new coordinates

    }

    public void background(Graphics graphics) {
        int posX = 0;
        int posY = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                PositionedImage tile;
                if (gameBoard[j][i] == 0) {
                    tile = new PositionedImage("img/floor.png", posX, posY);
                } else {
                    tile = new PositionedImage("img/wall.png", posX, posY);
                }
                posY += 72;
                tile.draw(graphics);
            }

            posY = 0;
            posX += 72;
        }
    }

    public int getTestBoxX() {
        return testBoxX;
    }

    public int getTestBoxY() {
        return testBoxY;
    }
    public boolean isFloor(int x, int y) {
        return gameBoard[y][x] == 0;
    }
}
