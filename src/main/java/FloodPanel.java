import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.*;

public class FloodPanel extends JPanel implements ActionListener, MouseMotionListener, MouseListener {
    int divisions = 40;
    boolean[][] painted = new boolean[divisions][divisions];
    int xStep = 600 / 40;
    int yStep = 600 / 40;
    Point pos;
    Queue<Point> rects = new LinkedList<>() {
    };

    Timer timer;

    FloodPanel() {
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.lightGray);
        setFocusable(true);
        addMouseMotionListener(this);
        addMouseListener(this);
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
        if (rects != null && !rects.isEmpty()) {
            for (var rect : rects) {
                g.setColor(Color.red);
                drawCell(g, (int) rect.getX(), (int) rect.getY());
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

    @Override
    public void mouseClicked(final MouseEvent e) {

    }

    @Override
    public void mousePressed(final MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            pos = e.getPoint();
            var x = pos.x / xStep;
            var y = pos.y / yStep;
            //            rects.add(new Point(x, y));
            //            //if right-mouse, execute flood fill at clicked coords
            //            System.out.println("FloodFill()");
            floodFill(new Point(x, y));
        }
    }

    @Override
    public void mouseReleased(final MouseEvent e) {

    }

    @Override
    public void mouseEntered(final MouseEvent e) {

    }

    @Override
    public void mouseExited(final MouseEvent e) {

    }

    public void floodFill(Point node) {
        int x = (int) node.getX();
        int y = (int) node.getY();

        if (x < 0 || y < 0 || x >= divisions || y >= divisions || painted[y][x]) {
            return;
        }
        painted[y][x] = true;
        rects.add(node);
        floodFill(new Point(x, y + 1));
        floodFill(new Point(x, y - 1));
        floodFill(new Point(x - 1, y));
        floodFill(new Point(x + 1, y));
    }
}
