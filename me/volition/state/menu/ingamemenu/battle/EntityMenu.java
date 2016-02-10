package me.volition.state.menu.ingamemenu.battle;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by mccloskeybr on 2/9/16.
 */
public class EntityMenu extends BattleMenu {
    public EntityMenu(BattleMenu prevMenu) {
        super(ArrayUtils.addAll(new String[]{"Go back"}, prevMenu.getBattleState().getEnemies_strarr()), prevMenu);
    }

    @Override
    public void select(int currentIndex) {
        if (currentIndex == 0)
            getPrevMenu().setSubMenu(null);
        else {
            getPrevMenu().setSelectedEntity(getBattleState().getEnemy(currentIndex - 1));
            getPrevMenu().select(getPrevMenu().getCurrentIndex());
        }
    }
}