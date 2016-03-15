package volition.adv_of_mark.move.impl;

import volition.adv_of_mark.mapObject.entity.Entity;
import volition.adv_of_mark.move.Move;

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
