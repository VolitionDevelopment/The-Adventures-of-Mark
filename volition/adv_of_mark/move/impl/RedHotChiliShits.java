package volition.adv_of_mark.move.impl;

import volition.adv_of_mark.mapObject.entity.Entity;
import volition.adv_of_mark.move.Move;

import java.util.Random;

/**
 * Created by mccloskeybr on 2/9/2016.
 */
public class RedHotChiliShits extends Move {

    private String[] responses = {
            "My butt hurts already!",
            "My mouth is on fire!",
            "Where's my milk?!"
    };

    private String[] attacks = {
            "I'm gonna wreck your booty!",
            "Prepare your anus!",
            "Here comes the pain train!",
            "You'll feel the pain in approx. 4 hours!"
    };

    public RedHotChiliShits() {
        super("Infamous Red Hot Chili Shits!", 5, 5);
    }

    @Override
    public void onCast(Entity caster, Entity target) {
        Random random = new Random();

        caster.setSpeech(attacks[random.nextInt(attacks.length)]);

        if (Math.random() < 0.8) {
            target.takeDamage(random.nextInt(5) + 5);
            target.setSpeech(responses[random.nextInt(responses.length)]);
        } else {
            target.setSpeech("Not even that spicy...");
        }
    }
}
