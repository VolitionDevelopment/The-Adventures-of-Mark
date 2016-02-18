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
public abstract class CutsceneState implements State{

    private String[] dialogue_text;
    private BufferedImage[] dialogue_images;
    private Textbox textbox;

    private int index;

    public CutsceneState(String[] dialogue_text) {
        this.dialogue_text = dialogue_text;
        this.dialogue_images = loadDialogue_images();

        this.textbox = new Textbox(dialogue_text[0]);
    }

    public String getCurrentString(){
        if (index < dialogue_text.length)
            return dialogue_text[index];
        return "";
    }

    public int getIndex(){
        return index;
    }

    public void setIndex(int i){
        index = i;
    }

    public Textbox getTextbox(){
        return textbox;
    }

    public BufferedImage[] getDialogue_images(){
        return dialogue_images;
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
    public void render(Graphics2D g) {
        if (index < dialogue_images.length && dialogue_images[index] != null)
                g.drawImage(dialogue_images[index], 0, 0, Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT, null);
        textbox.render(g);
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
