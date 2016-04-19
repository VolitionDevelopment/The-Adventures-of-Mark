package volition.adv_of_mark.mapObject.entity;

import volition.adv_of_mark.location.Location;
import volition.adv_of_mark.location.tile.Tile;
import volition.adv_of_mark.mapObject.MapObject;
import volition.adv_of_mark.mapObject.ObjectEvent;
import volition.adv_of_mark.move.Move;
import volition.adv_of_mark.util.Animator;
import volition.adv_of_mark.util.GameManager;
import volition.adv_of_mark.util.LocationManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Demerzel on 2/3/16.
 */
public abstract class Entity extends MapObject {

    private String speech;
    private int baseTolerance, tolerance;
    private int baseBrainpower, brainpower;
    private int baseSpeed;
    private int armor, wepDamage;
    private boolean goingRight, goingLeft, goingUp, goingDown;
    private int dir; //0 up, 1 down, 2 right, 3 left
    private Animator animator;

    private ArrayList<Move> moves;

    public Entity(String name, String bio, ObjectEvent onInteract, int baseTolerance, int baseBrainpower, int baseSpeed, double x, double y, int length, int width, int height) {
        super(x, y, length, width, height, onInteract, name, bio);

        loadImages();

        this.baseTolerance = baseTolerance;
        this.baseBrainpower = baseBrainpower;
        this.baseSpeed = baseSpeed;

        this.moves = new ArrayList<>();

        levelUp();
    }

    //for enemies that exist only inside battles
    public Entity(String name, String bio, int baseTolerance, int baseBrainpower, int baseSpeed) {
        super(-1, -1, -1, -1, -1, ObjectEvent.NONE, name, bio);

        loadImages();


        this.baseTolerance = baseTolerance;
        this.baseBrainpower = baseBrainpower;
        this.baseSpeed = baseSpeed;

        this.moves = new ArrayList<>();

        levelUp();
    }

    public void useMove(int index, Entity target) {
        if (index < moves.size())
            moves.get(index).onCast(this, target);
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

    public int getArmor() {
        return armor;
    }

    public int getWepDamage() {
        return wepDamage;
    }

    public String getSpeech() {
        return speech;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }

    public boolean isMoving() {
        return goingUp || goingDown || goingLeft || goingRight;
    }

    public boolean isGoingRight() {
        return goingRight;
    }

    public boolean isGoingLeft() {
        return goingLeft;
    }

    public boolean isGoingUp() {
        return goingUp;
    }

    public boolean isGoingDown() {
        return goingDown;
    }

    public boolean isFacingUp() {
        return dir == 0;
    }

    public boolean isFacingDown() {
        return dir == 1;
    }

    public boolean isFacingRight() {
        return dir == 2;
    }

    public boolean isFacingLeft() {
        return dir == 3;
    }

    public BufferedImage getImage() {
        return animator.getCurrentImage();
    }

    public Animator getAnimator() {
        return animator;
    }

    public ArrayList<Move> getMoves() {
        return moves;
    }

    public boolean isDead() {
        return tolerance <= 0;
    }


    public void setBaseTolerance(int baseTolerance) {
        this.baseTolerance = baseTolerance;
    }

    public void modBaseTolerance(int health) {
        this.baseTolerance += health;
    }

    public void setTolerance(int tolerance) {
        this.tolerance = tolerance;
    }

    public void modTolerance(int health) {
        this.tolerance += health;
        if (tolerance > baseTolerance)
            tolerance = baseTolerance;
    }

    public void setBaseBrainpower(int baseBrainpower) {
        this.baseBrainpower = baseBrainpower;
    }

    public void modBaseBrainpower(int mana) {
        this.baseBrainpower += mana;
    }

    public void setBrainpower(int brainpower) {
        this.brainpower = brainpower;
    }

    public void modBrainpower(int mana) {
        this.brainpower += mana;
        if (brainpower > baseBrainpower)
            brainpower = baseBrainpower;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setWepDamage(int wepDamage) {
        this.wepDamage = wepDamage;
    }

    public void setSpeech(String speech) {
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

    public void setAnimator(Animator animator) {
        if (this.animator != animator) {
            if (this.animator != null)
                this.animator.reset();
            this.animator = animator;
        }
    }

    public void addMove(Move move) {
        this.moves.add(move);
    }

    public void stopMoving() {
        goingDown = false;
        goingUp = false;
        goingLeft = false;
        goingRight = false;
    }

    public void takeDamage(int damage) {
        damage -= armor;
        if (damage < 0)
            damage = 0;

        this.tolerance -= damage;
    }

    public void levelUp() {
        setTolerance(getBaseTolerance());
        setBrainpower(getBaseBrainpower());
    }

    public void update(double delta) {
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

    public void render(Graphics2D g) {

        Location location = LocationManager.getCurrentLocation();

        g.drawImage(
                animator.getCurrentImage(),
                (int) location.getBg_horizOffset() + (location.getTilemap().length * Tile.TILE_SIZE / 2)
                        + (int) ((Tile.TILE_SIZE / 2) * (getX() / Tile.TILE_SIZE) - (Tile.TILE_SIZE / 2) * (getY() / Tile.TILE_SIZE))
                        - getWidth() / 2,
                (int) location.getBg_vertOffset()
                        + (int) ((Tile.TILE_SIZE / 4) * (getX() / Tile.TILE_SIZE) + (Tile.TILE_SIZE / 4) * (getY() / Tile.TILE_SIZE))
                        - getHeight() / 2 * ((getHeight() / Tile.TILE_SIZE) - 1)
                        - Tile.TILE_SIZE / 2,
                null
        );

    }

    public void render(Graphics2D g, int x, int y) {
        g.drawImage(animator.getCurrentImage(), x, y, null);
    }

    public void render(Graphics2D g, int x, int y, int width, int height) {
        g.drawImage(animator.getCurrentImage(), x, y, width, height, null);
    }
}