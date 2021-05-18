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
        TerrainMap map = new TerrainMap("tmc.pgm");
        TerrainMap map2 = new TerrainMap("diablo.pgm");
        TerrainMap map3 = new TerrainMap("map.pgm");
        ArrayList<Float> eff = new ArrayList<Float>();
        HashMap<Coords, Coords> startGoalPairs = new HashMap<Coords, Coords>();
        File file = new File("diabloCoords50.csv");

        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                String coords = scan.nextLine();
                String[] coordArr = coords.split(",");
                int y1 = Integer.parseInt(coordArr[0]);
                int x1 = Integer.parseInt(coordArr[1]);
                int y2 = Integer.parseInt(coordArr[2]);
                int x2 = Integer.parseInt(coordArr[3]);
                startGoalPairs.put(new Coords(y1, x1), new Coords(y2, x2));
            }
        } catch (FileNotFoundException e) {
            System.err.println("no file");
        }

        Iterator Iterator = startGoalPairs.entrySet().iterator();
        while (Iterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry)Iterator.next();
            Coords start  = (Coords)mapElement.getKey();
            Coords goal  = (Coords)mapElement.getValue();
            RamblersSearch searcher = new RamblersSearch(map2, goal);
            SearchState initState = (SearchState) new RamblersState(start, 0);
    
            Float res_bf = searcher.runSearchLessVerbose(initState, "BB");
            eff.add(res_bf);
            System.out.println(start.gety()+","+start.getx()+","+goal.gety()+","+goal.getx()+","+res_bf);
        }


        // System.out.println("Efficiencies:");
        // for (Float i : eff) {
        //     System.out.println(i);
        // }

    }
}
