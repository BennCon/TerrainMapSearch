import java.util.ArrayList;

/*
*	State in rambler's problem
*	Benjamin Consterdine
*/
public class RamblersState extends SearchState {
    //Instance variables
    private Coords coords;
    private double estRemCost;
    
    //Constructor
    public RamblersState(Coords c, int lc, double rc) {
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
    public double getEstRemCost() {
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
        String strat = "manhattan";
        Coords c;
        int succCost;
        double estCostToGoal;
        int x = this.getx();
        int y = this.gety();
        
        if ((x+1) < map.getWidth()) {
            c = new Coords(y, x+1);
            succCost = costToSuccessor(searcher, c);
            estCostToGoal = estRemCost(searcher, strat);
            succs.add(new RamblersState(c, succCost, estCostToGoal));
        }
        if ((y+1) < map.getHeight()) {
             c = new Coords(y+1, x);
            succCost = costToSuccessor(searcher, c);
            estCostToGoal = estRemCost(searcher, strat);
            succs.add(new RamblersState(c, succCost, estCostToGoal));
        }
        if ((x-1) >= 0) {
            c = new Coords(y, x-1);
            succCost = costToSuccessor(searcher, c);
            estCostToGoal = estRemCost(searcher, strat);
            succs.add(new RamblersState(c, succCost, estCostToGoal));
        }
        if ((y-1) >= 0) {
            c = new Coords(y-1, x);
            succCost = costToSuccessor(searcher, c);
            estCostToGoal = estRemCost(searcher, strat);
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
    private double estRemCost(Search searcher, String strat) {
        double estCost;
        RamblersSearch rsearch = (RamblersSearch) searcher;
        Coords goal = rsearch.getGoal();
        int x1 = this.getx();
        int y1 = this.gety();
        int x2 = goal.getx();
        int y2 = goal.gety();
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
    
        if (strat.equals("manhattan")) {
            //For Manhattan Distance - Diff in X + Diff in Y
            estCost =  dx + dy;
        } else {
            //For Euclidean Distance - sqrt((dx)^2 + (dy)^2)
            estCost = Math.sqrt((Math.pow(dx, 2) + Math.pow(dy, 2)));
        }
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
