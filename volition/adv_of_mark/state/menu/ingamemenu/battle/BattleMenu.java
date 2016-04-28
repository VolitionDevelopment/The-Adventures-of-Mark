package volition.adv_of_mark.state.menu.ingamemenu.battle;

import volition.adv_of_mark.mapObject.entity.Entity;
import volition.adv_of_mark.state.battle.BattleState;
import volition.adv_of_mark.state.menu.ingamemenu.InGameMenu;
import volition.adv_of_mark.util.FontManager;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by mccloskeybr on 2/8/2016.
 */
//similar to menustate, except is overlayed on top of the game / battle state
public abstract class BattleMenu extends InGameMenu {

    private BattleMenu prevMenu, subMenu;
    private int x;
    private Entity selectedEntity; //only for movemenu/entitymenu

    private BattleState state;

    public BattleMenu(String[] options, BattleState state) {
        super(options);
        this.state = state;
        x = 20;
    }

    public BattleMenu(String[] options, BattleMenu prevMenu) {
        super(options);
        this.prevMenu = prevMenu;
        x = prevMenu.getX() + 70;
    }

    public Entity getSelectedEntity(){
        return selectedEntity;
    }

    public int getX(){
        return x;
    }

    public BattleMenu getPrevMenu(){
        return prevMenu;
    }

    public BattleState getBattleState(){
        if (state == null && prevMenu != null)
            return prevMenu.getBattleState();

        return state;
    }

    public void setSelectedEntity(Entity entity){
        this.selectedEntity = entity;
    }

    public void setSubMenu(BattleMenu subMenu){
        this.subMenu = subMenu;
    }

    @Override
    public void update(){
        if (subMenu == null) {
            super.update();
        } else
            subMenu.update();
    }

    @Override
    public void keyPressed(int k){
        if (subMenu == null) {
            super.keyPressed(k);

            if (k == KeyEvent.VK_S) {
                setCurrentIndex(getCurrentIndex() + 1);
                onKeyPress();
            } else if (k == KeyEvent.VK_W) {
                setCurrentIndex(getCurrentIndex() - 1);
                onKeyPress();
            } else if (k == KeyEvent.VK_D && getCurrentIndex() + 5 < getOptions().length) {
                setCurrentIndex(getCurrentIndex() + 5);
                onKeyPress();
            } else if (k == KeyEvent.VK_A && getCurrentIndex() - 5 > -1) {
                setCurrentIndex(getCurrentIndex() - 5);
                onKeyPress();
            }

        } else {
            subMenu.keyPressed(k);
        }
    }

    @Override
    public void render(Graphics2D g){

        g.setFont(FontManager.getSans(14));

        int y = 3 * volition.adv_of_mark.Window.WINDOW_HEIGHT / 4;
        int x = this.x;
        for (int i = 0; i < getOptions().length; i++) {
            if (i == getCurrentIndex())
                g.setColor(Color.RED);
            else
                g.setColor(Color.WHITE);

            g.drawString(getOptions()[i], x, y);

            y += 30;
            if (y >= volition.adv_of_mark.Window.WINDOW_HEIGHT - 30) {
                y = 3 * volition.adv_of_mark.Window.WINDOW_HEIGHT / 4;
                x += 120;
            }
        }

        if (subMenu != null)
            subMenu.render(g);
    }

    @Override
    public abstract void select(int currentIndex);

}
