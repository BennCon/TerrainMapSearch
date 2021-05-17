/**
  * RunRamblersBB.java
  * Benjamin Consterdine
  * 
  * Run Branch and Bound search for Rambler's Problem
*/
public class RunRamblersBB {
    public static void main(String[] args) {
        TerrainMap map = new TerrainMap("tmc.pgm");
        Coords goal =  new Coords(9,11);
        Coords start = new Coords(3,1);

        RamblersSearch searcher = new RamblersSearch(map, goal);
        SearchState initState = (SearchState) new RamblersState(start, 0);

        String res_bf = searcher.runSearch(initState, "BB");
        System.out.println(res_bf);
    }

}
