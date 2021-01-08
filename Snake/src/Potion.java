import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

// This is another consumable that shrinks you, but is risky to eat since its attached 
// to the back of your tail
public class Potion extends Consumable {
    public static final String IMG_FILE = "files/potion.png";
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;

    private static BufferedImage img;
    private LinkedList<SnakeBody> body;
    private boolean visible;

    public Potion(int courtWidth, int courtHeight, LinkedList<SnakeBody> body) {
        super(INIT_VEL_X, INIT_VEL_Y, 400, 400, courtWidth, courtHeight);
        this.body = body;

        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }

    public void setVisibility(boolean bool) {
        visible = bool;
    }

    public boolean visible() {
        return visible;
    }

    @Override
    public void draw(Graphics g) {
        if (visible) {
            g.drawImage(img, this.getPx(), this.getPy(), this.getWidth(), this.getHeight(), null);
        }
    }

    @Override
    public void spawn() {
        if (body.size() > 15) {
            visible = true;
            this.setPx(body.getLast().getPx() - body.getLast().getVx() - 20); // trails the tail
            this.setPy(body.getLast().getPy() - body.getLast().getVy() - 20);
            this.setVx(body.getLast().getVx());
            this.setVy(body.getLast().getVy());
        }
    }

}