package me.volition.move.impl;

import me.volition.entity.Entity;
import me.volition.move.LingerEffect;
import me.volition.move.Move;

import java.util.Random;

/**
 * Created by Demerzel on 2/4/16.
 */
public class Rekt extends Move implements LingerEffect{
    private String[] insults = {
            "Your mom!"
    };

    public Rekt() {
        super("Get Rekt", 10, 20);
    }

    @Override
    public void fire(Entity entity) {
        int damage = Math.min(entity.getBaseTolerance() - entity.getTolerance(), getDamage());
        damage = Math.max(0, damage);

        entity.modTolerance(-damage);
    }

    @Override
    public void onCast(Entity caster, Entity target) {
        caster.setSpeech(insults[new Random().nextInt(insults.length)]);
        target.setSpeech("I'm so sad.");
    }
}
