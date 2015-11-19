import userInterface.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Arnold on 2015-11-12.
 */
public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new MainFrame();
                frame.setSize(480,640);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setTitle("E-media projekt nr 1, Arnold Woznica");
                frame.setVisible(true);
            }
        });
    }
}
