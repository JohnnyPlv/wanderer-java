package wanderer;

import java.awt.*;

public class Player extends Entity {
    protected String appearance = "img/hero-right.png";
    protected int xpBar;



    public Player() {
        this.level = 1;
        this.xpBar = 0;
        this.hp = 20 + dice;
        this.dp = 2 * dice;
        this.sp = 5 * dice;
        this.inspiration = 4 + this.level + dice;
        this.positionedImage(appearance,0,0);
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

    public void drawXpBar (Graphics graphics, int posX, int posY) {
        Graphics2D g2 = (Graphics2D) graphics;
        int fontSize = 20;
        Font f = new Font("SANS_SERIF",Font.BOLD,fontSize);
        g2.setFont(f);
        g2.drawString(this.toStringXpBar(),posX,posY);

    }




}
