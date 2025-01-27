import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FloodPanel extends JPanel implements ActionListener {
    Timer timer;
    FloodPanel() {
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.lightGray);
        setFocusable(true);
        timer = new Timer(32, this);
        timer.start();

    }

    public void paintComponent(Graphics g){
        g.drawLine(0,0,200,200);
    }
    @Override
    public void actionPerformed(final ActionEvent e) {
        repaint();
    }
}
