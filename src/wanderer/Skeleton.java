package wanderer;

public class Skeleton extends Entity {
    protected String appearance = "img/skeleton.png";


    public Skeleton() {
        this.hp = 100;
        this.positionedImage(appearance,216,216);
    }

    public Skeleton(int posX,int posY) {
        this.positionedImage(appearance,posX,posY);
    }



}
