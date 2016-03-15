package volition.adv_of_mark.mapObject.entity.enemies;

import volition.adv_of_mark.mapObject.entity.Entity;
import volition.adv_of_mark.move.impl.RedHotChiliShits;
import volition.adv_of_mark.util.Animator;
import volition.adv_of_mark.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/7/2016.
 */
public class Chili extends Entity {

    private Animator battle;

    public Chili() {
        super("Chili", "Haunts your dreams and your bowels.", 5, 10, 15);

        addMove(new RedHotChiliShits());
    }

    @Override
    public void loadImages() {
        BufferedImage spritesheet = ImageManager.getInstance().loadImage("/volition/adv_of_mark/assets/image/entities/chili_spritesheet.png");

        BufferedImage[] battleFrames = new BufferedImage[2];
        battleFrames[0] = spritesheet.getSubimage(0, 0, 64, 64);
        battleFrames[1] = spritesheet.getSubimage(64, 0, 64, 64);
        battle = new Animator(battleFrames);

        setAnimator(battle);
    }

    @Override
    public Animator getBattleAnimator() {
        return battle;
    }
}
