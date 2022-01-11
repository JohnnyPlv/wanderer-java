package wanderer;

public class Boss extends Entity{
    protected String appearance = "img/boss.png";

    // TODO fix the stats /2 SP and DP
    public Boss() {
        this.level = 1;
        this.hp = 5 * this.level * dice + dice ;
        this.dp = this.level * dice + dice / 2;
        this.sp = this.level * dice + this.level;
        this.positionedImage(appearance,648,0);
    }
}
