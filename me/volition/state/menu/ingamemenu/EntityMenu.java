package me.volition.state.menu.ingamemenu;

import me.volition.state.battle.BattleState;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by mccloskeybr on 2/9/16.
 */
public class EntityMenu extends InGameMenu {
    public EntityMenu(InGameMenu prevMenu) {
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
