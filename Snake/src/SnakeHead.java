import java.awt.*;

// The movable part of the snake

public class SnakeHead extends GameObj {
    public static final int SIZE = 15;
    public static final int INIT_POS_X = 50;
    public static final int INIT_POS_Y = 40;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;

    private Color color;

    public SnakeHead(int courtWidth, int courtHeight, Color color) {
        super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, SIZE, SIZE, courtWidth, courtHeight);

        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillRect(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }

}
