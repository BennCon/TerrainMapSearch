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
    boolean sameState(SearchState s2) {
        RamblersState rs2 = (RamblersState) s2;
        return (coords.equals(rs2.getCoords()) && localCost == rs2.getLocalCost());
    }

    ArrayList<SearchState> getSuccessors(Search searcher) {
        // TODO Auto-generated method stub
        return null;
    }

    boolean goalPredicate(Search searcher) {
        // TODO Auto-generated method stub
        return false;
    }

}
