package me.volition.util;

import java.awt.*;

/**
 * Created by Demerzel on 2/4/16.
 */
public class RenderUtils {
    private static void alias(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHints(rh);
    }

    public static void drawCenteredText(Graphics g, String line, int y, Font font){
        alias(g);
        g.drawString(line, (me.volition.Window.WINDOW_WIDTH / 2) - (g.getFontMetrics().stringWidth(line) / 2), 20);
    }

    public static void drawCenteredText(Graphics g, String line, int y){
        alias(g);
        g.drawString(line, (me.volition.Window.WINDOW_WIDTH / 2) - (g.getFontMetrics().stringWidth(line) / 2), 20);
    }

    public static void drawLeftText(Graphics g, String line, int y){
        alias(g);
        g.drawString(line, 20, y);
    }
}
