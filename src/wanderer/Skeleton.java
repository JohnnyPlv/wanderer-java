package wanderer;

import java.awt.*;
import java.util.List;

public class Skeleton extends Entity {
    protected String appearance = "img/skeleton.png";


    public Skeleton() {
        this.level = 1;
        this.hp = 2 * this.level;
        this.currentHp = this.hp;
        this.dp = this.level  ;
        this.sp = this.level  ;
        this.inspiration = 1 + this.level  ;
        this.positionedImage(appearance,216,216);
    }

    public Skeleton(int posX,int posY) {
        this.level = 1;
        this.hp = 10 * this.level  ;
        this.currentHp = this.hp;
        this.dp =  this.level  ;
        this.sp =  this.level  ;
        this.inspiration = 1 + this.level;
        this.positionedImage(appearance,posX,posY);
    }


}
