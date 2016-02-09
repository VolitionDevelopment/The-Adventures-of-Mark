package me.volition.entity.enemies;

import me.volition.entity.Entity;
import me.volition.util.Animator;
import me.volition.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/7/2016.
 */
public class Jalapeno extends Entity {

    private Animator battle;

    public Jalapeno() {
        super("Jalapeno", "Haunts your dreams and your bowels.", 5, 10, 15);
    }

    @Override
    public void loadImages() {
        BufferedImage spritesheet = new ImageManager().loadImage("/me/volition/assets/image/entities/jalapeno_spritesheet.png");

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
