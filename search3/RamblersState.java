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

    //Checks if two states have the same coordinates
    public boolean sameState(SearchState s2) {
        RamblersState rs2 = (RamblersState) s2;
        return (coords.equals(rs2.getCoords()));
    }

    //Gets successors to a givens tate
    public ArrayList<SearchState> getSuccessors(Search searcher) {
        RamblersSearch rsearch = (RamblersSearch) searcher;
        TerrainMap map = rsearch.getMap();
        ArrayList<SearchState> succs = new ArrayList<SearchState>();
        Coords c;
        int succCost;
        int x = this.getx();
        int y = this.gety();
        
        //Has to check for map boundaries
        if ((x-1) >= 0) {
            c = new Coords(y, x-1);
            succCost = costToSuccessor(searcher, c);
            succs.add(new RamblersState(c, succCost));
        }
        if ((x+1) < map.getWidth()) {
            c = new Coords(y, x+1);
            succCost = costToSuccessor(searcher, c);
            succs.add(new RamblersState(c, succCost));
        }
        if ((y+1) < map.getDepth()) {
            c = new Coords(y+1, x);
            succCost = costToSuccessor(searcher, c);
            succs.add(new RamblersState(c, succCost));
        }
        if ((y-1) >= 0) {
            c = new Coords(y-1, x);
            succCost = costToSuccessor(searcher, c);
            succs.add(new RamblersState(c, succCost));
        }       

        return succs;
    }

    //Helper method for finding cost to successor
    private int costToSuccessor(Search searcher, Coords c) {
        int cost;
        RamblersSearch rsearch = (RamblersSearch) searcher;
        TerrainMap map = rsearch.getMap();
        //Gets height of current and successor nodes
        int currentHeight = map.getTmap()[gety()][getx()];
        int succHeight = map.getTmap()[c.gety()][c.getx()];

        //Checks if successor is higher than current node
        if (succHeight <= currentHeight) {
            cost = 1;
        } else {
            cost = 1 + (succHeight - currentHeight);
        }
        return cost;
    }

    //Checks if a state matches the goal
    public boolean goalPredicate(Search searcher) {
       RamblersSearch rsearch = (RamblersSearch) searcher;
       Coords goal = rsearch.getGoal();
       return (coords.equals(goal)); 
    }

    public String toString() {
        return "(" + this.gety() + "," + this.getx() + 
        ") Local Cost: " + this.getLocalCost();
    }
}
