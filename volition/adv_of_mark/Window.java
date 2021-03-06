package volition.adv_of_mark;

import javax.swing.*;
import java.awt.event.KeyListener;

/**
 * Created by mccloskeybr on 2/3/16.
 */
public class Window {
    public static final int WINDOW_WIDTH = 832;
    public static final int WINDOW_HEIGHT = 640;

    private JFrame frame;

    public Window(JPanel jPanel){
        frame = new JFrame();
        frame.setTitle("The Adventures of Mark");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT + 23);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(jPanel);
        frame.setVisible(true);
    }

    public void addKeyListener(KeyListener keyListener){
        frame.addKeyListener(keyListener);
    }

}
