import java.util.LinkedList;
import java.util.Random;

/*
 * Creates a coordinate independently that doesn't intersect any coordinate in a provided SnakeBody
 * list
 */
public class RandomPositionGenerator {
    private int px;
    private int py;

    public RandomPositionGenerator(int courtWidth, int courtHeight, LinkedList<SnakeBody> pstns) {
        if (pstns == null) {
            throw new NullPointerException();
        }

        Random rand = new Random();
        int px = Math.max(1, rand.nextInt(courtWidth));
        int py = Math.max(1, rand.nextInt(courtHeight));

        for (int i = 0; i < pstns.size(); i++) {
            for (int j = 0; j < pstns.size(); j++) {
                if (px == pstns.get(j).getPx() && py == pstns.get(j).getPy()) {
                    if (px == pstns.get(i).getPx()) {
                        px = Math.max(1, rand.nextInt(courtWidth));
                        i = 0;
                    }
                    if (py == pstns.get(j).getPy()) {
                        py = Math.max(1, rand.nextInt(courtHeight));
                        i = 0;
                    }
                }
            }
        }

        /*
         * for (int i = 0; i < positions.size(); i++) {
         * 
         * }
         */
        this.px = px;
        this.py = py;
    }

    public int getPx() {
        return px;
    }

    public int getPy() {
        return py;
    }
}
