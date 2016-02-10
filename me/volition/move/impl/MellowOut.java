package me.volition.move.impl;

import me.volition.entity.Entity;
import me.volition.move.Move;

/**
 * Created by mccloskeybr on 2/9/2016.
 */
public class MellowOut extends Move {
    public MellowOut() {
        super("Mellow Out", 0, -5);
    }

    @Override
    public void onCast(Entity caster, Entity target) {
        caster.setSpeech("Chill, bro!");
        target.setSpeech("Maybe I'll try it out...");
    }
}
