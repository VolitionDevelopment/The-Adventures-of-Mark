package me.volition.util;

import me.volition.Window;

import java.awt.*;

/**
 * Created by Demerzel on 2/4/16.
 */
public class RenderUtils {
    public static Graphics2D alias(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHints(rh);
        return g2;
    }

    public static void drawCenteredText(Graphics g, String line, int y, Font font){
        Graphics g2 = alias(g);
        g2.setFont(font);
        g2.drawString(line, (me.volition.Window.WINDOW_WIDTH / 2) - (g.getFontMetrics().stringWidth(line) / 2), y);
    }

    public static void drawCenteredText(Graphics g, String line, int y, int size){
        Graphics g2 = alias(g);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Determination Sans", Font.PLAIN, size));
        g2.drawString(line, (me.volition.Window.WINDOW_WIDTH / 2) - (g.getFontMetrics().stringWidth(line) / 2), y);
    }

    public static void drawLeftText(Graphics g, String line, int y, int size){
        Graphics g2 = alias(g);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Determination Sans", Font.PLAIN, size));
        g2.drawString(line, 20, y);
    }

}
