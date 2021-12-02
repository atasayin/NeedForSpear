package UI;

import domain.DomainObject;
import domain.Ball;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BallView implements Drawable {

    BufferedImage Ball_img;
    static BallView instance;

    private BallView() {
    }

    public static BallView getInstance() {
        if (instance == null) {
            instance = new BallView();
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
            Ball_img = ImageIO.read(this.getClass().getResource("../assets/simpleball.png"));
        } catch (Exception e) {
            e.printStackTrace();

        }

        // scale to unit length L
        Image scaled = Ball_img.getScaledInstance(16, 16,
                BufferedImage.SCALE_SMOOTH);
        if (scaled instanceof BufferedImage)
            Ball_img = (BufferedImage) scaled;

        // Create a buffered image with transparency
        BufferedImage new_bimage = new BufferedImage(scaled.getWidth(null), scaled.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = new_bimage.createGraphics();
        bGr.drawImage(scaled, 0, 0, null);
        bGr.dispose();

        Ball_img = new_bimage;
    }

    @Override
    public void draw(Graphics2D g2d, DomainObject domainObject, int width, int height) {
        // TODO Auto-generated method stub
        Ball Ball = (Ball) domainObject;

        g2d.drawImage(Ball_img, Ball.getPosVector().getX(), Ball.getPosVector().getY(), null);
        //g2d.drawImage(Ball_img, (width-Ball.getLength())/2, (height-Ball.getThickness()-40), null);

    }


}
