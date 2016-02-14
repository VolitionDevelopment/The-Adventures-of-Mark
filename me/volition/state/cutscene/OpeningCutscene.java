package me.volition.state.cutscene;

import me.volition.*;
import me.volition.state.StateManager;
import me.volition.util.ImageManager;

import java.awt.*;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/6/16.
 */
public class OpeningCutscene extends CutsceneState {

    private double stringIndex;
    private String str;
    private float alpha;

    public OpeningCutscene() {
        super(
                new String[]{
                        "Mark.                                   ",
                        "The young sung hero of new.             ",
                        "Vanquisher of...                        ",
                        "Nothing.                                ",
                }
        );
        alpha = 1;
        str = "";
    }

    @Override
    public BufferedImage[] loadDialogue_images() {
        ImageManager im = new ImageManager();
        BufferedImage[] images = new BufferedImage[4];

        images[0] = im.loadImage("/me/volition/assets/image/cutscene/opening1.png");
        images[1] = im.loadImage("/me/volition/assets/image/cutscene/opening2.png");
        images[2] = im.loadImage("/me/volition/assets/image/cutscene/opening1.png");
        images[3] = im.loadImage("/me/volition/assets/image/cutscene/opening2.png");
        return images;
    }

    @Override
    public void update(double delta){
        super.update(delta);
        stringIndex += delta / 3;

        if (stringIndex < 10)
            alpha -= delta * delta;
        else if (stringIndex > 30)
            alpha += delta * delta;

        if (alpha > 1)
            alpha = 1;
        else if (alpha < 0)
            alpha = 0;

        if ((int)stringIndex == getCurrentString().length()) {
            stringIndex = 0;
            setIndex(getIndex() + 1);
        }

        if (!str.equals(getCurrentString().substring(0, (int) stringIndex))) {
            str = getCurrentString().substring(0, (int) stringIndex);
            getTextbox().setText(str);
        }

    }

    @Override
    public void render(Graphics g){
        if (getIndex() < getDialogue_images().length && getDialogue_images()[getIndex()] != null)
                g.drawImage(getDialogue_images()[getIndex()], 0, 0, me.volition.Window.WINDOW_WIDTH, me.volition.Window.WINDOW_HEIGHT, null);

        g.setColor(new Color(0, 0, 0, alpha));
        g.fillRect(0, 0, me.volition.Window.WINDOW_WIDTH, me.volition.Window.WINDOW_HEIGHT);
        getTextbox().render(g);
    }

    @Override
    public void finish() {
        StateManager.setCurrentState(StateManager.GAME_INDEX);
    }

    @Override
    public void keyPressed(int k){
        if (k == KeyEvent.VK_SPACE)
            finish();
    }
}
