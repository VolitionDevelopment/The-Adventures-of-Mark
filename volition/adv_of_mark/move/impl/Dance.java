package volition.adv_of_mark.move.impl;

import volition.adv_of_mark.mapObject.entity.Entity;
import volition.adv_of_mark.move.Move;

import java.util.Random;

/**
 * Created by Demerzel on 2/11/16.
 */
public class Dance extends Move{
    private String[] useText = {
            "I'm groovin to the beat",
            "Time to break a leg!"
    };

    private String[] responses = {
            "Tell my wife I love her!",
            "MY EYES!",
    };

    public Dance(){
        super("Dance", 10, 10);
    }

    @Override
    public void onCast(Entity caster, Entity target) {
        Random random = new Random();

        caster.setSpeech(useText[random.nextInt(useText.length)]);

        if(Math.random() < 0.85){
            target.takeDamage((int)((getDamage() + caster.getWepDamage()) * 1.1));
            target.setSpeech(responses[random.nextInt(responses.length)]);
        }else{
            target.setSpeech("You need to never dance again...");
        }
    }
}
