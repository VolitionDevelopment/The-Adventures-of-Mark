package volition.lvgen.util;

import volition.lvgen.comp.LvGen_Tile;

import java.io.*;

/**
 * Created by mccloskeybr on 3/14/16.
 */
public class LvGen_FileManager {

    private static LvGen_FileManager instance;

    public static LvGen_FileManager getInstance(){

        if (instance == null)
            instance = new LvGen_FileManager();

        return instance;

    }

    public void save(String name, LvGen_Tile[][] map, int tileType){

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(name)));

            bufferedWriter.write(map.length + " :: " + map[0].length);
            for (int i = 0; i < map.length; i++) {

                bufferedWriter.newLine();

                String line = "";
                for (int j = 0; j < map[0].length; j++) {

                    if (map[i][j].getTile_ID() != 0) { // needed for plotting locations (100 needs to be wood, not nothing)

                        if (tileType == 0)
                            line += map[i][j].getTile_ID();
                        else
                            line += 100 + map[i][j].getTile_ID() / 2;

                    } else
                        line += "0";

                    line += " ";

                }

                bufferedWriter.write(line.trim());

            }

            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (Exception e){ e.printStackTrace(); }

    }

    public LvGen_Tile[][] open(String path){

        try {

            BufferedReader br = new BufferedReader(new FileReader(new File(path)));

            String line = br.readLine();
            String[] linebits = line.split(" :: ");
            int r = Integer.parseInt(linebits[0]);
            int c = Integer.parseInt(linebits[1]);

            LvGen_Tile[][] map = new LvGen_Tile[r][c];

            for (int i = 0; i < r; i++) {

                line = br.readLine();
                linebits = line.split(" ");

                for (int j = 0; j < linebits.length; j++)
                    map[i][j] = new LvGen_Tile(Integer.parseInt(linebits[j]));

            }

            return map;

        } catch (Exception e) { e.printStackTrace(); }

        return null;

    }

}
