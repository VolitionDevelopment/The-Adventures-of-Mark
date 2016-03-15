package volition.adv_of_mark.component;

import volition.adv_of_mark.Window;
import volition.adv_of_mark.mapObject.MapObject;
import volition.adv_of_mark.util.FontManager;
import volition.adv_of_mark.util.RenderUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Demerzel on 2/5/16.
 */
public class Textbox extends JPanel {
    private MapObject speaker;
    private String text;

    public Textbox(String text){
        this.text = text;
    }

    public Textbox(String text, MapObject speaker){
        this(text);
        this.speaker = speaker;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void render(Graphics2D g){

        g.setColor(Color.WHITE);
        g.fillRect(15, volition.adv_of_mark.Window.WINDOW_HEIGHT - 145, volition.adv_of_mark.Window.WINDOW_WIDTH - 30, 110);
        g.setColor(Color.BLACK);
        g.fillRect(20, volition.adv_of_mark.Window.WINDOW_HEIGHT - 140, volition.adv_of_mark.Window.WINDOW_WIDTH - 40, 100);

        g.setColor(Color.WHITE);
        g.setFont(FontManager.getMono(18));
        if(speaker != null){
            g.drawString(speaker.getName() + ": ", 25, volition.adv_of_mark.Window.WINDOW_HEIGHT - 120);
        }
        RenderUtils.drawWrappedText(g, "* " + text, 140, volition.adv_of_mark.Window.WINDOW_HEIGHT - 120, Window.WINDOW_WIDTH - 140);
    }

}
