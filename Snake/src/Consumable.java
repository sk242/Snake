//These are the consumables

public abstract class Consumable extends GameObj {
    public static final int SIZE = 20;

    public Consumable(int velX, int velY, int xPos, int yPos, int courtWidth, int courtHeight) {
        super(velX, velY, xPos, yPos, SIZE, SIZE, courtWidth, courtHeight);

    }

    public abstract void spawn();

}
