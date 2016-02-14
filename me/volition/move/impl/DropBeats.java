package me.volition.move.impl;

import me.volition.entity.Entity;
import me.volition.move.Move;

/**
 * Created by Demerzel on 2/11/16.
 */
public class DropBeats extends Move {
    public DropBeats(){
        super("Drop Beats", 6, 5);
    }

    @Override
    public void onCast(Entity caster, Entity target) {

    }
}
