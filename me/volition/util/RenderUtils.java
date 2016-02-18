package me.volition.util;

import java.awt.*;
import java.util.ArrayList;

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

    public static void drawCenteredText(Graphics2D g, String line, int y, int size){
        g.setColor(Color.WHITE);
        g.setFont(FontManager.getSans(size));
        g.drawString(line, (me.volition.Window.WINDOW_WIDTH / 2) - (g.getFontMetrics().stringWidth(line) / 2), y);
    }

    public static void drawLeftText(Graphics2D g, String line, int y, int size){
        g.setColor(Color.WHITE);
        g.setFont(FontManager.getSans(size));
        g.drawString(line, 20, y);
    }

    public static void drawOutlinedBox(Graphics2D g, int x, int y, int width, int height){
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.fillRect(x + 5, y + 5, width - 10, height - 10);
    }

    public static void drawWrappedText(Graphics2D g, String str, int x, int y, int width){
        ArrayList<String> strings = new ArrayList<>();

        int loclastBreak = 0;
        int locLastSpace = 0;
        int index = 0;

        while (index < str.length()) {
            if (str.charAt(index) == ' ')
                locLastSpace = index;
            else if (g.getFontMetrics().stringWidth(str.substring(loclastBreak, index)) > width) {
                strings.add(str.substring(loclastBreak, locLastSpace));
                loclastBreak = locLastSpace + 1;
            }
            index++;
            if (index == str.length())
                strings.add(str.substring(loclastBreak, str.length()));
        }

        for (int i = 0; i < strings.size(); i++)
            g.drawString(strings.get(i), x, y + 30 * i);
    }

    public static void drawBoxedWrappedText(Graphics2D g, String str, int x, int y, int width){
        ArrayList<String> strings = new ArrayList<>();

        int loclastBreak = 0;
        int locLastSpace = 0;
        int index = 0;

        while (index < str.length()) {
            if (str.charAt(index) == ' ')
                locLastSpace = index;
            else if (g.getFontMetrics().stringWidth(str.substring(loclastBreak, index)) > width) {
                strings.add(str.substring(loclastBreak, locLastSpace));
                loclastBreak = locLastSpace + 1;
            }
            index++;
            if (index == str.length())
                strings.add(str.substring(loclastBreak, str.length()));
        }

        drawOutlinedBox(g, x - 10, y - 20, width, strings.size() * 30);

        g.setColor(Color.WHITE);
        for (int i = 0; i < strings.size(); i++)
            g.drawString(strings.get(i), x, y + 20 * i);
    }

}
