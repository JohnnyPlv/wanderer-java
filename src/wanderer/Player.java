package wanderer;

public class Player extends Entity {
    public String appearance = "img/hero-right.png";



    public Player() {
        this.hp = 100;
        this.positionedImage(appearance,0,0);
    }


}
