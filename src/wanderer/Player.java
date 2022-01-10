package wanderer;

public class Player extends Entity {
    public String appearance = "img/hero-right.png";



    public Player() {
        this.level = 1;
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




}
