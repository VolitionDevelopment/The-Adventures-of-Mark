package me.volition.mapObject.entity.shopkeepers;

import me.volition.location.tile.Tile;
import me.volition.mapObject.entity.Entity;
import me.volition.mapObject.ObjectEvent;
import me.volition.util.Animator;
import me.volition.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/17/16.
 */
public class Peppito extends Entity {

    private Animator idle;

    public Peppito(double x, double y) {
        super("The Legendary Peppito Himself", "I heard he learned how to make pizzas from Mr. Hanky.", ObjectEvent.OPEN_ITEMSTORE, 100, 5, 5, x, y, Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
    }

    @Override
    public void loadImages() {

        BufferedImage spriteSheet = ImageManager.getInstance().loadImage("/me/volition/assets/image/entities/player_spritesheet.png");

        BufferedImage[] idleFrames = new BufferedImage[2];
        idleFrames[0] = spriteSheet.getSubimage(0, 0, 64, 64);
        idleFrames[1] = spriteSheet.getSubimage(64, 0, 64, 64);
        idle = new Animator(idleFrames);

        setAnimator(idle);
    }

    @Override
    public Animator getBattleAnimator() {
        return idle;
    }
}
