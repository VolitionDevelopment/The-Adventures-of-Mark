package volition.adv_of_mark.util;

import volition.adv_of_mark.location.Exit;
import volition.adv_of_mark.location.Location;
import volition.adv_of_mark.location.impl.ApartmentRoom;
import volition.adv_of_mark.location.tile.Tile;
import volition.adv_of_mark.state.game.GameState;

import java.util.Random;

/**
 * Created by mccloskeybr on 3/30/16.
 */
public class LocationManager {

    public static final int APARTMENT = 0;

    private static Location[][] map;
    private static int loc_x, loc_y;

    public static Location getCurrentLocation(){
        return map[loc_y][loc_x];
    }

    public static Location getLocationFromMap(int x, int y) {
        if (x < map[0].length && x > -1 && y < map.length && y > -1)
            return map[y][x];
        return null;
    }

    public static void setMap(Location[][] map1) {
        map = map1;

        loc_x = 5;
        loc_y = 5;
    }

    public static void setLocation(int x, int y){
        loc_x = x;
        loc_y = y;
    }

    public static Location[][] loadDungeon(int dungeonType){

        Location[][] map = null;

        if (dungeonType == APARTMENT)
            map = loadApartmentLocation();

        // add exits to other rooms

        if (map != null) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] != null) {
                        if (i > 0 && map[i - 1][j] != null) // north
                            for (int k = 1; k < 14; k++)
                                map[i][j].addExit(new Exit(k * Tile.TILE_SIZE, 0, Tile.TILE_SIZE, Tile.TILE_SIZE, j, i - 1, k * Tile.TILE_SIZE, 13 * Tile.TILE_SIZE, true));

                        if (j > 0 && map[i][j - 1] != null) // west
                            for (int k = 1; k < 14; k++)
                                map[i][j].addExit(new Exit(0, k * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE, j - 1, i, 13 * Tile.TILE_SIZE, k * Tile.TILE_SIZE, true));

                        if (i < map.length - 1 && map[i + 1][j] != null) // south
                            for (int k = 1; k < 14; k++)
                                map[i][j].addExit(new Exit(k * Tile.TILE_SIZE, 14 * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE, j, i + 1, k * Tile.TILE_SIZE, Tile.TILE_SIZE, true));

                        if (j < map[i].length - 1 && map[i][j + 1] != null) // east
                            for (int k = 1; k < 14; k++)
                                map[i][j].addExit(new Exit(14 * Tile.TILE_SIZE, k * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE, j + 1, i, Tile.TILE_SIZE, k * Tile.TILE_SIZE, true));
                    }
                }
            }
        }

        printDungeon(map);

        return map;

    }

    private static Location[][] loadApartmentLocation(){

        Location[][] map = new Location[10][10];

        int numRooms = 10;

        Random rand = new Random();

        int i = 0;
        int x = 5, y = 5;
        while (i < numRooms) {

            map[y][x] = new ApartmentRoom(x, y);

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

    public static Location[][] getSurroundingLocations(Location location) {

        Location[][] surroundingLocations = new Location[3][3];

        surroundingLocations[0][0] = getLocationFromMap(location.getX() - 1, location.getY() - 1);
        surroundingLocations[0][1] = getLocationFromMap(location.getX(), location.getY() - 1);
        surroundingLocations[0][2] = getLocationFromMap(location.getX() + 1, location.getY() - 1);

        surroundingLocations[1][0] = getLocationFromMap(location.getX() - 1, location.getY());
        surroundingLocations[1][1] = getCurrentLocation();
        surroundingLocations[1][2] = getLocationFromMap(location.getX() + 1, location.getY());

        surroundingLocations[2][0] = getLocationFromMap(location.getX() - 1, location.getY() + 1);
        surroundingLocations[2][1] = getLocationFromMap(location.getX(), location.getY() + 1);
        surroundingLocations[2][2] = getLocationFromMap(location.getX() + 1, location.getY() + 1);

        return surroundingLocations;

    }

    public static int[] enterNewArea(int newLoc_x, int newLoc_y) {

        int deltaX = 0;
        int deltaY = 0;

        if (newLoc_x == loc_x - 1) {
            deltaX = + 15 * Tile.TILE_SIZE / 2;
            deltaY = + 15 * Tile.TILE_SIZE / 2;
        } else if (newLoc_x == loc_x + 1) {
            deltaX = - 15 * Tile.TILE_SIZE / 2;
            deltaY = - 15 * Tile.TILE_SIZE / 2;
        } else if (newLoc_y == loc_y - 1) {
            deltaX = - 15 * Tile.TILE_SIZE / 2;
            deltaY = + 15 * Tile.TILE_SIZE / 2;
        } else {
            deltaX = + 15 * Tile.TILE_SIZE / 2;
            deltaY = - 15 * Tile.TILE_SIZE / 2;
        }

        return new int[] {deltaX, deltaY};

    }

    private static void printDungeon(Location[][] map){

        for (int n = 0; n < map.length; n++) {
            for (int j = 0; j < map[n].length; j++) {
                if (map[n][j] == null)
                    System.out.print("-");
                else
                    System.out.print("x");
            }
            System.out.println();
        }

    }

}
