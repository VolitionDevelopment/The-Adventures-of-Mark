package me.volition.move.impl;

import me.volition.entity.Entity;
import me.volition.move.Move;

/**
 * Created by Demerzel on 2/3/16.
 */
public class ShutUp extends Move {
    public ShutUp(){
        super("Shut Up!", 40, 5);
    }

    @Override
    public void onCast(Entity caster, Entity target) {

    }
}
