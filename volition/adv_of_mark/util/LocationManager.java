package volition.adv_of_mark.util;

import volition.adv_of_mark.location.Location;
import volition.adv_of_mark.location.impl.ApartmentRoom;

import java.util.Random;

/**
 * Created by mccloskeybr on 3/30/16.
 */
public class LocationManager {

    public static final int APARTMENT = 0;

    public static Location[][] loadDungeon(int dungeonType){

        if (dungeonType == APARTMENT)
            return loadApartmentLocation();

        return null;

    }

    private static Location[][] loadApartmentLocation(){

        Location[][] map = new Location[10][10];
        int numRooms = 10;

        Random rand = new Random();

        int i = 0;
        int x = 5, y = 5;
        while (i < numRooms) {

            map[y][x] = new ApartmentRoom();

            int r;
            while (map[y][x] != null) {
                r = rand.nextInt(4);

                if (r == 0)
                    x++;
                else if (r == 1)
                    x--;
                else if (r == 2)
                    y++;
                else if (r == 3)
                    y--;

                if (y < 0 || y >= map.length || x < 0 || x >= map[0].length) {
                    x = 5;
                    y = 5;
                }

            }

            i++;

        }

        return map;

    }

}
