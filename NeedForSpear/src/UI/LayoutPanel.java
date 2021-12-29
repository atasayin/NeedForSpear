package UI;

import domain.*;
import domain.controller.KeyboardController;
import domain.controller.LayoutController;
import domain.obstacle.Obstacle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;

public class LayoutPanel extends JPanel implements ActionListener,MouseListener, KeyListener {

    /////////////////////////////////////////////////////////////////////////////////////

    // Timer
    private static final int TIMER_SPEED = 50;
    private static final int INFO_REFRESH_PERIOD = 3000;
    private Timer tm = new Timer(TIMER_SPEED,  this);

    // LayoutController
    private LayoutController LC;

    private int PANEL_WIDTH;
    private int PANEL_HEIGHT;

    private double C_PANEL_WIDTH = 0.6;

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

    public LayoutPanel(int frame_width, int frame_height) {

        this.LC = new LayoutController();
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


        g.drawRect(200, 200, 200 , 200);
        g2d.setTransform(old);

    }

    private void drawPaddle(Graphics2D g2d, Paddle d, int width, int height) throws IOException {
        // TODO Auto-generated method stub
        PaddleView.getInstance().draw(g2d, d, width, height);

    }


    public void initializeLayoutPanel() throws IOException {
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setMinimumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setMaximumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        //this.setSize(frame_width,frame_height);
    }



    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println(e.getXOnScreen());
        System.out.println(e.getX());
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

