package wanderer;

public class Player extends Entity {
    public String appearance = "img/hero-right.png";



    public Player() {
        this.level = 1;
        this.hp = 20 + ((int) (Math.random() * 6) + 1 );
        this.dp = 2 * ((int) (Math.random() * 6) + 1 );
        this.sp = 5 * ((int) (Math.random() * 6) + 1 );
        this.positionedImage(appearance,0,0);
    }

    public void fight(Entity character) {

        while (this.hp > 0 && character.hp > 0 ) {
            if ( (2 * ((int) (Math.random() * 6) + 1 ) + this.sp) > character.dp) {
                character.hp -= this.sp + (((int) (Math.random() * 6) + 1 ) *2 ) - character.dp;
            }
            if ((2 * ((int) (Math.random() * 6) + 1 ) + this.sp) < character.dp) {
                this.hp -= character.sp + (((int) (Math.random() * 6) + 1 ) *2 ) - this.dp;
            }
        }

    }


}
