package me.volition.entity;

import me.volition.Window;
import me.volition.item.Item;
import me.volition.item.ItemSlot;
import me.volition.location.Location;
import me.volition.location.tile.Tile;
import me.volition.util.Animator;
import me.volition.util.ImageManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by mccloskeybr on 2/3/16.
 */
public class Player extends Entity{
    private int level;
    private int exp;
    private int money;
    private ArrayList<Item> inventory;
    private HashMap<ItemSlot, Item> equippedItems;
    private Animator idle, walkRight, walkLeft, walkUp, walkDown;

    public Player(Location location) {
        super("Mark", "Mark is a man fresh out of college. He won 'Frattiest Bro' at his frat house, Theta Xi.", 100, 30, 5, location, Window.WINDOW_WIDTH / 2 - Tile.TILE_SIZE / 2, Window.WINDOW_HEIGHT / 2 - Tile.TILE_SIZE / 2);
    }

    @Override
    public void loadImages(){

        BufferedImage spriteSheet = new ImageManager().loadImage("/me/volition/assets/image/entities/player_spritesheet.png");

        BufferedImage[] rightFrames = new BufferedImage[4];
        rightFrames[0] = spriteSheet.getSubimage(128, 64, 64, 64);
        rightFrames[1] = spriteSheet.getSubimage(192, 64, 64, 64);
        rightFrames[2] = spriteSheet.getSubimage(0, 128, 64, 64);
        rightFrames[3] = spriteSheet.getSubimage(64, 128, 64, 64);
        walkRight = new Animator(rightFrames);

        BufferedImage[] leftFrames = new BufferedImage[4];
        leftFrames[0] = spriteSheet.getSubimage(128, 0, 64, 64);
        leftFrames[1] = spriteSheet.getSubimage(192, 0, 64, 64);
        leftFrames[2] = spriteSheet.getSubimage(0, 64, 64, 64);
        leftFrames[3] = spriteSheet.getSubimage(64, 64, 64, 64);
        walkLeft = new Animator(leftFrames);

        BufferedImage[] upFrames = new BufferedImage[2];
        upFrames[0] = spriteSheet.getSubimage(0, 192, 64, 64);
        upFrames[1] = spriteSheet.getSubimage(64, 192, 64, 64);
        walkUp = new Animator(upFrames);

        BufferedImage[] downFrames = new BufferedImage[2];
        downFrames[0] = spriteSheet.getSubimage(128, 128, 64, 64);
        downFrames[1] = spriteSheet.getSubimage(192, 128, 64, 64);
        walkDown = new Animator(downFrames);

        BufferedImage[] idleFrames = new BufferedImage[2];
        idleFrames[0] = spriteSheet.getSubimage(0, 0, 64, 64);
        idleFrames[1] = spriteSheet.getSubimage(64, 0, 64, 64);
        idle = new Animator(idleFrames);

        setAnimator(idle);
    }

    @Override
    public void update(double delta){
        super.update(delta);

        //free camera only updates animations, not position
        if (getLocation().hasFreeCamera()) {
            if (!isGoingRight() && !isGoingLeft() && !isGoingUp() && !isGoingDown())
                setAnimator(idle);
            else {
                if (isGoingDown())
                    setAnimator(walkDown);
                else if (isGoingUp())
                    setAnimator(walkUp);
                //up/down animations have priority over left/right
                if (isGoingLeft() && !isGoingUp() && !isGoingDown())
                    setAnimator(walkLeft);
                else if (isGoingRight() && !isGoingUp() && !isGoingDown())
                    setAnimator(walkRight);
            }
        } else {
            if (!isGoingRight() && !isGoingLeft() && !isGoingUp() && !isGoingDown())
                setAnimator(idle);
            else {
                if (isGoingDown()) {
                    setY(getY() + (delta * getBaseSpeed()));
                    setAnimator(walkDown);
                } else if (isGoingUp()) {
                    setY(getY() - (delta * getBaseSpeed()));
                    setAnimator(walkUp);
                }
                //up/down animations have priority over left/right
                if (isGoingLeft()) {
                    setX(getX() - (delta * getBaseSpeed()));
                    if (!isGoingUp() && !isGoingDown())
                        setAnimator(walkLeft);
                } else if (isGoingRight()) {
                    setX(getX() + (delta * getBaseSpeed()));
                    if (!isGoingUp() && !isGoingDown())
                        setAnimator(walkRight);
                }
            }
        }
    }

    public void setLocation(Location location){
        if (getLocation() == null){
            super.setLocation(location);
        } else {
            setX(Window.WINDOW_WIDTH - getX());
            setY(Window.WINDOW_HEIGHT - getY());

            super.setLocation(location);
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void modExp(int exp){
        this.exp += exp;

        while(nextLevel() <= 0){
            level++;
            heal();
            Random r = new Random();
            modBaseTolerance(Math.max(1, r.nextInt(15)));
            modBaseBrainpower(Math.max(1, r.nextInt(10)));
        }
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void modMoney(int money){
        this.money += money;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public void addItem(Item item){
        this.inventory.add(item);
    }

    public void removeItem(Item item){
        if(inventory.contains(item)){
            inventory.remove(item);
        }
    }

    public HashMap<ItemSlot, Item> getEquippedItems() {
        return equippedItems;
    }

    public void setEquippedItems(HashMap<ItemSlot, Item> equippedItems) {
        this.equippedItems = equippedItems;
    }

    public void equip(Item item){
        if(equippedItems.get(item.getSlot()) != null){
            inventory.add(equippedItems.get(item.getSlot()));
            equippedItems.replace(item.getSlot(), item);
        }

        equippedItems.put(item.getSlot(), item);
        inventory.remove(item);
    }

    public int nextLevel(){
        double LEVEL_CONSTANT = 20;
        return (int) (LEVEL_CONSTANT * (level) * (level + 5) - getExp());
    }

    @Override
    public Rectangle getBounds(){
        if (getLocation().hasFreeCamera())
            return new Rectangle(Window.WINDOW_WIDTH / 2 - getWidth() / 2, Window.WINDOW_HEIGHT / 2 - getHeight() / 2, 64, 64);
        else
            return super.getBounds();
    }

    @Override
    public void render(Graphics g){
        if (getLocation().hasFreeCamera())
            super.render(g, Window.WINDOW_WIDTH / 2 - getWidth() / 2, Window.WINDOW_HEIGHT / 2 - getHeight() / 2);
        else
            super.render(g);
    }

}
