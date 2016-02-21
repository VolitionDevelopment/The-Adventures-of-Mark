package me.volition.item.impl.usable;

import me.volition.mapObject.entity.Player;
import me.volition.util.ImageManager;

/**
 * Created by Demerzel on 2/4/16.
 */
public class WholePizza extends Usable {
    public WholePizza() {
        super("Pizza", "An entire pizza. The smell makes your eyes water and your stomach grumble.", "Restores all of your Tolerance.", 60, -1, ImageManager.getInstance().loadImage("/me/volition/assets/image/items/usables/fullpizza.png"));
    }

    @Override
    public void use(Player player) {
        player.modTolerance(player.getBaseTolerance());
    }
}
