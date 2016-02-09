package me.volition.state.menu.ingamemenu;

import me.volition.state.battle.BattleState;

/**
 * Created by mccloskeybr on 2/8/2016.
 */
public class BattleMenu extends InGameMenu{
    public BattleMenu(BattleState battleState) {
        super(new String[]{"Moves", "Items", "Run Away"}, battleState);
    }

    @Override
    public void select(int currentIndex) {
        if (currentIndex == 0)
            getBattleState().setCurrentMenu(new MoveMenu(getBattleState()));
        else if (currentIndex == 1)
            getBattleState().setCurrentMenu(new ItemMenu(getBattleState()));
        else;
            //run away
    }
}
