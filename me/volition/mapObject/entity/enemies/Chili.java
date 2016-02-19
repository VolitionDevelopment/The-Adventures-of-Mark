package me.volition.mapObject.entity.enemies;

import me.volition.mapObject.entity.Entity;
import me.volition.move.impl.RedHotChiliShits;
import me.volition.util.Animator;
import me.volition.util.ImageManager;

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
        BufferedImage spritesheet = ImageManager.getInstance().loadImage("/me/volition/assets/image/entities/chili_spritesheet.png");

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
