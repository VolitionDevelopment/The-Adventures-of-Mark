package me.volition.move.impl;

import me.volition.entity.Entity;
import me.volition.move.Move;

import java.util.Random;

/**
 * Created by mccloskeybr on 2/9/2016.
 */
public class MellowOut extends Move {

    private String[] responses = {
            "Maybe I'll try it out...",
            "Maybe I should relax a bit",
            "Just one."
    };

    public MellowOut() {
        super("Mellow Out", 0, -5);
    }

    @Override
    public void onCast(Entity caster, Entity target) {
        Random random = new Random();

        caster.setSpeech("Chill, bro!");

        if (Math.random() < 0.5) {
            target.setSpeech(responses[random.nextInt(responses.length)]);
        } else {
            target.setSpeech("It's not for me.");
        }
    }
}
