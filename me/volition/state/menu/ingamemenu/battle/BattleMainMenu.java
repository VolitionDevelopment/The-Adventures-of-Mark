package me.volition.state.menu.ingamemenu.battle;

import me.volition.state.StateManager;
import me.volition.state.battle.BattleState;
import me.volition.util.GameManager;

/**
 * Created by mccloskeybr on 2/8/2016.
 */
public class BattleMainMenu extends BattleMenu {
    public BattleMainMenu(BattleState battleState) {
        super(new String[]{"Moves", "Items", "Run Away"}, battleState);
    }

    @Override
    public void select(int currentIndex) {
        if (currentIndex == 0)
            setSubMenu(new MoveMenu(this));
        else if (currentIndex == 1)
            setSubMenu(new ItemMenu(this));
        else {
            getBattleState().getPlayer().setInBattle(false);
            StateManager.setCurrentState(GameManager.getInstance().getGameState());
        }
    }
}
