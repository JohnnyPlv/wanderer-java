package wanderer;

import java.awt.*;
import java.util.HashMap;

public class Player extends Entity {
    protected String appearance = "img/hero-right.png";
    protected int xpBar;
    //protected HashMap<String,Integer> stats;



    public Player() {
        this.level = 1;
        this.xpBar = 0;
        this.hp = 20 + dice;
        this.dp = 2 * dice;
        this.sp = 5 * dice;
        this.inspiration = 4 + this.level + dice;
        this.positionedImage(appearance,0,0);
        //this.stats = new HashMap<>();
        //addStats();
    }
 //old fight method
    public void fight(Entity enemy) {

        while (this.hp > 0 && enemy.hp > 0 ) {
            if ( (2 * dice + this.sp) > enemy.dp) {
                enemy.hp -= this.sp + (dice *2 ) - enemy.dp;
            }
            if ((2 * dice + this.sp) < enemy.dp) {
                this.hp -= enemy.sp + (dice *2 ) - this.dp;
            }
        }

    }

    public String toStringXpBar() {
        return "XP BAR: " + xpBar;
    }

    public String toStringStats() {
        return getClass().getSimpleName() + " (Level " + level + ")" +
                "\n" + "HP " + hp +
                "\n" + "DP " + dp +
                "\n" + "SP " + sp +
                "\n" + "Inspiration " + inspiration;

    }
    // draw hero stats
    public void drawHeroStats (Graphics graphics, int posX, int posY) {
        Color grey = new Color(108,108,108);
        graphics.setColor(grey);
        drawFont(graphics, posX, posY);
        for (String line : this.toStringStats().split("\n")) {
            drawFont(graphics, posX, posY).drawString(line,posX,posY);
            posY += 20;
        }
    }
    // draws ex bar
    public void drawXpBar (Graphics graphics, int posX, int posY) {
        drawFont(graphics, posX, posY).drawString(this.toStringXpBar(),posX,posY);
    }
        // TODO make new HpBar , make it from entity and move it to HERO stats
//    public void drawHpBar (Graphics graphics,int posX, int posY, int posXwidth, int posYheight) {
//        graphics.setColor(Color.red);
//        graphics.fillRect(posX,posY,posXwidth,posYheight);
//    }



//    public void addStats () {
//        stats.put("Level",this.level);
//        stats.put("HP",this.hp);
//        stats.put("SP",this.sp);
//    }

//    public void drawHashStats (Graphics graphics, int posX, int posY) {
//        Graphics2D g2 = (Graphics2D) graphics;
//        int fontSize = 20;
//        Font f = new Font("SANS_SERIF",Font.BOLD,fontSize);
//        g2.setFont(f);
//        for (String key : stats.keySet()) {
//            int value = stats.get(key);
//            String stat = key + " " + value;
//            g2.drawString(stat,posX,posY);
//            posY += 20;
//        }
//    }

}
