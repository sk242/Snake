
/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import javax.swing.*;

/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact
 * with one another.
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

    private SnakeBody head; // the initial snake, keyboard control
    private LinkedList<SnakeBody> body = new LinkedList<SnakeBody>(); // the snake body
    private Consumable apple; // the apples, randomly generate after consumed
    private Potion potion; // a potion to shrink the snake
    private int score = 0; // apples eaten
    private String username = new String(""); // the user
    private boolean playing = false; // whether the game is running
    private JLabel status; // Current status text, i.e. "Running..."

    // Leaderboard Variables
    private ArrayList<Object[]> leaderboard = new ArrayList<Object[]>();
    private Object[] highScore1;
    private Object[] highScore2;
    private Object[] highScore3;
    private Object[] highScore4;

    // Game constants
    public static final int COURT_WIDTH = 330;
    public static final int COURT_HEIGHT = 300;
    public static final int HEAD_VELOCITY = 16;

    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 35;

    public GameCourt(JLabel status, String username) {
        this.username = username;
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Timer
        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start();

        // Enable keyboard focus on the court area.
        setFocusable(true);

        // This key listener allows the snake to move as long as an arrow key is
        // pressed, by
        // changing the square's velocity accordingly.
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    head.setVx(-HEAD_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    head.setVx(HEAD_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    head.setVy(HEAD_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    head.setVy(-HEAD_VELOCITY);
                }
            }

            public void keyReleased(KeyEvent e) {
                head.setVx(0);
                head.setVy(0);
            }
        });

        this.status = status;
    }

    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
        this.body = new LinkedList<SnakeBody>();
        head = new SnakeBody(50, 40, COURT_WIDTH, COURT_HEIGHT, Color.RED);
        SnakeBody body1 = new SnakeBody(34, 40, COURT_WIDTH, COURT_HEIGHT, Color.GREEN);
        apple = new Apple(100, 40, COURT_WIDTH, COURT_HEIGHT, body);
        potion = new Potion(COURT_WIDTH, COURT_HEIGHT, body);
        body.add(head);
        body.add(body1);
        playing = true;
        status.setText("Running...");
        score = 0;
        requestFocusInWindow();
    }

    // Retrieves high scores. Cannot modify txt file since it is only a reader
    public String getHighScore() {
        FileReader file = null;
        BufferedReader scoreReader = null;
        String scores = new String("");
        try {
            file = new FileReader("files/high_scores.txt");
            scoreReader = new BufferedReader(file);
            String currentScore;
            currentScore = scoreReader.readLine();
            while (currentScore != null) {
                scores = scores + "\n" + currentScore;
                currentScore = scoreReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("IOException retrieving high score");
        } finally {
            try {
                scoreReader.close();
            } catch (IOException e) {
                System.out.println("IOException retrieving high score");
            }
        }
        return scores;
    }

    public void initHighScores() {
        String[] oldNameAndScores = new String[4];
        String[] oldNameAndScore1 = new String[2];
        String[] oldNameAndScore2 = new String[2];
        String[] oldNameAndScore3 = new String[2];
        oldNameAndScores = this.getHighScore().split("\n");
        if (oldNameAndScores.length > 1) {
            oldNameAndScore1 = oldNameAndScores[1].split(":");
            String oldName1 = new String("");
            oldName1 = oldNameAndScore1[0];
            int oldScore1 = Integer.parseInt(oldNameAndScore1[1]);
            this.recordHighScores(oldName1, oldScore1);
        }

        if (oldNameAndScores.length > 2) {
            oldNameAndScore2 = oldNameAndScores[2].split(":");
            String oldName2 = new String("");
            oldName2 = oldNameAndScore2[0];
            int oldScore2 = Integer.parseInt(oldNameAndScore2[1]);
            this.recordHighScores(oldName2, oldScore2);

        }

        if (oldNameAndScores.length > 3) {
            oldNameAndScore3 = oldNameAndScores[3].split(":");
            String oldName3 = new String("");
            oldName3 = oldNameAndScore3[0];
            int oldScore3 = Integer.parseInt(oldNameAndScore3[1]);
            this.recordHighScores(oldName3, oldScore3);
        }

    }

    public void recordHighScores(String name, int score) {

        if (highScore1 == null) {
            highScore1 = new Object[2];
            highScore1[0] = name;
            highScore1[1] = score;
            leaderboard.add(highScore1);

        } else if (highScore2 == null) {
            highScore2 = new Object[2];
            highScore2[0] = name;
            highScore2[1] = score;
            leaderboard.add(highScore2);

        } else if (highScore3 == null) {
            highScore3 = new Object[2];
            highScore3[0] = name;
            highScore3[1] = score;
            leaderboard.add(highScore3);

        } else if (highScore4 == null) {
            highScore4 = new Object[2];
            highScore4[0] = name;
            highScore4[1] = score;
            leaderboard.add(highScore4);

        } else {
            // if all scores exist, just modify the lowest score
            leaderboard.get(3)[0] = name;
            leaderboard.get(3)[1] = score;
        }

        // maintain invariants (that leaderboard is in order)

        // Comparator Created
        Comparator<Object[]> comp = new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {
                if (o1 == null || o2 == null) {
                    throw new NullPointerException();
                }
                if (o1[1] == null || o2[1] == null) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                if ((int) o1[1] > (int) o2[1]) {
                    return -1;
                } else if ((int) o1[1] < (int) o2[1]) {
                    return 1;
                } else {
                    return 0; 
                    }
            }
        };

        Collections.sort(leaderboard, comp);

        File scoreFile = new File("files/high_scores.txt");
        if (!scoreFile.exists()) {
            try {
                scoreFile.createNewFile(); // in case file is missing
            } catch (IOException e) {
                System.out.println("Unable to create file");
            }
        }

        // Updates the document
        BufferedWriter scoreWriter = null;
        try {
            scoreWriter = new BufferedWriter(new FileWriter(scoreFile));
            for (int i = 0; i < Math.min(3, leaderboard.size()); i++) {
                if (leaderboard.get(i) != null) {
                    scoreWriter.write((String) leaderboard.get(i)[0] + ":"
                            + Integer.toString((int) leaderboard.get(i)[1]) + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Adding IOException");
        } finally {
            try {
                scoreWriter.close();
            } catch (IOException e) {
                System.out.println("Couldn't write to file");
            }
        }
    }

    /**
     * This method is called every time the timer defined in the constructor
     * triggers.
     */
    void tick() {
        if (playing) {
            // moves the body
            if (head.getVx() != 0 || head.getVy() != 0) {
                for (int i = body.size() - 1; i > 0; i--) { // for loop because moving between
                    // i and i-1 twice so iterator.next would take longer.
                    body.get(i).setPx(body.get(i - 1).getPx());
                    body.get(i).setPy(body.get(i - 1).getPy());
                }
            }
            // moves the head
            head.move();

            // potion moves
            potion.spawn();

            if (head.intersects(potion) && potion.visible()) {
                for (int i = 0; i < 5; i++) {
                    body.removeLast();
                }
                potion.setVisibility(false);
                repaint();
            }

            // check for the game end conditions
            // Did the snake hit itself or a wall?
            for (int i = 1; i < body.size(); i++) {
                if (head.hitWall() || body.get(0).intersects(body.get(i))) {
                    playing = false;
                    status.setText(username + " lost");
                    this.recordHighScores(username, score);
                    repaint();
                    JOptionPane.showMessageDialog(this, this.getHighScore(), "High Scores", 
                            JOptionPane.PLAIN_MESSAGE);
                    break; // Prevents infinite loop
                }
                repaint();

            }

            // check if snake hits apple
            for (int i = 0; i < body.size(); i++) {
                if (head.intersects(apple) || body.get(i).intersects(apple)) {
                    apple.spawn();
                    // Snake body grows
                    int tailPx;
                    int tailPy;
                    tailPx = body.getLast().getPx() - body.getLast().getVx();
                    if (body.getLast().getVx() != 0) {
                        tailPx = tailPx - head.getVx() - 1;
                    }
                    tailPy = body.getLast().getPy() - body.getLast().getVy();
                    if (body.getLast().getVy() != 0) {
                        tailPy = tailPy - head.getVy() - 1;
                    }
                    SnakeBody tail = new SnakeBody(tailPx, tailPy, COURT_WIDTH, COURT_HEIGHT, 
                            Color.GREEN);
                    body.add(tail);

                    // Increment score
                    score++;
                    status.setText("Score:" + Integer.toString(score));
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        apple.draw(g);
        for (int i = 0; i < body.size(); i++) {
            body.get(i).draw(g);
        }
        potion.draw(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}