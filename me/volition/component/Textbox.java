package me.volition.component;

import me.volition.*;
import me.volition.entity.Entity;
import me.volition.util.RenderUtils;

import javax.swing.*;
import javax.xml.soap.Text;
import java.awt.*;
import java.awt.Window;

/**
 * Created by Demerzel on 2/5/16.
 */
public class Textbox extends JPanel {
    private Entity entity;
    private String text;

    public Textbox(String text){
        this.text = text;
    }

    public Textbox(String text, Entity entity){
        this(text);
        this.entity = entity;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void render(Graphics g){

        Graphics2D g2 = (Graphics2D) g;
        RenderUtils.alias(g);

        g2.setColor(Color.WHITE);
        g2.fillRect(15, me.volition.Window.WINDOW_HEIGHT - 145, me.volition.Window.WINDOW_WIDTH - 30, 110);
        g2.setColor(Color.BLACK);
        g2.fillRect(20, me.volition.Window.WINDOW_HEIGHT - 140, me.volition.Window.WINDOW_WIDTH - 40, 100);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Determination Mono", Font.PLAIN, 18));
        if(entity != null){
            g.drawString(entity.getName() + ": ", 25, me.volition.Window.WINDOW_HEIGHT - 120);
        }
        g.drawString("* " + text, 140, me.volition.Window.WINDOW_HEIGHT - 120);
    }

}
