import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FloodPanel extends JPanel implements ActionListener {
    int divisions = 40;
    boolean[][] painted = new boolean[divisions][divisions];
    int xStep = 600/40;
    int yStep = 600/40;
    Timer timer;
    FloodPanel() {
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.lightGray);
        setFocusable(true);
        timer = new Timer(32, this);
        timer.start();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        var d = getSize();
//        g.drawLine(0,0,200,200);
        for (int x = 0; x <=divisions ; x++) {
            g.drawLine(xStep * x ,0,  x*xStep, d.height);
        }

        for (int y = 0; y <=divisions; y++) {
            g.drawLine(0,y*yStep, d.width, y*yStep);
        }
    }
    @Override
    public void actionPerformed(final ActionEvent e) {
        repaint();
    }
}
