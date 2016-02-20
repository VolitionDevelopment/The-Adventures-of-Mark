package me.volition.mapObject;

/**
 * Created by mccloskeybr on 2/19/16.
 */
public abstract class MapObject {

    private double x, y;
    private String name, desc;
    private ObjectEvent onInspect;

    public MapObject(double x, double y, ObjectEvent onInspect, String name, String desc){
        this.x = x;
        this.y = y;
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

}