package me.volition.mapObject.entity;

import me.volition.Window;
import me.volition.item.Item;
import me.volition.item.ItemSlot;
import me.volition.item.impl.armor.DeliveryUniform;
import me.volition.item.impl.armor.helmet.Fedora;
import me.volition.item.impl.armor.Jammies;
import me.volition.item.impl.usable.MtnDank;
import me.volition.item.impl.usable.PizzaSlice;
import me.volition.item.impl.usable.WholePizza;
import me.volition.item.impl.weapon.Fists;
import me.volition.item.impl.weapon.Spoon;
import me.volition.location.Location;
import me.volition.mapObject.ObjectEvent;
import me.volition.location.tile.Tile;
import me.volition.move.impl.BadPun;
import me.volition.util.Animator;
import me.volition.util.GameManager;
import me.volition.util.ImageManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mccloskeybr on 2/3/16.
 */
public class Player extends Entity{

    public static final int MAX_INV = 10;

    private int level;
    private int exp;
    private int money;
    private ArrayList<Item> inventory;
    private HashMap<ItemSlot, Item> equippedItems;
    private Animator walkRight, walkLeft, walkUp, walkDown;
    private Animator idleRight, idleLeft, idleUp, idleDown;
    private boolean isInBattle;

    public Player(int x, int y) {
        super("Mark", "Mark is a man fresh out of college. He won 'Frattiest Bro' at his frat house, Theta Xi.", ObjectEvent.NONE, 20, 5, 10, x, y);
        inventory = new ArrayList<>();
        equippedItems = new HashMap<>();

        equip(new Fists());
        equip(new Jammies());

        addItem(new MtnDank());
        addItem(new PizzaSlice());
        addItem(new WholePizza());
        addItem(new DeliveryUniform());
        addItem(new Fedora());
        addItem(new Spoon());

        addMove(new BadPun());

        modMoney(1000);

        //levels up
        modExp(0);
    }

    @Override
    public void loadImages(){

        BufferedImage spriteSheet = ImageManager.getInstance().loadImage("/me/volition/assets/image/entities/player_spritesheet.png");

        //idle

        BufferedImage[] idleLeftFrames = new BufferedImage[2];
        idleLeftFrames[0] = spriteSheet.getSubimage(0, 0, 64, 64);
        idleLeftFrames[1] = spriteSheet.getSubimage(64, 0, 64, 64);
        idleLeft = new Animator(idleLeftFrames);

        BufferedImage[] idleRightFrames = new BufferedImage[2];
        idleRightFrames[0] = spriteSheet.getSubimage(128, 0, 64, 64);
        idleRightFrames[1] = spriteSheet.getSubimage(192, 0, 64, 64);
        idleRight = new Animator(idleRightFrames);

        BufferedImage[] idleUpFrames = new BufferedImage[2];
        idleUpFrames[0] = spriteSheet.getSubimage(0, 64, 64, 64);
        idleUpFrames[1] = spriteSheet.getSubimage(64, 64, 64, 64);
        idleUp = new Animator(idleUpFrames);

        BufferedImage[] idleDownFrames = new BufferedImage[2];
        idleDownFrames[0] = spriteSheet.getSubimage(128, 64, 64, 64);
        idleDownFrames[1] = spriteSheet.getSubimage(192, 64, 64, 64);
        idleDown = new Animator(idleDownFrames);

        //action

        BufferedImage[] leftFrames = new BufferedImage[4];
        leftFrames[0] = spriteSheet.getSubimage(0, 128, 64, 64);
        leftFrames[1] = spriteSheet.getSubimage(64, 128, 64, 64);
        leftFrames[2] = spriteSheet.getSubimage(128, 128, 64, 64);
        leftFrames[3] = spriteSheet.getSubimage(192, 128, 64, 64);
        walkLeft = new Animator(leftFrames);

        BufferedImage[] rightFrames = new BufferedImage[4];
        rightFrames[0] = spriteSheet.getSubimage(0, 192, 64, 64);
        rightFrames[1] = spriteSheet.getSubimage(64, 192, 64, 64);
        rightFrames[2] = spriteSheet.getSubimage(128, 192, 64, 64);
        rightFrames[3] = spriteSheet.getSubimage(192, 192, 64, 64);
        walkRight = new Animator(rightFrames);

        BufferedImage[] downFrames = new BufferedImage[2];
        downFrames[0] = spriteSheet.getSubimage(0, 256, 64, 64);
        downFrames[1] = spriteSheet.getSubimage(64, 256, 64, 64);
        walkDown = new Animator(downFrames);

        BufferedImage[] upFrames = new BufferedImage[2];
        upFrames[0] = spriteSheet.getSubimage(128, 256, 64, 64);
        upFrames[1] = spriteSheet.getSubimage(192, 256, 64, 64);
        walkUp = new Animator(upFrames);

        setAnimator(idleDown);
    }

    @Override
    public void update(double delta){
        super.update(delta);

        if (!isInBattle) {

            if (!isMoving()) {
                if (isFacingLeft())
                    setAnimator(idleLeft);
                else if (isFacingRight())
                    setAnimator(idleRight);
                else if (isFacingUp())
                    setAnimator(idleUp);
                else
                    setAnimator(idleDown);

            }else {

                double speed = delta * getBaseSpeed();

                if (isGoingDown()) {

                    setY(getY() + speed);
                    setX(getX() + speed);

                    setAnimator(walkDown);

                } else if (isGoingUp()) {
                    setY(getY() - speed);
                    setX(getX() - speed);

                    setAnimator(walkUp);

                }
                //up/down animations have priority over left/right
                if (isGoingLeft()) {

                    setY(getY() + speed);
                    setX(getX() - speed);

                    if (!isGoingUp() && !isGoingDown())
                        setAnimator(walkLeft);

                } else if (isGoingRight()) {

                    setY(getY() - speed);
                    setX(getX() + speed);

                    if (!isGoingUp() && !isGoingDown())
                        setAnimator(walkRight);

                }
            }
        }
    }

    public void inspect(){
        GameManager.getInstance().getGameState().getCurrentLocation().inspect();
    }

    public void setInBattle(boolean isInBattle){
        this.isInBattle = isInBattle;
    }

    public String[] getMoves_strarr(){
        String[] moves = new String[getMoves().size()];
        for (int i = 0; i < moves.length; i++)
            moves[i] = getMoves().get(i).getName();
        return moves;
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
            modBaseTolerance(10);
            modBaseBrainpower(10);

            levelUp();
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

    public String[] getInventory_strarr(){
        if (inventory == null)
            System.out.println("Inv is null");
        String[] inv = new String[inventory.size()];
        for (int i = 0; i < inv.length; i++)
            inv[i] = inventory.get(i).getName();
        return inv;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    //returns true if picked up
    public boolean addItem(Item item){
        if (inventory.size() < MAX_INV) {
            this.inventory.add(item);
            return true;
        }
        return false;
    }

    public void removeItem(Item item){
        if(inventory.contains(item)){
            inventory.remove(item);
        }
    }

    public void useItem(int index){
        if (index < inventory.size() && index >= 0) {
            Item item = inventory.get(index);
            item.use(this);
            if (item.getSlot().equals(ItemSlot.NONE)) {
                inventory.remove(item);
            }
            //armor and weapon pieces handle themselves when they go through equip method
        }
    }

    public HashMap<ItemSlot, Item> getEquippedItems() {
        return equippedItems;
    }

    public void equip(Item item){
        if(equippedItems.get(item.getSlot()) != null){
            //replacing an already equipped item
            //replaces values
            if (item.getSlot() == ItemSlot.WEAPON)
                setWepDamage(item.getValue());
            else {
                //unequips, then equips
                setArmor(getArmor() - equippedItems.get(item.getSlot()).getValue());
                setArmor(getArmor() + item.getValue());
            }

            inventory.add(equippedItems.get(item.getSlot()));
            equippedItems.replace(item.getSlot(), item);
            inventory.remove(item);

        } else {
            //nothing equipped
            if (item.getSlot() == ItemSlot.WEAPON)
                setWepDamage(item.getValue());
            else
                setArmor(getArmor() + item.getValue());

            equippedItems.put(item.getSlot(), item);
            inventory.remove(item);
        }
    }

    public int nextLevel(){
        double LEVEL_CONSTANT = 20;
        return (int) (LEVEL_CONSTANT * (level) * (level + 5) - getExp());
    }

    @Override
    public Animator getBattleAnimator(){
        return idleRight;
    }

    @Override
    public void render(Graphics2D g){
        if (GameManager.getInstance().getGameState().getCurrentLocation().hasFreeCamera())
            super.render(g, Window.WINDOW_WIDTH / 2 - getWidth() / 2, Window.WINDOW_HEIGHT / 2 - getHeight() / 2);
        else
            super.render(g);
    }

}
