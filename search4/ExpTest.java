import java.util.ArrayList;
import java.util.Random; 
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.*;
import java.io.DataOutputStream;
import java.io.FileOutputStream;


public class ExpTest {
    public static void main(String[] args) {
        //Loads in possible map files
        TerrainMap map = new TerrainMap("tmc.pgm");
        TerrainMap map2 = new TerrainMap("diablo.pgm");
        TerrainMap map3 = new TerrainMap("map.pgm"); //Never actually used in testing
        ArrayList<Float> eff = new ArrayList<Float>();
        File file = new File("tmcCoords10000.csv"); //Loads CSV of coordinates

        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                //Reads coordinates from file
                String coords = scan.next();
                String[] coordArr = coords.split(",");
                int y1 = Integer.parseInt(coordArr[0]);
                int x1 = Integer.parseInt(coordArr[1]);
                int y2 = Integer.parseInt(coordArr[2]);
                int x2 = Integer.parseInt(coordArr[3]);

                Coords start  = new Coords(y1, x1);
                Coords goal  = new Coords(y2, x2);

                RamblersSearch searcher = new RamblersSearch(map, goal);
                SearchState initState = (SearchState) new RamblersState(start, 0, 0);

                //Runs less verbose version of search - only prints effincies
                Float res_bf = searcher.runSearchLessVerbose(initState, "AStar");
                eff.add(res_bf);
                System.out.println(res_bf);
            }
        } catch (FileNotFoundException e) {
            System.err.println("no file");
        }
    }
}