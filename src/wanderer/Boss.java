package wanderer;

public class Boss extends Entity{
    public String appearance = "img/boss.png";

    public Boss() {
        this.hp = 100;
        this.positionedImage(appearance,648,0);
    }
}
