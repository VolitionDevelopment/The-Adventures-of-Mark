package me.volition.entity;

import me.volition.location.Location;

/**
 * Created by mccloskeybr on 2/3/16.
 */
public class Player extends Entity{

    public Player(Location location) {
        super("Mark", "", 100, 30, 5, location);
    }
}
