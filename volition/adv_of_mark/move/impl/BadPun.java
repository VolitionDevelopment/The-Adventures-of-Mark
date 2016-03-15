package volition.adv_of_mark.move.impl;

import volition.adv_of_mark.mapObject.entity.Entity;
import volition.adv_of_mark.move.Move;

import java.util.Random;

/**
 * Created by Demerzel on 2/3/16.
 */
public class BadPun extends Move {
    private String[] badPuns = {
            "I'll calcu-later!",
            "A dyslexic man walks into a bra",
            "What's a fish with no eyes? A fsh!",
            "I'll send you to the pun-itentiary!",
            "I thought I was indecisive, now I'm not so sure.",
    };

    private String[] responses = {
            "Ahh! It's so bad!",
            "Hehe...",
            "LOL! XD"
    };

    public BadPun(){
        super("Bad Pun", 5, 10);
    }

    @Override
    public void onCast(Entity caster, Entity target) {
        Random random = new Random();

        caster.setSpeech(badPuns[random.nextInt(badPuns.length)]);

        if(Math.random() < 0.85){
            target.takeDamage((int)((getDamage() + caster.getWepDamage()) * 1.1));
            target.setSpeech(responses[random.nextInt(responses.length)]);
        }else{
            target.setSpeech("Go back to joke school.");
        }
    }
}
