package UI;

import domain.DomainObject;
import domain.Game;
import domain.Paddle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PaddleView implements Drawable {

    BufferedImage paddle_img;
    static PaddleView instance;

    private PaddleView() {
    }

    public static PaddleView getInstance() {
        if (instance == null) {
            instance = new PaddleView();
            try {
                instance.fillImgs();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return instance;
    }


    private void fillImgs() throws IOException{
        // TODO Auto-generated method stub
        try {
            paddle_img = ImageIO.read(this.getClass().getResource("../assets/paddle1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // scale to unit length L
        int paddleLen = BuildModeScreen.FRAME_WIDTH/10;
        //int paddleLen = Game.getInstance().PC.getPaddle().getLength();
        int paddleThick = 20;
        Image scaled = paddle_img.getScaledInstance(paddleLen, paddleThick,
                BufferedImage.SCALE_SMOOTH);
        if (scaled instanceof BufferedImage)
            paddle_img = (BufferedImage) scaled;

        // Create a buffered image with transparency
        BufferedImage new_bimage = new BufferedImage(scaled.getWidth(null), scaled.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = new_bimage.createGraphics();
        bGr.drawImage(scaled, 0, 0, null);
        bGr.dispose();

        paddle_img = new_bimage;
    }

    @Override
    public void draw(Graphics2D g2d, DomainObject domainObject, int width, int height) {
        // TODO Auto-generated method stub
        Paddle paddle = (Paddle) domainObject;

        g2d.drawImage(paddle_img, paddle.getPosVector().getX(), paddle.getPosVector().getY(), null);
        //g2d.drawImage(paddle_img, (width-paddle.getLength())/2, (height-paddle.getThickness()-40), null);

    }


}
