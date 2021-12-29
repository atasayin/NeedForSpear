package UI;

import domain.*;
import domain.controller.LayoutController;
import domain.obstacle.Obstacle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.BitSet;

@SuppressWarnings("serial")
public class LayoutPanel extends JPanel implements ActionListener,MouseListener, KeyListener {

    /////////////////////////////////////////////////////////////////////////////////////

    // Timer
    private static final int TIMER_SPEED = 50;
    private static final int INFO_REFRESH_PERIOD = 3000;
    private Timer tm = new Timer(TIMER_SPEED,  this);

    // LayoutController
    private LayoutController LC;
    private Layout layout;

    private int PANEL_WIDTH;
    private int PANEL_HEIGHT;
    private double C_PANEL_WIDTH = 0.6;

    // Ketbits
    private BitSet keyBits = new BitSet(256);


    /////////////////////////////////////////////////////////////////////////////////////

    public LayoutPanel(Layout layout, int frame_width, int frame_height) {
        super();
        this.setVisible(true);
        this.PANEL_WIDTH = frame_width;
        this.PANEL_HEIGHT = frame_height;
        try {
            initializeLayoutPanel();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.setBackground(Color.BLACK);

    }

    public LayoutPanel(LayoutController LC, int frame_width, int frame_height) {

        this.LC = LC;
        this.PANEL_WIDTH = (int) (frame_width * C_PANEL_WIDTH);
        this.PANEL_HEIGHT = frame_height;

        try {
            initializeLayoutPanel();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.setBackground(Color.LIGHT_GRAY);
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();

        for (DomainObject domainObject : Game.getInstance().getDomainObjectArr()) {
            try {
                drawComponent(g2d, domainObject);
            } catch (IOException e) {
                e.printStackTrace();
            }
            g2d.setTransform(old);

        }

        g2d.setTransform(old);

    }

    private void drawPaddle(Graphics2D g2d, Paddle d, int width, int height) throws IOException {
        // TODO Auto-generated method stub
        PaddleView.getInstance().draw(g2d, d, width, height);

    }

    private void drawComponent(Graphics2D g2d, DomainObject d) throws IOException {
        // TODO Auto-generated method stub
        if (d instanceof Obstacle) {
            ObstacleView.getInstance().draw(g2d, d, PANEL_WIDTH, PANEL_HEIGHT);
        }

    }


    public void initializeLayoutPanel() throws IOException {
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setMinimumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setMaximumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        tm.start();
        addMouseListener(this);
        addKeyListener(this);
        //this.setSize(frame_width,frame_height);
    }

    // Mouse Events
    @Override
    public void mouseClicked(MouseEvent e) {
        // Invoked when the mouse button has been clicked (pressed and released) on a component
        LC.setMouseInput(e.getX(),e.getY());
        LC.control();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Invoked when a mouse button has been pressed on a component

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Invoked when a mouse button has been released on a component

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    // Key Events
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        LC.setKeyInput(keyCode);
        LC.control();

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        keyBits.clear(keyCode);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();

    }
}

