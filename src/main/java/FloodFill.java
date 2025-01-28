import java.awt.*;

import javax.swing.*;

public class FloodFill {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Flood Fill");
        frame.setVisible(true);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(new Dimension(600, 600));
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        var floodPanel = new FloodPanel();
        frame.add(floodPanel);
        frame.pack();
        floodPanel.requestFocus();

    }
}
