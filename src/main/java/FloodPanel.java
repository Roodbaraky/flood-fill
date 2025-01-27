import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

public class FloodPanel extends JPanel implements ActionListener, MouseMotionListener {
    int divisions = 40;
    boolean[][] painted = new boolean[divisions][divisions];
    int xStep = 600 / 40;
    int yStep = 600 / 40;
    Point pos;
    Timer timer;

    FloodPanel() {
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.lightGray);
        setFocusable(true);
        addMouseMotionListener(this);

        //need add mouse listener for click
        timer = new Timer(32, this);
        timer.start();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        var d = getSize();
        //        g.drawLine(0,0,200,200);
        for (int x = 0; x <= divisions; x++) {
            g.drawLine(xStep * x, 0, x * xStep, d.height);
        }

        for (int y = 0; y <= divisions; y++) {
            g.drawLine(0, y * yStep, d.width, y * yStep);
        }
        for (int y = 0; y < divisions; y++) {
            for (int x = 0; x < divisions; x++) {
                if (painted[y][x]) {
                    drawCell(g, x, y);
                }
            }
        }
    }

    private void drawCell(final Graphics g, final int x, final int y) {
        g.fillRect(x * xStep, y * yStep, xStep, yStep);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        repaint();
    }

    @Override
    public void mouseDragged(final MouseEvent e) {
        if (e.getModifiersEx() == InputEvent.BUTTON1_DOWN_MASK) {
            pos = e.getPoint();
            //add valid within dimensions
            var x = pos.x / xStep;
            var y = pos.y / yStep;
            painted[y][x] = true;
        }

    }

    @Override
    public void mouseMoved(final MouseEvent e) {

    }
}
