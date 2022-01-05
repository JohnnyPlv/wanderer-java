package wanderer;

import java.awt.*;
import java.util.List;

public class Skeleton extends Entity {
    protected String appearance = "img/skeleton.png";


    public Skeleton() {
        this.level = 1;
        this.hp = 2 * this.level * ((int) (Math.random() * 6) + 1 );
        this.dp = this.level * ((int) (Math.random() * 6) + 1 );
        this.sp = this.level * ((int) (Math.random() * 6) + 1 );
        this.positionedImage(appearance,216,216);
    }

    public Skeleton(int posX,int posY) {
        this.level = 1;
        this.hp = 2 * this.level * ((int) (Math.random() * 6) + 1 );
        this.dp =  this.level * ((int) (Math.random() * 6) + 1 );
        this.sp =  this.level * ((int) (Math.random() * 6) + 1 );
        this.positionedImage(appearance,posX,posY);
    }


}
