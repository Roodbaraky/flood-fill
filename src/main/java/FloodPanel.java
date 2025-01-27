import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;
import java.util.Stack;

import javax.swing.*;

public class FloodPanel extends JPanel implements ActionListener, MouseMotionListener, MouseListener {
    int divisions = 40;
    boolean[][] painted = new boolean[divisions][divisions];
    boolean[][] toFill = new boolean[divisions][divisions];

    int xStep = 600 / 40;
    int yStep = 600 / 40;
    Point pos;
    Queue<Point> rectsQueue = new ArrayDeque<>(600);
    Stack<Point> rectsStack = new Stack<>();
    Timer timer;

    FloodPanel() {
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.lightGray);
        setFocusable(true);
        addMouseMotionListener(this);
        addMouseListener(this);
        timer = new Timer(32, this);
        timer.start();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        var d = getSize();
        //Draw Grid
        for (int x = 0; x <= divisions; x++) {
            g.drawLine(xStep * x, 0, x * xStep, d.height);
        }

        for (int y = 0; y <= divisions; y++) {
            g.drawLine(0, y * yStep, d.width, y * yStep);
        }

        //Draw Painted Cells
        for (int y = 0; y < divisions; y++) {
            for (int x = 0; x < divisions; x++) {
                if (painted[y][x]) {
                    drawCell(g, x, y);
                }
            }
        }
        //FloodFill from Queue
        if (!rectsQueue.isEmpty()) {
            Point current = rectsQueue.poll();
            floodFillQueue(current);
        }
        //FloodFill from Stack
        if (!rectsStack.isEmpty()) {
            Point current = rectsStack.pop();
            floodFillStack(current);

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
        //use Queue
        if (e.getButton() == MouseEvent.BUTTON3) {
            rectsQueue.clear();
            rectsQueue.add(new Point(e.getX() / xStep, e.getY() / yStep));
        }
        //use Stack
        if (e.getButton() == MouseEvent.BUTTON2) {
            rectsStack.clear();
            rectsStack.push(new Point(e.getX() / xStep, e.getY() / yStep));
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

    public void floodFillStep(Point node, Collection<Point> pointCollection) {
        int x = (int) node.getX();
        int y = (int) node.getY();

        if (x < 0 || y < 0 || x >= divisions || y >= divisions || painted[y][x] || toFill[y][x]) {
            return;
        }

        toFill[y][x] = true;
        floodFillStep(new Point(x, y + 1), pointCollection);
        floodFillStep(new Point(x, y - 1), pointCollection);
        floodFillStep(new Point(x - 1, y), pointCollection);
        floodFillStep(new Point(x + 1, y), pointCollection);

    }

    public void floodFillStack(Point current) {
        var y = current.y;
        var x = current.x;
        if (x < 0 || y < 0 || x >= divisions || y >= divisions || painted[y][x] || toFill[y][x]) {
            return;
        }

        toFill[y][x] = true;
        painted[y][x] = true;

        rectsStack.push(new Point(x + 1, y));
        rectsStack.push(new Point(x - 1, y));
        rectsStack.push(new Point(x, y + 1));
        rectsStack.push(new Point(x, y - 1));

    }

    public void floodFillQueue(Point current) {
        var y = current.y;
        var x = current.x;
        if (x < 0 || y < 0 || x >= divisions || y >= divisions || painted[y][x] || toFill[y][x]) {
            return;
        }
        toFill[y][x] = true;
        painted[y][x] = true;
        rectsQueue.add(new Point(x, y - 1));
        rectsQueue.add(new Point(x, y + 1));
        rectsQueue.add(new Point(x - 1, y));
        rectsQueue.add(new Point(x + 1, y));
    }
}
