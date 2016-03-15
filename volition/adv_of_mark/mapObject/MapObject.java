package volition.adv_of_mark.mapObject;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/19/16.
 */
public abstract class MapObject {

    private double x, y;
    private int length, width, height; //in terms of multiview measurements, not isometric
    private String name, desc;
    private ObjectEvent onInspect;

    public MapObject(double x, double y, int length, int width, int height, ObjectEvent onInspect, String name, String desc){
        this.x = x;
        this.y = y;
        this.length = length;
        this.width = width;
        this.height = height;
        this.onInspect = onInspect;
        this.name = name;
        this.desc = desc;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getLength() {
        return length;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public ObjectEvent getOnInspect(){
        return onInspect;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setOnInspect(ObjectEvent onInspect){
        this.onInspect = onInspect;
    }

    public abstract BufferedImage getImage();

    public abstract void render(Graphics2D g);

    public abstract void render(Graphics2D g, int x, int y);

}