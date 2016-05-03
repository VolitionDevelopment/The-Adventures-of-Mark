package volition.adv_of_mark.util;

import volition.adv_of_mark.location.Exit;
import volition.adv_of_mark.location.Location;
import volition.adv_of_mark.location.impl.*;
import volition.adv_of_mark.location.tile.Tile;
import volition.adv_of_mark.mapObject.entity.Entity;
import volition.adv_of_mark.mapObject.entity.shopkeepers.Peppito;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by mccloskeybr on 3/30/16.
 */
public class LocationManager {

    private static HashMap<Integer, Class<? extends Location>> locationProperties;
    public static final int CENTRALTOWN = 200;

    private static Location[][] map;
    private static int loc_x, loc_y;

    public static Class<? extends Location> getLocationFromID(int id){

        if (locationProperties == null) {

            locationProperties = new HashMap<>();

            locationProperties.put(0, null);
            locationProperties.put(100, Apartment.class);
            locationProperties.put(101, House.class);
            locationProperties.put(102, Grass.class);
            locationProperties.put(103, Street.class);
            locationProperties.put(104, Forest.class);

        }

        return locationProperties.get(id);

    }

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

        loc_x = 15;
        loc_y = 5;
    }

    public static void setLocation(int x, int y){
        loc_x = x;
        loc_y = y;
    }

    public static Location[][] loadMap(int mapType){

        Location[][] map = null;

        if (mapType == CENTRALTOWN)
            map = loadCentralTown();

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

        return map;

    }

    private static Location[][] loadCentralTown(){

        Location[][] map = new Location[10][30];

        FileManager.getInstance().loadLocationChunkFromText("/volition/adv_of_mark/assets/maps/chunk/centraltown/ct00.txt", map, 0, 0);
        FileManager.getInstance().loadLocationChunkFromText("/volition/adv_of_mark/assets/maps/chunk/centraltown/ct10.txt", map, 10, 0);
        FileManager.getInstance().loadLocationChunkFromText("/volition/adv_of_mark/assets/maps/chunk/centraltown/ct20.txt", map, 20, 0);

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
