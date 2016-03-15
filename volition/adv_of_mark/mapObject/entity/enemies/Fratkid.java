package volition.adv_of_mark.mapObject.entity.enemies;

import volition.adv_of_mark.mapObject.entity.Entity;
import volition.adv_of_mark.move.impl.Rekt;
import volition.adv_of_mark.util.Animator;
import volition.adv_of_mark.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/7/2016.
 */
public class Fratkid extends Entity {

    private Animator battle;

    public Fratkid() {
        super("Frat boy", "Tries too hard to be cool.", 20, 1, 10);
        addMove(new Rekt());
    }


    @Override
    public void loadImages() {
        BufferedImage spritesheet = ImageManager.getInstance().loadImage("/volition/adv_of_mark/assets/image/entities/fratkid_spritesheet.png");

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
