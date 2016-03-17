package volition.lvgen.util;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 3/16/16.
 */
public class LvGen_ObjectProperty {

    private BufferedImage iconImage;
    private BufferedImage mapImage;

    public LvGen_ObjectProperty(BufferedImage iconImage, BufferedImage mapImage) {
        this.iconImage = iconImage;
        this.mapImage = mapImage;
    }

    public BufferedImage getIconImage() {
        return iconImage;
    }

    public BufferedImage getMapImage() {
        return mapImage;
    }
}
