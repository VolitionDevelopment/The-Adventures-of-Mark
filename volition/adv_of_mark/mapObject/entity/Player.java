package volition.adv_of_mark.mapObject.entity;

import volition.adv_of_mark.item.Item;
import volition.adv_of_mark.item.ItemSlot;
import volition.adv_of_mark.item.impl.armor.DeliveryUniform;
import volition.adv_of_mark.item.impl.armor.helmet.Fedora;
import volition.adv_of_mark.item.impl.armor.Jammies;
import volition.adv_of_mark.item.impl.usable.MtnDank;
import volition.adv_of_mark.item.impl.usable.PizzaSlice;
import volition.adv_of_mark.item.impl.usable.WholePizza;
import volition.adv_of_mark.item.impl.weapon.Fists;
import volition.adv_of_mark.item.impl.weapon.Spoon;
import volition.adv_of_mark.location.Location;
import volition.adv_of_mark.mapObject.ObjectEvent;
import volition.adv_of_mark.location.tile.Tile;
import volition.adv_of_mark.move.impl.BadPun;
import volition.adv_of_mark.util.Animator;
import volition.adv_of_mark.util.GameManager;
import volition.adv_of_mark.util.ImageManager;
import volition.adv_of_mark.util.LocationManager;

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
    private boolean ableMove;

    public Player(int x, int y) {
        super("Mark", "Mark is a man fresh out of college. He won 'Frattiest Bro' at his frat house, Theta Xi.", ObjectEvent.NONE, 20, 5, 10, x, y, Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
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

        BufferedImage spriteSheet = ImageManager.getInstance().loadImage("/volition/adv_of_mark/assets/image/entities/player_spritesheet.png");

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

        if (ableMove) {

            if (!isMoving()) {
                if (isFacingLeft())
                    setAnimator(idleLeft);
                else if (isFacingRight())
                    setAnimator(idleRight);
                else if (isFacingUp())
                    setAnimator(idleUp);
                else
                    setAnimator(idleDown);

            } else {

                Location location = LocationManager.getCurrentLocation();

                double speed = delta * getBaseSpeed();

                if (isGoingDown()) {
                    if (location.ableMoveDown() && location.ableMoveRight()) {

                        setX(getX() + speed);
                        setY(getY() + speed);

                        setAnimator(walkDown);

                    } else
                        setAnimator(idleDown);

                } else if (isGoingUp()) {
                    if (location.ableMoveUp() && location.ableMoveLeft()){

                        setX(getX() - speed);
                        setY(getY() - speed);

                        setAnimator(walkUp);

                    } else
                        setAnimator(idleUp);

                }

                //up/down animations have priority over left/right
                if (isGoingLeft()) {
                    if (location.ableMoveDown() && location.ableMoveLeft()){

                        setX(getX() - speed);
                        setY(getY() + speed);

                        if (!isGoingUp() && !isGoingDown())
                            setAnimator(walkLeft);

                    } else if (!isGoingUp() && !isGoingDown())
                        setAnimator(idleLeft);

                } else if (isGoingRight()) {
                    if (location.ableMoveUp() && location.ableMoveRight()){

                        setX(getX() + speed);
                        setY(getY() - speed);

                        if (!isGoingUp() && !isGoingDown())
                            setAnimator(walkRight);

                    } else if (!isGoingUp() && !isGoingDown())
                        setAnimator(idleRight);

                }
            }
        }
    }

    public void inspect(){
        LocationManager.getCurrentLocation().inspect();
    }

    public void setAbleMove(boolean ableMove){
        this.ableMove = ableMove;
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

}
