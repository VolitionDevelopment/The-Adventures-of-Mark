package volition.adv_of_mark.util;

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

    public Tile[][] loadMapFromText(String path){
        try {

            System.out.println(getClass().getResource(path).toString());
            BufferedReader br = new BufferedReader(new FileReader(new File(getClass().getResource(path).toString().substring(5))));

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

    public void saveGame(){}

    public void loadGame(){}

}
