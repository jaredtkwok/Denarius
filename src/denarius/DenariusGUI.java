/*  
 *******************************************************************************
 *  Denarius
 *  DenariusGUI.java
 *  GUI version of Denarius
 *  Author: Jared Kwok
 *******************************************************************************
 */
package denarius;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class DenariusGUI {

    public static void main(String[] args) {
        //GameView game = new GameView();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int width = screenSize.width;
        final int height = screenSize.height;
        JFrame frame = new JFrame("Deal or No Deal GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.getContentPane().add(game);
        frame.setLocation(width / 4, height / 4);
        frame.setSize(600, 450);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
