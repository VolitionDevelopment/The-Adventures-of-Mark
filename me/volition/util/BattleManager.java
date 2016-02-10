package me.volition.util;

import me.volition.entity.Entity;
import me.volition.entity.Player;
import me.volition.location.tile.Tile;
import me.volition.state.StateManager;
import me.volition.state.battle.BattleState;
import me.volition.state.game.GameState;
import me.volition.state.menu.ingamemenu.battle.BattleMenu;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by mccloskeybr on 2/4/16.
 */

//Best class ever
public class BattleManager {

    public static BattleMenu currentMenu;

    public static void startBattle(Player player, ArrayList<Entity> entities, BufferedImage bgTile){

        BattleState battleState = new BattleState(bgTile);
        battleState.setPlayer(player);
        battleState.setEnemies(entities);

        StateManager.setCurrentState(battleState);

    }
}
