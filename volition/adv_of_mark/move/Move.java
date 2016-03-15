package volition.adv_of_mark.move;

import volition.adv_of_mark.mapObject.entity.Entity;

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

    public int getDamage() {
        return damage;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public abstract void onCast(Entity caster, Entity target);
}
