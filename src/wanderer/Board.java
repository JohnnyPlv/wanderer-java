package wanderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Board extends JComponent implements KeyListener {

    private int heroPositionX;  // TODO implement the actual value from player instead board value for hero
    private int heroPositionY;
    protected Player hero;
    protected List<Entity> listOfEntities; // TODO change List of entites for Enemies to List of enemies - > create class Enemy?
    protected int moveClock;


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
        listOfEntities = new ArrayList<>();
        addEntities();
        // set the size of your draw board
        setPreferredSize(new Dimension(720, 820));
        setVisible(true);
    }


    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        background(graphics);
        positionHero();
        drawEntities(graphics);
        hero.drawStats(graphics,0,730);
        drawEntitiesStat(graphics);
        hero.draw(graphics);
    }


    // To be a KeyListener the class needs to have these 3 methods in it
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {
        moveClock += 1;
        // checks if the floor is occupied by enemy, if yes it will get out of the method
        if (isOccupiedBySkeleton() && e.getKeyCode() != KeyEvent.VK_SPACE) {
            return;
        }

        // if the floor is occupies and also the space was hit then hero fights the entity which shares the pos with hero
        if (isOccupiedBySkeleton() && e.getKeyCode() == KeyEvent.VK_SPACE) {
            for (Entity s : listOfEntities) {
                if (hero.posX == s.posX && hero.posY == s.posY) {
                    hero.fight(s);
                }

            }
        }
        // logic for avoding the walls, checks the coordinates of the matrix, if I move up it checks the coordinate of Y -72 - the tile one up (because wanna move up, if value of matrix 1 then its false
        if (e.getKeyCode() == KeyEvent.VK_UP && heroPositionY >= 72 && isGround(heroPositionX / 72, (heroPositionY - 72) / 72)) {
            hero.changeImage("img/hero-up.png");
            heroPositionY -= 72;
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN && heroPositionY <= 576 && isGround(heroPositionX / 72, (heroPositionY + 72) / 72)) {
            hero.changeImage("img/hero-down.png");
            heroPositionY += 72;
        } else if(e.getKeyCode() == KeyEvent.VK_LEFT && heroPositionX >= 72 && isGround((heroPositionX - 72) / 72, heroPositionY / 72)) {
            hero.changeImage("img/hero-left.png");
            heroPositionX -= 72;
        } else if(e.getKeyCode() == KeyEvent.VK_RIGHT && heroPositionX <= 576 && isGround((heroPositionX + 72) / 72, heroPositionY / 72) ) {
            hero.changeImage("img/hero-right.png");
            heroPositionX += 72;
        }

        // every time the key is pressed the turn counter starts summing - every second move the enemy moves
        if (moveClock % 2 == 0) {
            for (Entity s : listOfEntities) {
               s.randomMoveNpc(gameBoard);
            }
        }

        removeSkeletons();

        repaint();

    }

    public void positionHero() {
        hero.setPosY(heroPositionY);
        hero.setPosX(heroPositionX);
    }
    // draws a background - method added to call in paint
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
    // adds the Enemy to the List<Entites> ,including boss
    public void addEntities() {
        for (int i = 0; i < 4 ; i++) {
            if (i == 0) {
                listOfEntities.add(new Skeleton());
            }
            if (i == 1) {
                listOfEntities.add(new Skeleton(648,648));
            }
            if (i == 2) {
                listOfEntities.add(new Skeleton(0,648));
            }
            if (i == 3) {
                listOfEntities.add(new Boss());
            }
        }
    }
    // method to call the Enemies in bulk on the board
    public void drawEntities(Graphics graphics) {
        for (Entity s : listOfEntities) {
            s.draw(graphics);
        }
    }
    // method to draw the enttites stat on the board
    public void drawEntitiesStat(Graphics graphics) {
        for (int i = 0; i < listOfEntities.size() ; i++) {
            if (hero.posX == listOfEntities.get(i).posX && hero.posY == listOfEntities.get(i).posY){
                listOfEntities.get(i).drawStats(graphics,0,750);
            }
        }
    }
    // method to remove the skeletos, loops through the list and checks wheter the condition isDead is true, if yes removes the char
    public void removeSkeletons () {
        for (Entity s : listOfEntities) {
            if (s.isDead()) {
                listOfEntities.remove(s);
                break;
            }
        }
    }
    // method to check if its occupied by skeleton, this method is used for entering the fight
    public boolean isOccupiedBySkeleton () {
        for (Entity s : listOfEntities) {
            if (hero.posX == s.posX && hero.posY == s.posY) {
                return true;
            }
        }
        return false;
    }
    // simple boolean used it avooiding wals logic, if matrix value 0 ( floor ) then its true
    public boolean isGround(int x, int y) {
        return gameBoard[y][x] == 0;
    }
}
