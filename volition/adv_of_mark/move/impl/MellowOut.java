package volition.adv_of_mark.move.impl;

import volition.adv_of_mark.mapObject.entity.Entity;
import volition.adv_of_mark.move.Move;

import java.util.Random;

/**
 * Created by mccloskeybr on 2/9/2016.
 */
public class MellowOut extends Move {

    private String[] attacks = {
            "Chill, bro!",
            "Weed is so dank man!",
            "Yoooo... Dude....."
    };

    private String[] responses = {
            "Maybe I'll try it out...",
            "Maybe I should relax a bit",
            "Just one."
    };

    public MellowOut() {
        super("Mellow Out", 0, -5);
    }

    @Override
    public void onCast(Entity caster, Entity target) {
        Random random = new Random();

        caster.setSpeech(attacks[random.nextInt(responses.length)]);

        if (Math.random() < 0.25) {
            target.takeDamage(random.nextInt(10) + 5);
            target.setSpeech(responses[random.nextInt(responses.length)]);
        } else {
            target.setSpeech("It's not for me.");
        }
    }
}
