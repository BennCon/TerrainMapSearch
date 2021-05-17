public class RunRamblersAstart {
    public static void main(String[] args) {
        TerrainMap map = new TerrainMap("tmc.pgm");
        Coords goal =  new Coords(8,3);
        Coords start = new Coords(0,0);

        RamblersSearch searcher = new RamblersSearch(map, goal);
        SearchState initState = (SearchState) new RamblersState(start, 0, 0);

        String res_astar = searcher.runSearch(initState, "AStar");
        System.out.println(res_astar);
    }
}

