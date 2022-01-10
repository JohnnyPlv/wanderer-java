package wanderer;

import java.awt.*;
import java.util.List;

public class Skeleton extends Entity {
    protected String appearance = "img/skeleton.png";


    public Skeleton() {
        this.level = 1;
        this.hp = 2 * this.level * dice;
        this.dp = this.level * dice;
        this.sp = this.level * dice;
        this.inspiration = 1 + this.level + dice;
        this.positionedImage(appearance,216,216);
    }

    public Skeleton(int posX,int posY) {
        this.level = 1;
        this.hp = 2 * this.level * dice;
        this.dp =  this.level * dice;
        this.sp =  this.level * dice;
        this.positionedImage(appearance,posX,posY);
    }


}
