package me.volition.entity;

import me.volition.location.Location;

import java.awt.*;
import java.awt.image.BufferedImage;

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
    private boolean goingRight, goingLeft, goingUp, goingDown;
    private double x, y;
    private BufferedImage image;

    public Entity(BufferedImage image, String name, String bio, int baseHealth, int baseMana, int baseSpeed, Location location, double x, double y) {
        this.image = image;
        this.name = name;
        this.bio = bio;
        this.baseHealth = baseHealth;
        this.baseMana = baseMana;
        this.baseSpeed = baseSpeed;
        this.location = location;

        this.x = x;
        this.y = y;

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

    public boolean isGoingRight(){
        return goingRight;
    }

    public boolean isGoingLeft(){
        return goingLeft;
    }

    public boolean isGoingUp(){
        return goingUp;
    }

    public boolean isGoingDown(){
        return isGoingDown();
    }

    public void setGoingRight(boolean goingRight) {
        this.goingRight = goingRight;
    }

    public void setGoingLeft(boolean goingLeft) {
        this.goingLeft = goingLeft;
    }

    public void setGoingUp(boolean goingUp) {
        this.goingUp = goingUp;
    }

    public void setGoingDown(boolean goingDown) {
        this.goingDown = goingDown;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
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

    abstract void update();

    public void render(Graphics g){
        g.drawImage(image, (int) x, (int) y, null);
    }
}
