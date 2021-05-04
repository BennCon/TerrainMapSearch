import java.util.ArrayList;

/*
*	State in rambler's problem
*	Benjamin Consterdine
*/
public class RamblersState extends SearchState {
    
    //Coordinates
    private Coords coords;
    
    //Constructor
    public RamblersState(Coords c, int lc) {
        coords = c;
        localCost = lc;
    }

    //Accessors for coordinates
    public Coords getCoords() {
        return coords;
    }
    public int getx() {
        return coords.getx();
    }
    public int gety() {
        return coords.gety();
    }

    //sameState
    public boolean sameState(SearchState s2) {
        RamblersState rs2 = (RamblersState) s2;
        return (coords.equals(rs2.getCoords()) && localCost == rs2.getLocalCost());
    }

    public ArrayList<SearchState> getSuccessors(Search searcher) {
        RamblersSearch rsearch = (RamblersSearch) searcher;
        TerrainMap map = rsearch.getMap();
        ArrayList<SearchState> succs = new ArrayList<SearchState>();
        int x = this.getx();
        int y = this.gety();
        
        if ((x+1) < map.getWidth()) {
            succs.add(new RamblersState(new Coords(y, x+1), costToSuccessor(searcher, new Coords(y, x+1))));
        }
        if ((y+1) < map.getHeight()) {
            succs.add(new RamblersState(new Coords(y+1, x), costToSuccessor(searcher, new Coords(y+1, x))));
        }
        if ((x-1) >= 0) {
            succs.add(new RamblersState(new Coords(y, x-1), costToSuccessor(searcher, new Coords(y, x-1))));
        }
        if ((y-1) >= 0) {
            succs.add(new RamblersState(new Coords(y-1, x), costToSuccessor(searcher, new Coords(y-1, x))));
        }        

        return succs;
    }

    //Cost to successor
    private int costToSuccessor(Search searcher, Coords c) {
        int cost;
        RamblersSearch rsearch = (RamblersSearch) searcher;
        TerrainMap map = rsearch.getMap();
        int currentHeight = map.getTmap()[gety()][getx()];
        int succHeight = map.getTmap()[c.gety()][c.getx()];

        if (succHeight <= currentHeight) {
            cost = 1;
        } else {
            cost = 1 + (succHeight - currentHeight);
        }
        return cost;
    }


    public boolean goalPredicate(Search searcher) {
       RamblersSearch rsearch = (RamblersSearch) searcher;
       Coords goal = rsearch.getGoal();
       return (coords == goal); 
    }

    public static void main(String[] args) {
        RamblersState current = new RamblersState(new Coords(7,3), 1);
        RamblersSearch search = new RamblersSearch(new TerrainMap("tmc.pgm"), new Coords(7,5));

        for (SearchState s : current.getSuccessors(search)) {
            RamblersState rs = (RamblersState) s;
            System.out.println("(" + rs.gety() + "," + rs.getx() + ") - " + rs.getLocalCost());
        }
    }

}
