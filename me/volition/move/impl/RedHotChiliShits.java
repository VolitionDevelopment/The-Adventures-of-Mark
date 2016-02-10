package me.volition.move.impl;

import me.volition.entity.Entity;
import me.volition.move.Move;

/**
 * Created by mccloskeybr on 2/9/2016.
 */
public class RedHotChiliShits extends Move {
    public RedHotChiliShits() {
        super("Infamous Red Hot Chili Shits!", 5, 5);
    }

    @Override
    public void onCast(Entity caster, Entity target) {
        caster.setSpeech("I'm gonna wreck your booty!");
        target.setSpeech("My ass is hurting already!");
    }
}
