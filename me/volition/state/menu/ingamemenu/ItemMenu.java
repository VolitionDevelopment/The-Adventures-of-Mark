package me.volition.state.menu.ingamemenu;

import me.volition.state.battle.BattleState;


/**
 * Created by mccloskeybr on 2/8/2016.
 */
public class ItemMenu extends InGameMenu {
    public ItemMenu(BattleState battleState) {
        super(new String[]{"Go back"}, battleState);
    }

    @Override
    public void select(int currentIndex) {
        if (currentIndex == 0)
            getBattleState().setCurrentMenu(new BattleMenu(getBattleState()));
    }
}
