package volition.adv_of_mark.state.menu.ingamemenu.battle;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by mccloskeybr on 2/8/2016.
 */
public class MoveMenu extends BattleMenu {

    public MoveMenu(BattleMenu prevMenu) {
        super(ArrayUtils.addAll(new String[]{"Go back"}, prevMenu.getBattleState().getPlayer().getMoves_strarr()), prevMenu);
    }

    @Override
    public void select(int currentIndex) {
        if (currentIndex == 0)
            getPrevMenu().setSubMenu(null);
        else {
            if (getSelectedEntity() == null)
                setSubMenu(new EntityMenu(this, getBattleState().getLiveEnemies()));
            else {
                getBattleState().getPlayer().useMove(currentIndex - 1, getSelectedEntity());
                getBattleState().startConversation();
                getBattleState().switchTurns();
            }
        }

    }
}
