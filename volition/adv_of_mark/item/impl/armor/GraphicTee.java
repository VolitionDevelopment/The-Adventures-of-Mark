package volition.adv_of_mark.item.impl.armor;

import volition.adv_of_mark.util.ImageManager;

/**
 * Created by mccloskeybr on 2/21/2016.
 */
public class GraphicTee extends Armor {
    public GraphicTee() {
        super("Graphic Tee", "What are you, 15?", 4, 30, ImageManager.getInstance().loadImage("/volition/adv_of_mark/assets/image/items/armor/graphictee.png"));
    }
}
