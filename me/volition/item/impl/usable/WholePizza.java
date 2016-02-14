package me.volition.item.impl.usable;

import me.volition.entity.Player;
import me.volition.util.ImageManager;

/**
 * Created by Demerzel on 2/4/16.
 */
public class WholePizza extends Usable {
    public WholePizza() {
        super("Whole Pizza", "An entire pizza. The smell makes your eyes water and your stomach grumble.", "Restores all of your Tolerance.", 60, 0, ImageManager.getInstance().loadImage("/me/volition/assets/image/items/fullpizza.png"));
    }

    @Override
    public void use(Player player) {
        player.modTolerance(getValue());
    }
}
