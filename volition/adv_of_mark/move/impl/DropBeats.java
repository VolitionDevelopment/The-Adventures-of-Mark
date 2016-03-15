package volition.adv_of_mark.move.impl;

import volition.adv_of_mark.mapObject.entity.Entity;
import volition.adv_of_mark.move.Move;

/**
 * Created by Demerzel on 2/11/16.
 */
public class DropBeats extends Move {
    public DropBeats(){
        super("Drop Beats", 6, 5);
    }

    @Override
    public void onCast(Entity caster, Entity target) {

    }
}
