package me.volition.state.cutscene;

import me.volition.Window;
import me.volition.component.Textbox;
import me.volition.state.State;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/6/16.
 */
public abstract class CutsceneState extends State{

    private String[] dialogue_text;
    private BufferedImage[] dialogue_images;
    private Textbox textbox;

    private int index;

    public CutsceneState(String[] dialogue_text) {
        this.dialogue_text = dialogue_text;
        this.dialogue_images = loadDialogue_images();

        this.textbox = new Textbox(dialogue_text[0]);
    }

    @Override
    public void init() {
        index = 0;
    }

    @Override
    public void update(double delta) {
        if (index == dialogue_text.length)
            finish();
    }

    @Override
    public void render(Graphics g) {
        if (index < dialogue_images.length) {
            if (dialogue_images[index] != null)
                g.drawImage(dialogue_images[index], 0, 0, Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT, null);

            textbox.render(g);
        }
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER || k == KeyEvent.VK_SPACE) {
            index++;
            if (index < dialogue_text.length)
                textbox.setText(dialogue_text[index]);
        }
    }

    @Override
    public void keyReleased(int k) {}

    public abstract void finish();

    public abstract BufferedImage[] loadDialogue_images();

}
