package UI;

import domain.*;
import domain.controller.KeyboardController;
import domain.obstacle.Obstacle;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LayoutPanel extends JPanel{

    BufferedImage img; // background
    String infoString = "";
    KeyboardController kc = new KeyboardController();


    public static int frame_width;
    public static int frame_height;

    public LayoutPanel(Layout layout, int frame_width, int frame_height) {
        super();
        this.setVisible(true);
        this.frame_width = frame_width;
        this.frame_height = frame_height;
        try {
            initializeLayoutPanel();

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setBackground(Color.YELLOW);

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();
        g2d.setTransform(old);
        int textWidth = g.getFontMetrics().stringWidth(infoString);
        g2d.drawString(infoString, this.getWidth() / 2 - textWidth / 2, 20);

    }

    private void drawObstacle(Graphics2D g2d, Obstacle d) {
        ObstacleView.getInstance().draw(g2d, d, frame_width, frame_height);

    }
    private void drawPaddle(Graphics2D g2d, Paddle d, int width, int height) throws IOException {
        PaddleView.getInstance().draw(g2d, d, width, height);

    }


    public void initializeLayoutPanel() throws IOException {
        this.setFocusable(true);
        this.setSize(frame_width,frame_height);
    }

    private static final int TIMER_SPEED = 50;
    private static final int INFO_REFRESH_PERIOD = 3000;

}

