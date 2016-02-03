package me.volition.entity;

import me.volition.location.Location;

/**
 * Created by Demerzel on 2/3/16.
 */
public abstract class Entity {
    private String name;
    private String bio;
    private int baseHealth, health;
    private int baseMana, mana;
    private int baseSpeed;
    private Location location;


    public Entity(String name, String bio, int baseHealth, int baseMana, int baseSpeed, Location location) {
        this.name = name;
        this.bio = bio;
        this.baseHealth = baseHealth;
        this.baseMana = baseMana;
        this.baseSpeed = baseSpeed;
        this.location = location;

        heal();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public void setBaseHealth(int baseHealth) {
        this.baseHealth = baseHealth;
    }

    public void modBaseHealth(int health){
        this.baseHealth += health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void modHealth(int health){
        this.health += health;
    }

    public int getBaseMana() {
        return baseMana;
    }

    public void setBaseMana(int baseMana) {
        this.baseMana = baseMana;
    }

    public void modBaseMana(int mana){
        this.baseMana += mana;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void modMana(int mana){
        this.mana += mana;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }

    public void setBaseSpeed(int baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isDead(){
        return health <= 0;
    }

    public void heal(){
        setHealth(getBaseHealth());
        setMana(getBaseMana());
    }
}
