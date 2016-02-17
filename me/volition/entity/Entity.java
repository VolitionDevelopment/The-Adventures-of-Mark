package me.volition.entity;

import me.volition.location.Location;
import me.volition.location.placeableObject.ObjectEvent;
import me.volition.move.Move;
import me.volition.util.Animator;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Demerzel on 2/3/16.
 */
public abstract class Entity {
    private String name;
    private String bio;
    private String speech;
    private int baseTolerance, tolerance;
    private int baseBrainpower, brainpower;
    private int baseSpeed;
    private int armor, wepDamage;
    private Location location;
    private boolean goingRight, goingLeft, goingUp, goingDown;
    private int dir; //0 up, 1 down, 2 right, 3 left
    private double x, y;
    private Animator animator;
    private ObjectEvent onInteract;

    private ArrayList<Move> moves;

    public Entity(String name, String bio, ObjectEvent onInteract, int baseTolerance, int baseBrainpower, int baseSpeed, Location location, double x, double y) {

        loadImages();

        this.name = name;
        this.bio = bio;
        this.onInteract = onInteract;
        this.baseTolerance = baseTolerance;
        this.baseBrainpower = baseBrainpower;
        this.baseSpeed = baseSpeed;
        this.location = location;

        this.moves = new ArrayList<>();

        this.x = x;
        this.y = y;

        levelUp();
    }

    //for enemies that exist only inside battles
    public Entity(String name, String bio, int baseTolerance, int baseBrainpower, int baseSpeed) {

        loadImages();

        this.name = name;
        this.bio = bio;
        this.baseTolerance = baseTolerance;
        this.baseBrainpower = baseBrainpower;
        this.baseSpeed = baseSpeed;

        this.moves = new ArrayList<>();

        levelUp();
    }

    public void useMove(int index, Entity target){
        if (index < moves.size())
            moves.get(index).onCast(this, target);
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public ObjectEvent getOnInteract(){
        return onInteract;
    }

    public int getBaseTolerance() {
        return baseTolerance;
    }

    public int getTolerance() {
        return tolerance;
    }

    public int getBaseBrainpower() {
        return baseBrainpower;
    }

    public int getBrainpower() {
        return brainpower;
    }

    public int getArmor(){
        return armor;
    }

    public int getWepDamage(){
        return wepDamage;
    }

    public String getSpeech(){
        return speech;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }

    public boolean isMoving(){
        return goingUp || goingDown || goingLeft || goingRight;
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

    public boolean isFacingUp(){
        return dir == 0;
    }

    public boolean isFacingDown(){
        return dir == 1;
    }

    public boolean isFacingRight(){
        return dir == 2;
    }

    public boolean isFacingLeft(){
        return dir == 3;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public int getWidth(){
        return animator.getCurrentImage().getWidth();
    }

    public int getHeight(){
        return animator.getCurrentImage().getHeight();
    }

    public Animator getAnimator(){
        return animator;
    }

    public Location getLocation() {
        return location;
    }

    public ArrayList<Move> getMoves() {
        return moves;
    }

    public boolean isDead(){
        return tolerance <= 0;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setBaseTolerance(int baseTolerance) {
        this.baseTolerance = baseTolerance;
    }

    public void modBaseTolerance(int health){
        this.baseTolerance += health;
    }

    public void setTolerance(int tolerance) {
        this.tolerance = tolerance;
    }

    public void modTolerance(int health){
        this.tolerance += health;
        if (tolerance > baseTolerance)
            tolerance = baseTolerance;
    }

    public void setBaseBrainpower(int baseBrainpower) {
        this.baseBrainpower = baseBrainpower;
    }

    public void modBaseBrainpower(int mana){
        this.baseBrainpower += mana;
    }

    public void setBrainpower(int brainpower) {
        this.brainpower = brainpower;
    }

    public void modBrainpower(int mana){
        this.brainpower += mana;
        if (brainpower > baseBrainpower)
            brainpower = baseBrainpower;
    }

    public void setArmor(int armor){
        this.armor = armor;
    }

    public void setWepDamage(int wepDamage){
        this.wepDamage = wepDamage;
    }

    public void setSpeech(String speech){
        this.speech = speech;
    }

    public void setBaseSpeed(int baseSpeed) {
        this.baseSpeed = baseSpeed;
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

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public void setAnimator(Animator animator){
        if (this.animator != animator) {
            if (this.animator != null)
                this.animator.reset();
            this.animator = animator;
        }
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void addMove(Move move){
        this.moves.add(move);
    }

    public void stopMoving(){
        goingDown = false;
        goingUp = false;
        goingLeft = false;
        goingRight = false;
    }

    public void takeDamage(int damage){
        damage -= armor;
        if (damage < 0)
            damage = 0;

        this.tolerance -= damage;
    }

    public void levelUp(){
        setTolerance(getBaseTolerance());
        setBrainpower(getBaseBrainpower());
    }

    public void update(double delta){
        animator.update(delta);

        if (goingUp)
            dir = 0;
        else if (goingDown)
            dir = 1;
        else if (goingRight)
            dir = 2;
        else if (goingLeft)
            dir = 3;
    }

    public abstract void loadImages();

    public abstract Animator getBattleAnimator();

    public void render(Graphics g){
        g.drawImage(animator.getCurrentImage(), (int) x, (int) y, null);
    }

    public void render(Graphics g, int x, int y){
        g.drawImage(animator.getCurrentImage(), x, y, null);
    }

    public void render(Graphics g, int x, int y, int width, int height){
        g.drawImage(animator.getCurrentImage(), x, y, width, height, null);
    }
}
