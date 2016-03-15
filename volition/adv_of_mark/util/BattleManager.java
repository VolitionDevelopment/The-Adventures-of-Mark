package volition.adv_of_mark.util;

import volition.adv_of_mark.mapObject.entity.Entity;
import volition.adv_of_mark.mapObject.entity.Player;
import volition.adv_of_mark.mapObject.entity.enemies.Chili;
import volition.adv_of_mark.mapObject.entity.enemies.Fratkid;
import volition.adv_of_mark.mapObject.entity.enemies.Stoner;
import volition.adv_of_mark.state.StateManager;
import volition.adv_of_mark.state.battle.BattleState;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mccloskeybr on 2/4/16.
 */

//Best class ever
public class BattleManager {

    public static void startBattle(Player player, ArrayList<Entity> entities, BufferedImage bgTile){

        BattleState battleState = new BattleState(bgTile);
        battleState.setPlayer(player);
        battleState.setEnemies(entities);

        StateManager.setCurrentState(battleState);

    }

    public static void startBattle(Player player, BufferedImage bgTile){

        BattleState battleState = new BattleState(bgTile);
        battleState.setPlayer(player);

        ArrayList<Entity> entities = new ArrayList<>();
        Random random = new Random();
        int size = random.nextInt(3) + 1;
        for (int i = 0; i < size; i++) {
            int mob = random.nextInt(3);
            if (mob == 0)
                entities.add(new Fratkid());
            else if (mob == 1)
                entities.add(new Chili());
            else
                entities.add(new Stoner());
        }
        battleState.setEnemies(entities);

        StateManager.setCurrentState(battleState);

    }
}
