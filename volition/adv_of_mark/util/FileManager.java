package volition.adv_of_mark.util;

import volition.adv_of_mark.location.Location;
import volition.adv_of_mark.location.tile.Tile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by mccloskeybr on 3/15/16.
 */
public class FileManager {

    private static FileManager instance;
    public static FileManager getInstance(){
        if (instance == null)
            instance = new FileManager();

        return instance;
    }

    public Tile[][] loadTileMapFromText(String path){
        try {

            BufferedReader br = new BufferedReader(new FileReader(new File(getClass().getResource(path).toString().substring(5)))); //substr to get rid of 'file:'

            String line = br.readLine();
            String[] linebits = line.split(" :: ");
            int r = Integer.parseInt(linebits[0]);
            int c = Integer.parseInt(linebits[1]);

            Tile[][] map = new Tile[r][c];

            for (int i = 0; i < r; i++) {

                line = br.readLine();
                linebits = line.split(" ");

                for (int j = 0; j < c; j++)
                    map[i][j] = new Tile(Integer.parseInt(linebits[j]));

            }

            return map;

        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    public void loadLocationChunkFromText(String path, Location[][] map, int x, int y) {

        try {

            BufferedReader br = new BufferedReader(new FileReader(new File(getClass().getResource(path).toString().substring(5)))); //substr to get rid of 'file:'

            String line = br.readLine();
            String[] linebits = line.split(" :: ");
            int r = Integer.parseInt(linebits[0]);
            int c = Integer.parseInt(linebits[1]);

            for (int i = y; i < y + r; i++) {

                line = br.readLine();
                linebits = line.split(" ");

                for (int j = x; j < x + c; j++)
                    map[i][j] = (Location) LocationManager.getLocationFromID(Integer.parseInt(linebits[j - x])).getConstructors()[0].newInstance(j, i);

            }

        } catch (Exception e) { e.printStackTrace(); }

    }

    public void saveGame(){}

    public void loadGame(){}

}
