package me.volition.entity;

import me.volition.location.Location;
import me.volition.util.Animator;
import me.volition.move.Move;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Demerzel on 2/3/16.
 */
public abstract class Entity {
    private String name;
    private String bio;
    private int baseTolerance, tolerance;
    private int baseBrainpower, brainpower;
    private int baseSpeed;
    private Location location;
    private boolean goingRight, goingLeft, goingUp, goingDown;
    private double x, y;
    private BufferedImage image;
    private Animator animator;

    private ArrayList<Move> moves;

    public Entity(BufferedImage image, String name, String bio, int baseTolerance, int baseBrainpower, int baseSpeed, Location location, double x, double y) {

        loadImages();

        this.image = image;
        this.name = name;
        this.bio = bio;
        this.baseTolerance = baseTolerance;
        this.baseBrainpower = baseBrainpower;
        this.baseSpeed = baseSpeed;
        this.location = location;

        this.moves = new ArrayList<>();

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

    public int getBaseTolerance() {
        return baseTolerance;
    }

    public void setBaseTolerance(int baseTolerance) {
        this.baseTolerance = baseTolerance;
    }

    public void modBaseTolerance(int health){
        this.baseTolerance += health;
    }

    public int getTolerance() {
        return tolerance;
    }

    public void setTolerance(int tolerance) {
        this.tolerance = tolerance;
    }

    public void modTolerance(int health){
        this.tolerance += health;
    }

    public int getBaseBrainpower() {
        return baseBrainpower;
    }

    public void setBaseBrainpower(int baseBrainpower) {
        this.baseBrainpower = baseBrainpower;
    }

    public void modBaseBrainpower(int mana){
        this.baseBrainpower += mana;
    }

    public int getBrainpower() {
        return brainpower;
    }

    public void setBrainpower(int brainpower) {
        this.brainpower = brainpower;
    }

    public void modBrainpower(int mana){
        this.brainpower += mana;
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
        return goingDown;
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
        return tolerance <= 0;
    }

    public void heal(){
        setTolerance(getBaseTolerance());
        setBrainpower(getBaseBrainpower());
    }

    public void setAnimator(Animator animator){
        if (this.animator != null)
            this.animator.reset();
        this.animator = animator;
    }

    abstract void update(double delta);

    abstract void loadImages();

    public void render(Graphics g){
        g.drawImage(animator.getCurrentImage(), (int) x, (int) y, null);
    }

    public ArrayList<Move> getMoves() {
        return moves;
    }

    public void addMove(Move move){
        this.moves.add(move);
    }
}
