package volition.adv_of_mark.state.menu.impl;

import volition.adv_of_mark.state.StateManager;
import volition.adv_of_mark.state.cutscene.OpeningCutscene;
import volition.adv_of_mark.state.menu.CenterTextMenu;
import volition.adv_of_mark.util.GameManager;
import volition.adv_of_mark.util.ImageManager;
import volition.adv_of_mark.util.RenderUtils;

import java.awt.*;

/**
 * Created by mccloskeybr on 2/3/16.
 */
public class MainMenu extends CenterTextMenu {

    private double tick;
    private int fontSize = 70;
    private boolean increasing = false;

    public MainMenu(){
        super(new ImageManager().loadImage("/volition/adv_of_mark/assets/image/menus/mainmenu.png"), new String[]{"New Game", "Continue", "Help", "About", "Quit"}, Color.WHITE, Color.RED);
    }

    @Override
    public void update(double delta){
        super.update(delta);

        float threshold = 2;
        tick += delta * 1;
        if (tick >= threshold){
            if (increasing) {
                fontSize++;
                if (fontSize >= 72)
                    increasing = false;
            } else {
                fontSize--;
                if (fontSize <= 68)
                    increasing = true;

            }
            tick = 0;
        }
    }

    @Override
    public void render(Graphics2D g){
        super.render(g);

        RenderUtils.drawCenteredText(g, "Adventures of Mark", 80, fontSize);
    }

    @Override
    public void select(int index) {

        //new game
        if (index == 0)
            StateManager.setCurrentState(new OpeningCutscene());

        //continue
        else if (index == 1)
            StateManager.setCurrentState(GameManager.getInstance().getGameState());

        //help
        else if (index == 2)
            StateManager.setCurrentState(new HelpMenu());

        //about
        else if (index == 3)
            StateManager.setCurrentState(new AboutMenu());

        //exit
        else
            System.exit(0);
    }
}
