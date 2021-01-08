import java.awt.Color;
import java.awt.Graphics;

// The movable part of the snake

public class SnakeBody extends GameObj {
    public static final int SIZE = 15;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;

    private Color color;

    public SnakeBody(int xPos, int yPos, int courtWidth, int courtHeight, Color color) {
        super(INIT_VEL_X, INIT_VEL_Y, xPos, yPos, SIZE, SIZE, courtWidth, courtHeight);

        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillRect(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }

}
