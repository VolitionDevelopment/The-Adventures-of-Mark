package me.volition.move.impl;

import me.volition.entity.Entity;
import me.volition.move.Move;

import java.util.Random;

/**
 * Created by Demerzel on 2/3/16.
 */
public class BadPun extends Move {
    private String[] badPuns = {
            "I'll calcu-later!",
            "A dyslexic man walks into a bra",
            "What do you call a fish with no eyes? A fsh!",
            "I'll send you to the pun-itentiary!",
            "I used to think I was indecisive, but now I'm not so sure.",
            "What do you call a fat vampire? Count Snackula!",
            "What do you call a sleepwalking nun? A roamin Catholic"
    };

    public BadPun(){
        super("Bad Pun", 300, 10);
    }

    @Override
    public void onCast(Entity caster, Entity target) {
        double d = Math.random();

        caster.setSpeech(badPuns[new Random().nextInt(badPuns.length)]);

        if(d >= 0.5){
            target.takeDamage((int)(caster.getWepDamage() * 1.1));
            target.setSpeech("Ahh! It's so bad!");
        }else{
            target.setSpeech("I've heard that one before...");
        }
    }
}
