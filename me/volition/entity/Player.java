package me.volition.entity;

import me.volition.item.Item;
import me.volition.item.ItemSlot;
import me.volition.location.Location;
import me.volition.util.Animator;
import me.volition.util.ImageManager;

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
        super("Mark", "", 100, 30, 5, location, 0, 0);
    }

    @Override
    public void loadImages(){

        BufferedImage spriteSheet = new ImageManager().loadImage("/me/volition/assets/image/entity/player_spritesheet.png");

        BufferedImage[] rightFrames = new BufferedImage[4];
        walkRight = new Animator(rightFrames);

        BufferedImage[] leftFrames = new BufferedImage[4];
        walkLeft = new Animator(leftFrames);

        BufferedImage[] upFrames = new BufferedImage[4];
        walkUp = new Animator(upFrames);

        BufferedImage[] downFrames = new BufferedImage[4];
        walkDown = new Animator(downFrames);

        BufferedImage[] idleFrames = new BufferedImage[2];
        idle = new Animator(idleFrames);

        setAnimator(idle);
    }

    @Override
    public void update(double delta){
        if (!isGoingRight() && !isGoingLeft() && !isGoingUp() && !isGoingDown())
            setAnimator(idle);
        else {
            if (isGoingDown()) {
                setY(delta * (getY() + getBaseSpeed()));
                setAnimator(walkDown);
            } else if (isGoingUp()) {
                setY(getY() - getBaseSpeed());
                setAnimator(walkUp);
            }

            if (isGoingLeft()) {
                setY(getX() - getBaseSpeed());
                setAnimator(walkLeft);
            } else if (isGoingRight()) {
                setY(getX() + getBaseSpeed());
                setAnimator(walkRight);
            }
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
            modBaseHealth(Math.max(1, r.nextInt(15)));
            modBaseMana(Math.max(1, r.nextInt(10)));
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

}
