package me.volition.state.menu.ingamemenu;

import me.volition.state.battle.BattleState;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by mccloskeybr on 2/8/2016.
 */
public class MoveMenu extends InGameMenu {

    public MoveMenu(BattleState battleState) {
        super(ArrayUtils.addAll(new String[]{"Go back"}, battleState.getPlayer().getMoves_strarr()), battleState);
    }

    @Override
    public void select(int currentIndex) {
        if (currentIndex == 0)
            getBattleState().setCurrentMenu(new BattleMenu(getBattleState()));
    }
}
