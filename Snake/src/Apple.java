import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.LinkedList;

import javax.imageio.ImageIO;

//This is the food, and it is displayed with an image

public class Apple extends Consumable {
    public static final String IMG_FILE = "files/apple.png";
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;

    private static BufferedImage img;
    private int courtWidth;
    private int courtHeight;
    private LinkedList<SnakeBody> body;

    public Apple(int xPos, int yPos, int courtWidth, int courtHeight, LinkedList<SnakeBody> body) {
        super(INIT_VEL_X, INIT_VEL_Y, xPos, yPos, courtWidth, courtHeight);
        this.courtWidth = courtWidth;
        this.courtHeight = courtHeight;
        this.body = body;

        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, this.getPx(), this.getPy(), this.getWidth(), this.getHeight(), null);
    }

    @Override
    public void spawn() {
        // Randomly creates an apple not intersecting the snake.
        RandomPositionGenerator newApplePosition = new RandomPositionGenerator(courtWidth, 
                courtHeight, body);
        this.setPx(newApplePosition.getPx());
        this.setPy(newApplePosition.getPy());

    }

}
