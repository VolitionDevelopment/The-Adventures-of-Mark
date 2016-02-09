package me.volition.state.menu.ingamemenu;

import org.apache.commons.lang3.*;


/**
 * Created by mccloskeybr on 2/8/2016.
 */
public class ItemMenu extends InGameMenu {
    public ItemMenu(InGameMenu prevMenu) {
        super(ArrayUtils.addAll(new String[]{"Go back"}, prevMenu.getBattleState().getPlayer().getInventory_strarr()), prevMenu);
    }

    @Override
    public void select(int currentIndex) {
        if (currentIndex == 0)
            getPrevMenu().setSubMenu(null);
    }
}
