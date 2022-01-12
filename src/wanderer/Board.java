package wanderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JComponent implements KeyListener {

    private int heroPositionX;  // TODO implement the actual value from player instead board value for hero
    private int heroPositionY;
    protected Player hero;
    //protected List<Entity> listOfEntitities; // TODO change List of entites for Enemies to List of enemies - > create class Enemy?
    protected int moveClock;
    protected ExperienceTable experienceTable;
    protected AreaLevel areaLevel;




    public Board() {
        heroPositionX = 0;
        heroPositionY = 0;
        hero = new Player();
        experienceTable = new ExperienceTable();
        areaLevel = new AreaLevel(1);
        // set the size of your draw board
        setPreferredSize(new Dimension(880, 840));
        setVisible(true);
    }


    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        background(graphics);
        backgroundUI(graphics);
        positionHero();
        drawEntities(graphics);
        hero.drawHeroStats(graphics,725,30);
        hero.drawXpBar(graphics,600,740);
        //hero.drawHpBar(graphics,5,770,hero.hp * 3,30);
        drawEntitiesStat(graphics, 5, 740);
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
        if (isOccupiedByEntity() && e.getKeyCode() != KeyEvent.VK_SPACE) {
            return;
        }

        // if the floor is occupies and also the space was hit then hero strikes the entity which shares the pos with hero
        // TODO battle LOGIC
        if (isOccupiedByEntity() && e.getKeyCode() == KeyEvent.VK_SPACE) {
            for (Entity s : areaLevel.listOfEntities) {
                if (hero.posX == s.posX && hero.posY == s.posY) {
                    if (hero.inspiration >= s.inspiration) {
                        hero.strike(s);
                        if (s.isDead()) {
                            increaseExperience();
                        }
                        if (!s.isDead())
                        s.strike(hero);
                    } else {
                        s.strike(hero);
                        if (!hero.isDead())
                        hero.strike(s);
                        if (s.isDead()) {
                            increaseExperience();
                        }
                    }
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
            for (Entity s : areaLevel.listOfEntities) {
                if (hero.posX != s.posX && hero.posY != s.posY) {
                    s.randomMoveNpc(areaLevel.map);
                }
            }
        }
        // checks if to change the levels
        changeLevel();
        // removes dead characters from the list
        removeEntities();

        repaint();

    }

    public void changeLevel () {
        for (Entity s : areaLevel.listOfEntities) {
            if (s instanceof Boss && s.isDead()) {
                areaLevel.currentArea++;
                areaLevel.generateLevel();
                heroPositionX = 0;
                heroPositionY = 0;
            }
        }
    }
    // implemented "XP bar" - uses ExperienceTable class where the final variables are set and increases the correct xp per kill
    public void increaseExperience () {
        for (Entity e : areaLevel.listOfEntities) {
            if (e.isDead() && e instanceof Skeleton) {
                hero.xpBar += experienceTable.XPGAINEDSKELETON;
            }
            if (e.isDead() && e instanceof Boss) {
                hero.xpBar += experienceTable.XPGAINEDBOSS;
            }
        }
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
                if (areaLevel.map[j][i] == 0) {
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
    public void backgroundUI(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(0,720,880,120);
        graphics.fillRect(720,0,160,840);
    }
    // method to call the Enemies in bulk on the board
    public void drawEntities(Graphics graphics) {
        for (Entity s : areaLevel.listOfEntities) {
            s.draw(graphics);
        }
    }
    // method to draw the enttites stat on the board
    public void drawEntitiesStat(Graphics graphics, int posX, int posY) {
        for (int i = 0; i < areaLevel.listOfEntities.size() ; i++) {
            if (hero.posX == areaLevel.listOfEntities.get(i).posX && hero.posY == areaLevel.listOfEntities.get(i).posY){
                areaLevel.listOfEntities.get(i).drawStats(graphics,posX,posY);
                areaLevel.listOfEntities.get(i).drawHpBar(graphics,5,770,areaLevel.listOfEntities.get(i).hp * 6,20);
            }
        }
    }
    // method to remove the skeletos, loops through the list and checks wheter the condition isDead is true, if yes removes the char
    public void removeEntities() {
        for (Entity s : areaLevel.listOfEntities) {
            if (s.isDead()) {
                areaLevel.listOfEntities.remove(s);
                break;
            }
        }
    }
    // method to check if its occupied by skeleton, this method is used for entering the fight
    public boolean isOccupiedByEntity() {
        for (Entity s : areaLevel.listOfEntities) {
            if (hero.posX == s.posX && hero.posY == s.posY) {
                return true;
            }
        }
        return false;
    }
    // simple boolean used it avooiding wals logic, if matrix value 0 ( floor ) then its true
    public boolean isGround(int x, int y) {
        return areaLevel.map[y][x] == 0;
    }
}
