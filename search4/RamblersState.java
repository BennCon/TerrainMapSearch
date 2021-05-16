import java.util.ArrayList;

/*
*	State in rambler's problem
*	Benjamin Consterdine
*/
public class RamblersState extends SearchState {
    //Instance variables
    private Coords coords;
    private int estRemCost;
    
    //Constructor
    public RamblersState(Coords c, int lc, int rc) {
        coords = c;
        localCost = lc;
        estRemCost = rc;
    }

    //Accessors
    public Coords getCoords() {
        return coords;
    }
    public int getx() {
        return coords.getx();
    }
    public int gety() {
        return coords.gety();
    }
    public int getEstRemCost() {
        return estRemCost;
    }

    //Checks if two states are identical
    public boolean sameState(SearchState s2) {
        RamblersState rs2 = (RamblersState) s2;
        return (coords.equals(rs2.getCoords()) && localCost == rs2.getLocalCost() && estRemCost == rs2.estRemCost);
    }

    //Gets successors of a given state
    public ArrayList<SearchState> getSuccessors(Search searcher) {
        RamblersSearch rsearch = (RamblersSearch) searcher;
        TerrainMap map = rsearch.getMap();
        ArrayList<SearchState> succs = new ArrayList<SearchState>();
        Coords c;
        int succCost;
        int estCostToGoal;
        int x = this.getx();
        int y = this.gety();
        
        if ((x+1) < map.getWidth()) {
            c = new Coords(y, x+1);
            succCost = costToSuccessor(searcher, c);
            estCostToGoal = estRemCost(searcher);
            succs.add(new RamblersState(c, succCost, estCostToGoal));
        }
        if ((y+1) < map.getHeight()) {
             c = new Coords(y+1, x);
            succCost = costToSuccessor(searcher, c);
            estCostToGoal = estRemCost(searcher);
            succs.add(new RamblersState(c, succCost, estCostToGoal));
        }
        if ((x-1) >= 0) {
            c = new Coords(y, x-1);
            succCost = costToSuccessor(searcher, c);
            estCostToGoal = estRemCost(searcher);
            succs.add(new RamblersState(c, succCost, estCostToGoal));
        }
        if ((y-1) >= 0) {
            c = new Coords(y-1, x);
            succCost = costToSuccessor(searcher, c);
            estCostToGoal = estRemCost(searcher);
            succs.add(new RamblersState(c, succCost, estCostToGoal));
        }        

        return succs;
    }

    //Cost to successor
    private int costToSuccessor(Search searcher, Coords c) {
        int cost;
        RamblersSearch rsearch = (RamblersSearch) searcher;
        TerrainMap map = rsearch.getMap();
        int currentHeight = map.getTmap()[this.gety()][this.getx()];
        int succHeight = map.getTmap()[c.gety()][c.getx()];

        if (succHeight <= currentHeight) {
            cost = 1;
        } else {
            cost = 1 + (succHeight - currentHeight);
        }
        return cost;
    }


    //Estimated cost to goal for A* heuristic 
    private int estRemCost(Search searcher) {
        int estCost;
        RamblersSearch rsearch = (RamblersSearch) searcher;
        Coords goal = rsearch.getGoal();
    
        //For Manhattan Distance - Diff in X + Diff in Y
        int dx = Math.abs(goal.getx() - this.getx());
        int dy = Math.abs(goal.gety() - this.gety());
        estCost =  dx + dy;

        return estCost;
    }

    public boolean goalPredicate(Search searcher) {
        RamblersSearch rsearch = (RamblersSearch) searcher;
        Coords goal = rsearch.getGoal();
        return (coords.equals(goal)); 
    }

    public String toString() {
        return "(" + this.gety() + "," + this.getx() + ") Local Cost: "
         + this.getLocalCost() + " //  Est. Remaining Cost: " + this.getEstRemCost();
    }

    
}
