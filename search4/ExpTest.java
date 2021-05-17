import java.util.ArrayList;
import java.util.Random; 

public class ExpTest {
    public static void main(String[] args) {
        TerrainMap map = new TerrainMap("tmc.pgm");
        TerrainMap map2 = new TerrainMap("diablo.pgm");
        TerrainMap map3 = new TerrainMap("map.pgm");
        TerrainMap[] maps = {map};
        ArrayList<Float> eff = new ArrayList<Float>();


        for (TerrainMap m : maps) {
            for (int i = 0; i < 1; i++) {
                Random random = new Random();
                int x1 = random.nextInt(m.getWidth());
                int y1 = random.nextInt(m.getDepth());
                int x2 = random.nextInt(m.getWidth());
                int y2 = random.nextInt(m.getDepth());

                Coords goal =  new Coords(y1,x1);
                Coords start = new Coords(y2,x2);

                RamblersSearch searcher = new RamblersSearch(m, goal);
                SearchState initState = (SearchState) new RamblersState(start, 0, 0);
        
                float res_bf = searcher.runSearchLessVerbose(initState, "AStar");
                eff.add(res_bf);
                // System.out.println(res_bf);
            }
        } 
        System.out.println("Efficiencies:");
        for (Float i : eff) {
            System.out.println(i);
        }

    }
}