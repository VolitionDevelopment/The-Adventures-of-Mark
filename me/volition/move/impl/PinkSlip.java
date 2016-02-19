package me.volition.move.impl;

import me.volition.mapObject.entity.Entity;
import me.volition.move.Move;

/**
 * Created by Demerzel on 2/3/16.
 */
public class PinkSlip extends Move {
    public PinkSlip(){
        super("Pink Slip", 100, 10);
    }

    @Override
    public void onCast(Entity caster, Entity target) {

    }
}
