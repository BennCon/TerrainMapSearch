/**
  * RunRamblersBB.java
  * Benjamin Consterdine
  * 
  * Run Branch and Bound search for Rambler's Problem
*/
public class RunRamblersBB {
    public static void main(String[] args) {
        TerrainMap map = new TerrainMap("diablo.pgm");
        Coords goal =  new Coords(10,50);
        Coords start = new Coords(9,100);

        RamblersSearch searcher = new RamblersSearch(map, goal);
        SearchState initState = (SearchState) new RamblersState(start, 0);

        Float res_bf = searcher.runSearchLessVerbose(initState, "BB");
        System.out.println(res_bf);
    }

}
