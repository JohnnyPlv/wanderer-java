package wanderer;

public class Boss extends Entity{
    protected String appearance = "img/boss.png";

    // TODO fix the stats /2 SP and DP
    public Boss() {
        this.level = 1;
        this.hp = 5 * this.level  ;
        this.currentHp = this.hp;
        this.dp = 3 + this.level;
        this.sp = 3 + this.level   + this.level;
        this.inspiration = 1 + this.level;
        this.positionedImage(appearance,648,0);
    }
}
