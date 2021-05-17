import java.util.ArrayList;

/*
*	State in rambler's problem
*	Benjamin Consterdine
*/
public class RamblersState extends SearchState {
    //Instance variables
    private Coords coords;

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
        String strat = "height";
        Coords c;
        int succCost;
        int estCostToGoal;
        int x = this.getx();
        int y = this.gety();
        
        if ((x+1) < map.getWidth()) {
            c = new Coords(y, x+1);
            succCost = costToSuccessor(searcher, c);
            estCostToGoal = estRemCost(searcher, strat, c);
            succs.add(new RamblersState(c, succCost, estCostToGoal));
        }
        if ((y+1) < map.getDepth()) {
            c = new Coords(y+1, x);
            succCost = costToSuccessor(searcher, c);
            estCostToGoal = estRemCost(searcher, strat, c);
            succs.add(new RamblersState(c, succCost, estCostToGoal));
        }
        if ((x-1) >= 0) {
            c = new Coords(y, x-1);
            succCost = costToSuccessor(searcher, c);
            estCostToGoal = estRemCost(searcher, strat, c);
            succs.add(new RamblersState(c, succCost, estCostToGoal));
        }
        if ((y-1) >= 0) {
            c = new Coords(y-1, x);
            succCost = costToSuccessor(searcher, c);
            estCostToGoal = estRemCost(searcher, strat, c);
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
    private int estRemCost(Search searcher, String strat, Coords c) {
        double estCost;
        RamblersSearch rsearch = (RamblersSearch) searcher;
        Coords goal = rsearch.getGoal();
        int x1 = c.getx();
        int y1 = c.gety();
        int x2 = goal.getx();
        int y2 = goal.gety();
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int dHeight = rsearch.getMap().getTmap()[y2][x2] - rsearch.getMap().getTmap()[y1][x1];
    
        if (strat.equals("manhattan")) {
            //For Manhattan Distance - Diff in X + Diff in Y
            estCost =  dx + dy;
        } else if (strat.equals("euclid")) {
            //For Euclidean Distance - sqrt((dx)^2 + (dy)^2)
            estCost = Math.sqrt((Math.pow(dx, 2) + Math.pow(dy, 2)));
        } else if (strat.equals("height")) {
            //For Height Difference
            if (dHeight > 0) {
                estCost = dHeight + (dy + dx);
            } else {
                estCost = dy + dx;
            }
        } else if (strat.equals("manhattan3d")) {
            //3D manhattan
            estCost = dx + dy + dHeight;
        } else {
            estCost = Math.sqrt((Math.pow(dx, 2) + Math.pow(dy, 2) + Math.pow(dHeight,2)));
        }
        return (int)estCost;
    }

    public boolean goalPredicate(Search searcher) {
        RamblersSearch rsearch = (RamblersSearch) searcher;
        Coords goal = rsearch.getGoal();
        return (coords.equals(goal)); 
    }

    public String toString() {
        return "(" + this.gety() + "," + this.getx() + ") Local Cost: "
         + this.getLocalCost() + " //  Est. Remaining Cost: " + this.getestRemCost();
    }


    
}
