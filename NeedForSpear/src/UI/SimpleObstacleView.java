package UI;

import domain.obstacle.Obstacle;
import domain.util.PosVector;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import domain.*;

public class SimpleObstacleView  implements Drawable {

        //Obstacle obstacle;
        BufferedImage simple_obs_img ;

        static SimpleObstacleView instance;

        private SimpleObstacleView() {
        }

        public static SimpleObstacleView getInstance() {
            if (instance == null) {
                instance = new SimpleObstacleView();
                try {
                    instance.fillImgs();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return instance;
        }

        private void fillImgs() throws IOException {
            try {
                simple_obs_img = ImageIO.read(this.getClass().getResource("../assets/paddle1.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            // scale to unit length L
            int paddleLen = BuildModeScreen.FRAME_WIDTH/10;
            int paddleThick = 20;
            Image scaled = simple_obs_img.getScaledInstance(paddleLen/5, paddleThick,
                    BufferedImage.SCALE_SMOOTH);
            if (scaled instanceof BufferedImage)
                simple_obs_img = (BufferedImage) scaled;

            // Create a buffered image with transparency
            BufferedImage new_bimage = new BufferedImage(scaled.getWidth(null), scaled.getHeight(null),
                    BufferedImage.TYPE_INT_ARGB);

            // Draw the image on to the buffered image
            Graphics2D bGr = new_bimage.createGraphics();
            bGr.drawImage(scaled, 0, 0, null);
            bGr.dispose();

            simple_obs_img = new_bimage;
//            simple_obs_img = ImageIO.read(new File("src/assets/simpleball.png"));
//
//            int obs_size = (int) (25 * 0.6);
//            int offset = 10;
//            simple_obs_img = scaleBImage(simple_obs_img, obs_size, obs_size);

        }

//        private BufferedImage scaleBImage(BufferedImage bimg, int width, int height) {
//            // scale to unit length L
//            Image scaled = bimg.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
//            if (scaled instanceof BufferedImage)
//                return (BufferedImage) scaled;
//
//            // Create a buffered image with transparency
//            BufferedImage new_bimage = new BufferedImage(scaled.getWidth(null), scaled.getHeight(null),
//                    BufferedImage.TYPE_INT_ARGB);
//
//            // Draw the image on to the buffered image
//            Graphics2D bGr = new_bimage.createGraphics();
//            bGr.drawImage(scaled, 0, 0, null);
//            bGr.dispose();
//
//            return new_bimage;
//        }

        @Override
        public void draw(Graphics2D g2d, DomainObject domainObject, int w, int h) {
            Obstacle obs = (Obstacle) domainObject;

            g2d.drawImage(simple_obs_img, obs.getPosVector().getX(), obs.getPosVector().getY(), null);
//            Obstacle obs = (Obstacle) object;
//            String type = Obstacle.getType();
//            BufferedImage bi;
//
//            // deciding which image to use
//            switch (type) {
//
//                case "WallMaria":
//                    bi = simple_obs_img;
//                    break;
//                default:
//                    bi = simple_obs_img;
//            }
//
//            // getting the location
//            PosVector pos = obs.getPosVector();
//
//            // drawing the component
//            g2d.drawImage(bi, pos.getX(), pos.getY(), null);
//            // give width and height information of the image for collision checks
//            obs.setWidthHeight(bi.getWidth(), bi.getHeight());

        }
    }


