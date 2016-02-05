package me.volition.state.menu;

import me.volition.Window;
import me.volition.state.StateManager;
import me.volition.util.ImageManager;

import java.awt.*;

/**
 * Created by mccloskeybr on 2/3/16.
 */
public class HelpMenu extends BottomTextMenu {

    public HelpMenu(){
        super (new ImageManager().loadImage("/me/volition/assets/image/menus/helpmenu.png"), new String[]{"Go back"}, new Color(255, 255, 255), new Color(255, 0, 0));
    }

    @Override
    public void select(int index) {
        if (index == 0)
            StateManager.setCurrentState(StateManager.MAIN_MENU_INDEX);
    }

    @Override
    public void render(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);

        super.render(g);

    }
}
