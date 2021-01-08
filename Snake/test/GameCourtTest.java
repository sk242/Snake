import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.util.LinkedList;

public class GameCourtTest {

    @Test
    public void testHeadCollideWall() {
        SnakeHead head;
        head = new SnakeHead(40, 40, Color.green);
        head.setPx(0);
        head.setPy(0);
        assertTrue(head.hitWall());
    }

    @Test
    public void testHeadCollideBody() {
        SnakeHead head;
        SnakeBody body;
        head = new SnakeHead(50, 50, Color.green);
        body = new SnakeBody(20, 20, 50, 50, Color.green);
        head.setPx(20);
        head.setPy(20);
        assertTrue(head.intersects(body));
    }

    @Test
    public void testHeadCollideApple() {
        SnakeHead head = new SnakeHead(50, 50, Color.green);
        LinkedList<SnakeBody> body = new LinkedList<SnakeBody>();
        Apple apple = new Apple(20, 20, 50, 50, body);
        assertEquals(body.size(), 0);
        head.setPx(20);
        head.setPy(20);
        assertTrue(head.intersects(apple));
    }

    @Test
    public void testHeadCollidePotion() {
        SnakeHead head = new SnakeHead(5, 5, Color.green);
        LinkedList<SnakeBody> body = new LinkedList<SnakeBody>();
        Potion potion = new Potion(5, 5, body);
        potion.setPx(3);
        potion.setPy(2);
        potion.setVisibility(true);
        SnakeBody part1 = new SnakeBody(1, 1, 10, 10, Color.green);
        SnakeBody part2 = new SnakeBody(1, 2, 10, 10, Color.green);
        SnakeBody part3 = new SnakeBody(1, 3, 10, 10, Color.green);
        SnakeBody part4 = new SnakeBody(1, 4, 10, 10, Color.green);
        SnakeBody part5 = new SnakeBody(1, 5, 10, 10, Color.green);
        SnakeBody part6 = new SnakeBody(1, 6, 10, 10, Color.green);
        SnakeBody part7 = new SnakeBody(1, 7, 10, 10, Color.green);
        SnakeBody part8 = new SnakeBody(2, 7, 10, 10, Color.green);
        SnakeBody part9 = new SnakeBody(2, 6, 10, 10, Color.green);
        SnakeBody part10 = new SnakeBody(2, 5, 10, 10, Color.green);
        SnakeBody part11 = new SnakeBody(2, 4, 10, 10, Color.green);
        SnakeBody part12 = new SnakeBody(2, 3, 10, 10, Color.green);
        SnakeBody part13 = new SnakeBody(2, 2, 10, 10, Color.green);
        SnakeBody part14 = new SnakeBody(2, 1, 10, 10, Color.green);
        SnakeBody part15 = new SnakeBody(3, 1, 10, 10, Color.green);
        body.add(part1);
        body.add(part2);
        body.add(part3);
        body.add(part4);
        body.add(part5);
        body.add(part6);
        body.add(part7);
        body.add(part8);
        body.add(part9);
        body.add(part10);
        body.add(part11);
        body.add(part12);
        body.add(part13);
        body.add(part14);
        body.add(part15);
        head.setPx(3);
        head.setPy(2);
        assertTrue(head.intersects(potion));
    }

    @Test
    public void testHeadCollideInvisiblePotion() {
        SnakeHead head = new SnakeHead(5, 5, Color.green);
        LinkedList<SnakeBody> body = new LinkedList<SnakeBody>();
        Potion potion = new Potion(5, 5, body);
        potion.setPx(3);
        potion.setPy(2);
        potion.setVisibility(false);
        SnakeBody part1 = new SnakeBody(1, 1, 10, 10, Color.green);
        SnakeBody part2 = new SnakeBody(1, 2, 10, 10, Color.green);
        SnakeBody part3 = new SnakeBody(1, 3, 10, 10, Color.green);
        SnakeBody part4 = new SnakeBody(1, 4, 10, 10, Color.green);
        SnakeBody part5 = new SnakeBody(1, 5, 10, 10, Color.green);
        SnakeBody part6 = new SnakeBody(1, 6, 10, 10, Color.green);
        SnakeBody part7 = new SnakeBody(1, 7, 10, 10, Color.green);
        SnakeBody part8 = new SnakeBody(2, 7, 10, 10, Color.green);
        SnakeBody part9 = new SnakeBody(2, 6, 10, 10, Color.green);
        SnakeBody part10 = new SnakeBody(2, 5, 10, 10, Color.green);
        SnakeBody part11 = new SnakeBody(2, 4, 10, 10, Color.green);
        SnakeBody part12 = new SnakeBody(2, 3, 10, 10, Color.green);
        SnakeBody part13 = new SnakeBody(2, 2, 10, 10, Color.green);
        SnakeBody part14 = new SnakeBody(2, 1, 10, 10, Color.green);
        SnakeBody part15 = new SnakeBody(3, 1, 10, 10, Color.green);
        body.add(part1);
        body.add(part2);
        body.add(part3);
        body.add(part4);
        body.add(part5);
        body.add(part6);
        body.add(part7);
        body.add(part8);
        body.add(part9);
        body.add(part10);
        body.add(part11);
        body.add(part12);
        body.add(part13);
        body.add(part14);
        body.add(part15);
        assertEquals(body.size(), 15);
        head.setPx(3);
        head.setPy(2);
        assertEquals(body.size(), 15);
    }

}
