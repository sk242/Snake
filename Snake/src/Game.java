/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
    public void run() {

        final JFrame frame = new JFrame("Snake");
        frame.setLocation(300, 300);

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Score:");
        status_panel.add(status);

        // Get Username
        String username = JOptionPane.showInputDialog(frame, "Please enter your first name.", 
                "Username", JOptionPane.PLAIN_MESSAGE);
        if (username == null) {
            username = "Anonymous";
        }
        String[] fullName = new String[username.length()];
        fullName = username.split(" ", 0); // Takes first name
        username = fullName[0];

        // Main playing area
        final GameCourt court = new GameCourt(status, username);
        frame.add(court, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        // Action listener for reset button
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.reset();
            }
        });
        control_panel.add(reset);

        final String Guide = ("Thanks for playing my game!\n" + "Use the arrow keys "
                + "to control your snake. Your goal is to eat apples without crashing into \n"
                + "the walls or your body. To help you along, a magical potion will appear " 
                + "whenever \n"
                + "you get too long (15 ;-)). Be warned, the potion isn't "
                + "easy to drink. It'll be trailing you, \n so you better not be " + "caught "
                        + "drinking and slithering.\n"
                + "I hope you have fun with my " + "game :)");
        final JButton instructions = new JButton("Instructions");
        instructions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent l) {
                JOptionPane.showMessageDialog(frame, Guide, "Instructions", 
                        JOptionPane.PLAIN_MESSAGE);
            }
        });
        control_panel.add(instructions);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        court.reset();
        court.initHighScores();
    }

    /**
     * Main method run to start and run the game. Initializes the GUI elements
     * specified in Game and runs it. IMPORTANT: Do NOT delete! You MUST include
     * this in your final submission.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}