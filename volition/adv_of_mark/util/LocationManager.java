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
                                map[i][j].addExit(new Exit(k, 0, j, i - 1, k * Tile.TILE_SIZE, 13 * Tile.TILE_SIZE));

                        if (j > 0 && map[i][j - 1] != null) // west
                            for (int k = 1; k < 14; k++)
                                map[i][j].addExit(new Exit(0, k, j - 1, i, 13 * Tile.TILE_SIZE, k * Tile.TILE_SIZE));

                        if (i < map.length - 1 && map[i + 1][j] != null) // south
                            for (int k = 1; k < 14; k++)
                                map[i][j].addExit(new Exit(k, 14, j, i + 1, k * Tile.TILE_SIZE, Tile.TILE_SIZE));

                        if (j < map[i].length - 1 && map[i][j + 1] != null) // east
                            for (int k = 1; k < 14; k++)
                                map[i][j].addExit(new Exit(14, k, j + 1, i, Tile.TILE_SIZE, k * Tile.TILE_SIZE));
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

    // BUG: if player is on edge of map will get out of bounds exception
    public static Location[][] getSurroundingLocations(Location location) {

        Location[][] surroundingLocations = new Location[5][5];

        for (int i = -2; i < 3; i++)
            for (int j = -2; j < 3; j++)
                if (getCurrentLocation().getX() + j >= 0 && getCurrentLocation().getX() + j < map[0].length &&
                        getCurrentLocation().getY() + i >= 0 && getCurrentLocation().getY() + i < map.length)
                    surroundingLocations[i + 2][j + 2] = getLocationFromMap(location.getX() + j, location.getY() + i);

        return surroundingLocations;

    }

    public static int[] enterNewArea(int newLoc_x, int newLoc_y) {

        int tranX;
        int tranY;

        int playerX = 0;
        int playerY = 0;

        int mapSize = 14 * Tile.TILE_SIZE;

        if (newLoc_x == loc_x - 1) {
            tranX = + mapSize / 2;
            tranY = + mapSize / 2;
            playerX = + mapSize / 2;
        } else if (newLoc_x == loc_x + 1) {
            tranX = - mapSize / 2;
            tranY = - mapSize / 2;
            playerX = - mapSize / 2;
        } else if (newLoc_y == loc_y - 1) {
            tranX = - mapSize / 2;
            tranY = + mapSize / 2;
            playerY = + mapSize / 2;
        } else {
            tranX = + mapSize / 2;
            tranY = - mapSize / 2;
            playerY = - mapSize / 2;
        }

        // bg delX, bg delY, player delX, player delY
        return new int[] {tranX, tranY, playerX, playerY};

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
