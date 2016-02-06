package me.volition.util;

import me.volition.*;
import me.volition.Window;

import java.awt.*;

/**
 * Created by Demerzel on 2/4/16.
 */
public class RenderUtils {
    public static void alias(Graphics g){
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

    public static void drawLeftText(Graphics g, String line, int y, int size){
        alias(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Determination Mono", Font.PLAIN, size));
        g.drawString(line, 20, y);
    }

    public static void drawTextBox(Graphics g, String text){
        Graphics2D g2 = alias(g);

        g2.setColor(Color.WHITE);
        g2.fillRect(15, Window.WINDOW_HEIGHT - 145, Window.WINDOW_WIDTH - 30, 110);
        g2.setColor(Color.BLACK);
        g2.fillRect(20, Window.WINDOW_HEIGHT - 140, Window.WINDOW_WIDTH - 40, 100);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Determination Mono", Font.PLAIN, 18));
        g.drawString(text, 25, Window.WINDOW_HEIGHT - 120);
    }
}
