/*
* 	RamblersSearch.java
*
*	search for rambler's problem
* Benjamin Consterdine
*/
public class RamblersSearch extends Search {

    private TerrainMap map; //map for traversal
    private Coords goal; //Goal coordinates

    public RamblersSearch(TerrainMap m, Coords g) {
        map = m;
        goal = g;
    }

    //Accesors
    public TerrainMap getMap() {
        return map;
    }
    public Coords getGoal() {
        return goal;
    }


}
