import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Color;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

public class RandomPositionGeneratorTest {

    @Test
    public void testNoIntersection() {
        RandomPositionGenerator rand;
        LinkedList<SnakeBody> positions = new LinkedList<SnakeBody>();
        SnakeBody oneone = new SnakeBody(0, 0, 3, 3, Color.green);
        SnakeBody onetwo = new SnakeBody(0, 1, 3, 3, Color.green);
        SnakeBody onethree = new SnakeBody(0, 2, 3, 3, Color.green);
        SnakeBody twoone = new SnakeBody(1, 0, 3, 3, Color.green);
        SnakeBody twothree = new SnakeBody(1, 2, 3, 3, Color.green);
        SnakeBody threeone = new SnakeBody(2, 0, 3, 3, Color.green);
        SnakeBody threetwo = new SnakeBody(2, 1, 3, 3, Color.green);
        SnakeBody threethree = new SnakeBody(2, 2, 3, 3, Color.green);

        positions.add(oneone);
        positions.add(onetwo);
        positions.add(onethree);
        positions.add(twoone);
        positions.add(twothree);
        positions.add(threeone);
        positions.add(threetwo);
        positions.add(threethree);

        rand = new RandomPositionGenerator(3, 3, positions);
        assertEquals(1, rand.getPx());
        assertEquals(1, rand.getPy());
    }

    @Test
    public void testNullList() {
        assertThrows(NullPointerException.class, () -> {
            @SuppressWarnings("unused")
            RandomPositionGenerator rand = new RandomPositionGenerator(2, 2, null);
        });
    }

}
