package com.iceye.assignment.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImageService {

    Logger log = Logger.getLogger(this.getClass());

    private static final String FONT_TYPE = "Arial";
    private static final int FONT_SIZE = 48;
    public static final String IMAGE_TYPE = "jpg";

    private static Map<RenderingHints.Key, Object> renderingProperties = new HashMap<>();

    static {
        renderingProperties.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        renderingProperties.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        renderingProperties.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
    }

    /**
     * Converts a text to an Image
     *
     * @param text
     * @return image byte array
     */
    public byte[] convertTextToImage(String text){
        log.info("Converting text '" + text + "' to image");

        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Font font = new Font(FONT_TYPE, Font.PLAIN, FONT_SIZE);
        Graphics2D g2d = img.createGraphics();
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        img = new BufferedImage(fm.stringWidth(text), fm.getHeight(), BufferedImage.TYPE_INT_ARGB);

        g2d.dispose();
        g2d = img.createGraphics();
        g2d.setRenderingHints(renderingProperties);
        g2d.setFont(font);
        g2d.setBackground(Color.WHITE);
        g2d.setColor(Color.BLACK);
        g2d.drawString(text, 0, g2d.getFontMetrics().getAscent());
        g2d.dispose();

        return getByteArray(img);
    }

    /**
     * Extract the byte array data from an @{@link BufferedImage} object
     *
     * @param img {@link BufferedImage}
     * @return image byte array
     */
    private byte[] getByteArray(BufferedImage img) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write( img, IMAGE_TYPE, baos);
            baos.flush();
            baos.close();
        } catch (IOException e) {
            log.error("Error extracting byte array from BufferedImage: ", e);
        }
        return baos.toByteArray();
    }
}
