package me.volition.move;

import me.volition.entity.Entity;
import me.volition.entity.Player;

/**
 * Created by Demerzel on 2/3/16.
 */
public abstract class Move {
    private String name;
    private int damage;
    private int manaCost;

    public Move(String name, int damage, int manaCost) {
        this.name = name;
        this.damage = damage;
        this.manaCost = manaCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public abstract void onCast(Entity caster, Entity target);
}
