package me.volition.move.impl;

import me.volition.entity.Entity;
import me.volition.move.Move;

/**
 * Created by Demerzel on 2/3/16.
 */
public class BadPun extends Move {
    private String[] badPuns = {
            "I'll calcu-later!",
            "A dyslexic man walks into a bra",
            "What do you call a fish with no eyes? A fsh!",
            "Your mom!",
            "I'll send you to the pun-itentiary!",
            "I once saw one man beat another man down in a rice field with a plastic trophy. It was a knick-nack paddy-whack!",
            "I used to think I was indecisive, but now I'm not so sure.",
            "What do you call a fat vampire? Count Snackula!",
            "What do you call a sleepwalking nun? A roamin Catholic"
    };

    public BadPun(){
        super("Bad Pun", 0, 10);
    }

    @Override
    public void onCast(Entity entity) {
        double d = Math.random();

        if(d >= 0.5){
            
            entity.setTolerance((int) (entity.getBaseTolerance() * 0.2));
        }else{

        }
    }
}
