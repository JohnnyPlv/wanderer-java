package wanderer;

public class Boss extends Entity{
    public String appearance = "img/boss.png";

    // TODO fix the stats /2 SP and DP
    // TODO create dice variable for Random number?
    public Boss() {
        this.level = 1;
        this.hp = 2 * this.level * ((int) (Math.random() * 6) + 1 ) + ((int) (Math.random() * 6) + 1 ) ;
        this.dp = this.level * ((int) (Math.random() * 6) + 1 ) + ((int) (Math.random() * 6) + 1 ) / 2;
        this.sp = this.level * ((int) (Math.random() * 6) + 1 ) + this.level;
        this.positionedImage(appearance,648,0);
    }
}
