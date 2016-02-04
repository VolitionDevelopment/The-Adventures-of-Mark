package me.volition.util;

import java.awt.*;

/**
 * Created by Demerzel on 2/4/16.
 */
public class RenderUtils {
    public static void drawCenteredText(Graphics g, String line, int y, Font font){
        g.setFont(font);
        g.drawString(line, (me.volition.Window.WINDOW_WIDTH / 2) - (g.getFontMetrics().stringWidth(line) / 2), 20);
    }

    public static void drawCenteredText(Graphics g, String line, int y){
        g.drawString(line, (me.volition.Window.WINDOW_WIDTH / 2) - (g.getFontMetrics().stringWidth(line) / 2), 20);
    }

    public static void drawLeftText(Graphics g, String line, int y){
        g.drawString(line, 20, y);
    }
}
