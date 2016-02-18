package me.volition.state.menu.ingamemenu.battle;

import org.apache.commons.lang3.*;

/**
 * Created by mccloskeybr on 2/8/2016.
 */
public class ItemMenu extends BattleMenu {
    public ItemMenu(BattleMenu prevMenu) {
        super(ArrayUtils.addAll(new String[]{"Go back"}, prevMenu.getBattleState().getPlayer().getInventory_strarr()), prevMenu);
    }

    @Override
    public void select(int currentIndex) {
        if (currentIndex == 0)
            getPrevMenu().setSubMenu(null);
        else {
            getBattleState().getPlayer().useItem(currentIndex - 1);
            getBattleState().switchTurns();
        }
    }
}
