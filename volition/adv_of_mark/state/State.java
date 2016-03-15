package volition.adv_of_mark.state;

import java.awt.*;

/**
 * Created by mccloskeybr on 2/3/16.
 */
public interface State {

    void init();

    void update(double delta);

    void render(Graphics2D g);

    void keyPressed(int k);

    void keyReleased(int k);

}
