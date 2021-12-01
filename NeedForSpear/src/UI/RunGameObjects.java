package UI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.*;

import domain.controller.KeyboardController;
import domain.* ;
import domain.obstacle.Obstacle;
import domain.util.PosVector;


@SuppressWarnings("serial")
public class RunGameObjects extends JPanel implements ActionListener, KeyListener {

    Timer tm = new Timer(TIMER_SPEED, this);
    BufferedImage img; // background
    String infoString = "";
    int infoRefreshCount;
    KeyboardController kc = new KeyboardController();
    Game game = Game.getInstance();

    public static int frame_width;
    public static int frame_height;


    public RunGameObjects(int width, int height) {
        this.frame_width = width;
        this.frame_height = height;
        try {
            initializeRunModeScreen();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();
        g2d.drawImage(img, 0, 0, null);

        for (DomainObject domainObject : Game.getInstance().getDomainObjectArr()) {
            drawComponent(g2d, domainObject);
            g2d.setTransform(old);

        }

        drawPaddle(g2d, Game.getInstance().PC.getPaddle(), frame_width, frame_height);

        //drawBall(g2d, player.getShooter());
        g2d.setTransform(old);
        int textWidth = g.getFontMetrics().stringWidth(infoString);
        g2d.drawString(infoString, this.getWidth() / 2 - textWidth / 2, 20);
    }

    private void drawObstacle(Graphics2D g2d, Obstacle d) {
        // TODO Auto-generated method stub
        SimpleObstacleView.getInstance().draw(g2d, d, frame_width, frame_height);

    }
    private void drawPaddle(Graphics2D g2d, Paddle d, int width, int height) {
        // TODO Auto-generated method stub
        PaddleView.getInstance().draw(g2d, d, width, height);

    }

    private void drawComponent(Graphics2D g2d, DomainObject d) {
        // TODO Auto-generated method stub
        if (d instanceof Obstacle) {
            SimpleObstacleView.getInstance().draw(g2d, d, frame_width, frame_height);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        //Game.getInstance().gameState.checkCollisions();
        update();
        repaint();
        //Game.getInstance().gameState.removeObjectsIfOutsideScreen();
        infoRefreshCount += TIMER_SPEED;
        if (infoRefreshCount >= INFO_REFRESH_PERIOD) {
            infoString = "";
            infoRefreshCount = 0;
        }

    }


    public void update() {
//        for (DomainObject domainObject : Game.getInstance().getDomainObjectArr()) {
//            domainObject.updatePosition();
//            domainObject.updateAngle();
//        }
//        Game.getInstance().gameState.updatePaddlePosition();
        Game.getInstance().PC.getPaddle().updatePosition(0,0);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int input = e.getKeyCode();
        // pause resume

        switch (input) {
            case 80: // p: pause
                infoString = "Game Paused.";
                repaint();
                tm.stop();
                Game.getInstance().gameState.isRunning = false;
                //lastPos = Game.getInstance().PC.getPaddle().getPosVector();
                //System.out.println(lastPos);

                return;
            case 82: // r: resume
                infoString = "Game Resumed.";
                tm.restart();
                Game.getInstance().gameState.isRunning = true;

                break;
            case 83: // s: save
                if (!tm.isRunning()) {
                    infoString = "Game Saved.";
                    kc.getInput(input, Game.getInstance().gameState.getPC().getPaddle()); // only works if game was paused
                    return;
                } else {
                    infoString = "Press \"Pause\" Button before saving.";
                    return;
                }
            case 76: // l: load
                if (!tm.isRunning()) {
                    infoString = "Game Loaded.";
                    kc.getInput(input, Game.getInstance().gameState.getPC().getPaddle());
                    tm.restart();// only works if game was paused
                    Game.getInstance().gameState.isRunning = true;
                    Game.getInstance().getPlayers().get(0).getPlayerState().notifyAllInventoryListeners("all");
                    return;
                } else {
                    infoString = "Press \"Pause\" Button before loading.";
                    return;
                }
            default:
        }

        if (kc.getInput(input, Game.getInstance().gameState.getPC().getPaddle())) { // when returns true restart
            tm.restart();
            Game.getInstance().gameState.isRunning = true;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub
            kc.released(Game.getInstance().gameState.getPC().getPaddle());
    }

    public void initializeRunModeScreen() throws IOException {
        this.setFocusable(true);
        tm.start();

    }

    private static final int TIMER_SPEED = 10;
    private static final int INFO_REFRESH_PERIOD = 3000;

}



