package wanderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Board extends JComponent implements KeyListener {

    private int heroPositionX;
    private int heroPositionY;
    Player hero;
    protected Skeleton skeleton;
    protected Skeleton skeleton1;
    protected Skeleton skeleton2;
    List<Skeleton> listOfSkeletons;
    protected int moveClock;
    protected Boss lordChaos;


     int[][] gameBoard = {
            {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 1, 0, 1, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 0},
            {0, 1, 0, 1, 0, 0, 1, 0, 0, 0},
            {0, 1, 0, 1, 0, 1, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 1, 0, 1, 0},
            {0, 1, 1, 1, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 1, 0, 0, 0}
    };

    public Board() {
        heroPositionX = 360;
        heroPositionY = 360;
        hero = new Player();
        skeleton = new Skeleton();
        skeleton1 = new Skeleton(648,648);
        skeleton2 = new Skeleton(0,648);
        listOfSkeletons = new ArrayList<>();
        addSkeletons();
        lordChaos = new Boss();

        // set the size of your draw board
        setPreferredSize(new Dimension(720, 820));
        setVisible(true);
    }


    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        background(graphics);
        positionHero();
        //placeSkeletons(graphics);
        hero.draw(graphics);
        skeleton.draw(graphics);
        skeleton1.draw(graphics);
        skeleton2.draw(graphics);
        lordChaos.draw(graphics);
        //System.out.println(listOfSkeletons.size());


        if (hero.getPosX() == skeleton.getPosX() && hero.getPosY() == skeleton.getPosY()) {
            listOfSkeletons.remove(0);
            System.out.println("Skeleton Killed");
            System.out.println("Skeletons remaining " + listOfSkeletons.size());
        }
        int random = ((int) (Math.random() * 4) + 1 );






        //graphics.fillRect(testBoxX, testBoxY, 50, 100);
        // here you have a 720x720 canvas
        // you can create and draw an image using the class below e.g.
//        PositionedImage image = new PositionedImage("img/hero-down.png", testBoxX, testBoxY);
//        listOfImages.add(image);
//        image.draw(graphics);
//        hero.setPosX(testBoxX); // TODO FIX with actual stats from player class
//        hero.setPosY(testBoxY);
        //hero.positionedImage("img/hero-down.png",testBoxX,testBoxY);
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
        moveClock += 1;
        int random = ((int) (Math.random() * 4) + 1 );
        //int heroPosition = testBoxY;
        // When the up or down keys hit, we change the position of our box
        if (e.getKeyCode() == KeyEvent.VK_UP && heroPositionY >= 72 && isFloor(heroPositionX / 72, (heroPositionY - 72) / 72)) {
            hero.changeImage("img/hero-up.png");
            //hero.setPosY(testBoxY);
            heroPositionY -= 72;
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN && heroPositionY <= 576 && isFloor(heroPositionX / 72, (heroPositionY + 72) / 72)) {
            hero.changeImage("img/hero-down.png");
            heroPositionY += 72;
        } else if(e.getKeyCode() == KeyEvent.VK_LEFT && heroPositionX >= 72 && isFloor((heroPositionX - 72) / 72, heroPositionY / 72)) {
            hero.changeImage("img/hero-left.png");
            heroPositionX -= 72;
        } else if(e.getKeyCode() == KeyEvent.VK_RIGHT && heroPositionX <= 576 && isFloor((heroPositionX + 72) / 72, heroPositionY / 72) ) {
            hero.changeImage("img/hero-right.png");
            heroPositionX += 72;
        }

//        if (e.getKeyCode() == KeyEvent.VK_UP && hero.posY >= 72 && isFloor(hero.posX / 72, (hero.posY - 72) / 72)) {
//            hero.changeImage("img/hero-up.png");
//            //hero.setPosY(testBoxY);
//            hero.posY -= 72;
//        } else if(e.getKeyCode() == KeyEvent.VK_DOWN && hero.posY <= 576 && isFloor(hero.posX / 72, (hero.posY + 72) / 72)) {
//            hero.changeImage("img/hero-down.png");
//            hero.posY += 72;
//        } else if(e.getKeyCode() == KeyEvent.VK_LEFT && hero.posX >= 72 && isFloor((hero.posX - 72) / 72, hero.posY / 72)) {
//            hero.changeImage("img/hero-left.png");
//            hero.posX -= 72;
//        } else if(e.getKeyCode() == KeyEvent.VK_RIGHT && hero.posX <= 576 && isFloor((hero.posX + 72) / 72, hero.posY / 72) ) {
//            hero.changeImage("img/hero-right.png");
//            hero.posX += 72;
//        }






//        if (random == 1 && skeleton.posY >= 72 && skeleton.isFloor(gameBoard,skeleton.posX/72,(skeleton.posY-72) / 72)) {
//            skeleton.posY -= 72;
//        } else if (random == 2 && skeleton.posY <= 576 && skeleton.isFloor(gameBoard, skeleton.posX/72,(skeleton.posY + 72)/72)){
//            skeleton.posY += 72;
//        } else if ( random == 3 && skeleton.posX >= 72 && skeleton.isFloor(gameBoard, (skeleton.posX - 72)/72, skeleton.posY/72)) {
//            skeleton.posX -= 72;
//        } else if (random == 4 && skeleton.posX <= 576 && skeleton.isFloor(gameBoard,(skeleton.posX + 72) / 72, skeleton.posY/72)) {
//            skeleton.posX += 72;
//        }
        if (moveClock % 2 == 0) {
            skeleton.randomMoveNpc(gameBoard);
            skeleton1.randomMoveNpc(gameBoard);
            skeleton2.randomMoveNpc(gameBoard);
            lordChaos.randomMoveNpc(gameBoard);
        }

        repaint();

        // and redraw to have a new picture with the new coordinates

    }

    public void positionHero() {
        hero.setPosY(heroPositionY);
        hero.setPosX(heroPositionX);
    }

    public void background(Graphics graphics) {
        int posX = 0;
        int posY = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Tile tile;
                if (gameBoard[j][i] == 0) {
                    tile = new Tile("img/floor.png", posX, posY);
                } else {
                    tile = new Tile("img/wall.png", posX, posY);
                }
                posY += 72;
                tile.draw(graphics);
            }

            posY = 0;
            posX += 72;
        }
    }

    public void placeSkeletons(Graphics graphics) {
        int posX = 0;
        int posY = 0;

        listOfSkeletons.get(0).setPosX(0);
        listOfSkeletons.get(0).setPosY(576);
        listOfSkeletons.get(0).draw(graphics);

    }

    public void addSkeletons() {
        for (int i = 0; i < 3 ; i++) {
            listOfSkeletons.add(new Skeleton());
        }
    }

    public int getHeroPositionX() {
        return heroPositionX;
    }

    public int getHeroPositionY() {
        return heroPositionY;
    }
    public boolean isFloor(int x, int y) {
        return gameBoard[y][x] == 0;
    }
}
