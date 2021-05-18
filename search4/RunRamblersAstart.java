public class RunRamblersAstart {
    public static void main(String[] args) {
        TerrainMap map = new TerrainMap("diablo.pgm");
        Coords goal =  new Coords(33,36);
        Coords start = new Coords(102,102);

        RamblersSearch searcher = new RamblersSearch(map, goal);
        SearchState initState = (SearchState) new RamblersState(start, 0, 0);

        Float res_astar = searcher.runSearchLessVerbose(initState, "AStar");
        System.out.println(res_astar);
    }
}

