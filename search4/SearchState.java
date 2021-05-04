/**
*	State in a state-space search
*	abstract class
*   must implement goalPredicate, getSuccessors, sameState, toString
*   variable cost version - has localCost variable
*   A* version - has estRemCost
*   changes indicated by //A*-
*   Phil Green 2013 version
*   Heidi Christensen (heidi.christensen@sheffield.ac.uk) 2021 version
*/

import java.util.*;

public abstract class SearchState {

  // change from search2 - need to give local cost
  protected int localCost;
  // A*
  protected int estRemCost;

  /**
  * accessor for local cost
  */
  public int getLocalCost(){
    return localCost;
  }

  // A*
  public int getestRemCost(){
    return estRemCost;
  }


  // must implement goalPredicate, getSuccessors, sameState, toString
  // goalPredicate takes a SearchNode & returns a boolean if it's a goal

  abstract boolean goalPredicate(Search searcher);
  abstract ArrayList<SearchState> getSuccessors(Search searcher);
  abstract boolean sameState(SearchState n2);

}
