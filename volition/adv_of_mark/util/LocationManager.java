package volition.adv_of_mark.util;

import volition.adv_of_mark.location.Exit;
import volition.adv_of_mark.location.Location;
import volition.adv_of_mark.location.impl.ApartmentRoom;
import volition.adv_of_mark.location.tile.Tile;

import java.util.Random;

/**
 * Created by mccloskeybr on 3/30/16.
 */
public class LocationManager {

    public static final int APARTMENT = 0;

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
                            map[i][j].addExit(new Exit(7 * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE, j, i - 1, 7 * Tile.TILE_SIZE, 12 * Tile.TILE_SIZE, true));
                        if (j > 0 && map[i][j - 1] != null) // west
                            map[i][j].addExit(new Exit(Tile.TILE_SIZE, 7 * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE, j - 1, i, 12 * Tile.TILE_SIZE, 7 * Tile.TILE_SIZE, true));
                        if (i < map.length - 1 && map[i + 1][j] != null) // south
                            map[i][j].addExit(new Exit(7 * Tile.TILE_SIZE, 13 * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE, j, i + 1, 7 * Tile.TILE_SIZE, 2 * Tile.TILE_SIZE, true));
                        if (j < map[i].length - 1 && map[i][j + 1] != null) // east
                            map[i][j].addExit(new Exit(13 * Tile.TILE_SIZE, 7 * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE, j + 1, i, 2 * Tile.TILE_SIZE, 7 * Tile.TILE_SIZE, true));
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
