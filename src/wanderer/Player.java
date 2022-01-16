package wanderer;

import java.awt.*;
import java.util.HashMap;

public class Player extends Entity {
    protected String appearance = "img/hero-right.png";
    protected int xpBar;

    public Player() {
        this.level = 1;
        this.xpBar = 0;
        this.hp = 50 ;
        this.currentHp = this.hp;
        this.dp = 2 ;
        this.sp = 5 ;
        this.inspiration = 4;
        this.positionedImage(appearance,0,0);
    }

    public void levelUp () {
        if (xpBar >= 100) {
            this.level++;
            this.dp++;
            this.sp++;
            this.inspiration++;
            this.xpBar = 0;
        }
    }

    public String toStringXpBar() {
        return "XP BAR: " + xpBar;
    }

    public String toStringStats() {
        return getClass().getSimpleName() + " (Level " + level + ")" +
                "\n" + "HP " + currentHp + "/" + hp +
                "\n" + "DP " + dp +
                "\n" + "SP " + sp +
                "\n" + "Inspiration " + inspiration;

    }
    // draw hero stats
    public void drawHeroStats (Graphics graphics, int posX, int posY) {
        Color grey = new Color(108,108,108);
        graphics.setColor(grey);
        for (String line : this.toStringStats().split("\n")) {
            drawFont(graphics, posX, posY).drawString(line,posX,posY); // draw Font iscreated in entity field and creates the specifik font, returns Graphics-font
            posY += 30;
        }
    }
    // draws ex bar
    public void drawXpBar (Graphics graphics, int posX, int posY) {
        drawFont(graphics, posX, posY).drawString(this.toStringXpBar(),posX,posY);
    }
        // TODO make new HpBar , make it from entity and move it to HERO stats

}
