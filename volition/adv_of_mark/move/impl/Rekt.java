package volition.adv_of_mark.move.impl;

import volition.adv_of_mark.mapObject.entity.Entity;
import volition.adv_of_mark.move.LingerEffect;
import volition.adv_of_mark.move.Move;

import java.util.Random;

/**
 * Created by Demerzel on 2/4/16.
 */
public class Rekt extends Move implements LingerEffect{
    private String[] insults = {
            "Your mom!",
            "Do you even lift brah?",
            "Leg day? What's that?",
    };

    private String[] responses = {
            "I'm so sad.",
            "Just leave me alone!"
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
        Random random = new Random();

        caster.setSpeech(insults[random.nextInt(insults.length)]);

        if (Math.random() < 0.5) {
            target.takeDamage(random.nextInt(7) + 4);
            target.setSpeech(responses[random.nextInt(responses.length)]);
        } else {
            target.setSpeech("Whatever dude.");
        }
    }
}
