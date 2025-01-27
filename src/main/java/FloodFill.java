import javax.swing.*;

public class FloodFill {
    public static void main(String[] args) {
        System.out.println("Working");

        JFrame frame = new JFrame("Flood Fill");
        frame.setVisible(true);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        var floodPanel = new FloodPanel();
        frame.add(floodPanel);
        frame.pack();
        floodPanel.requestFocus();

    }
}
