package me.volition.state.menu.ingamemenu.battle;

import me.volition.entity.Entity;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;

/**
 * Created by mccloskeybr on 2/9/16.
 */
public class EntityMenu extends BattleMenu {

    private ArrayList<Entity> entities;

    public EntityMenu(BattleMenu prevMenu, ArrayList<Entity> entities) {
        super(ArrayUtils.addAll(new String[]{"Go back"}, prevMenu.getBattleState().getEnemies_strarr()), prevMenu);
        this.entities = entities;
    }

    @Override
    public void select(int currentIndex) {
        if (currentIndex == 0)
            getPrevMenu().setSubMenu(null);
        else {
            getPrevMenu().setSelectedEntity(entities.get(currentIndex - 1));
            getPrevMenu().select(getPrevMenu().getCurrentIndex());
        }
    }
}
