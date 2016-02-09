package me.volition.entity.enemies;

import me.volition.entity.Entity;
import me.volition.util.Animator;
import me.volition.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/7/2016.
 */
public class Stoner extends Entity {

    private Animator battle;

    public Stoner() {
        super("Stoner", "Just wants to have a mellow time.", 15, 5, 5);
    }

    @Override
    public void loadImages() {
        BufferedImage spritesheet = new ImageManager().loadImage("/me/volition/assets/image/entities/stoner_spritesheet.png");

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
