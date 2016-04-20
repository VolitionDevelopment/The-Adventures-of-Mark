package volition.adv_of_mark.state.menu.ingamemenu.battle;

import volition.adv_of_mark.state.StateManager;
import volition.adv_of_mark.state.battle.BattleState;
import volition.adv_of_mark.util.GameManager;

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
            getBattleState().getPlayer().setAbleMove(true);
            StateManager.setCurrentState(GameManager.getInstance().getGameState());
        }
    }
}
