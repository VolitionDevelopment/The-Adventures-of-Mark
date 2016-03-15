package volition.adv_of_mark.mapObject.entity.shopkeepers;

import volition.adv_of_mark.location.tile.Tile;
import volition.adv_of_mark.mapObject.entity.Entity;
import volition.adv_of_mark.mapObject.ObjectEvent;
import volition.adv_of_mark.util.Animator;
import volition.adv_of_mark.util.ImageManager;

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

        BufferedImage spriteSheet = ImageManager.getInstance().loadImage("/volition/adv_of_mark/assets/image/entities/player_spritesheet.png");

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
