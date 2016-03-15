package me.lvgen.util;

import me.lvgen.comp.Tile;

import java.io.*;

/**
 * Created by mccloskeybr on 3/14/16.
 */
public class FileManager {

    private static FileManager instance;

    public static FileManager getInstance(){

        if (instance == null)
            instance = new FileManager();

        return instance;

    }

    public void save(String name, Tile[][] map){

        try {

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(name)));

            bufferedWriter.write(map.length + " :: " + map[0].length);
            for (int i = 0; i < map.length; i++) {

                bufferedWriter.newLine();

                String line = "";
                for (int j = 0; j < map[0].length; j++)
                    line += map[i][j].getID() + " ";

                bufferedWriter.write(line.trim());

            }

            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (Exception e){ e.printStackTrace(); }

    }

    public Tile[][] open(String path){

        try {

            BufferedReader br = new BufferedReader(new FileReader(new File(path)));

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

}
