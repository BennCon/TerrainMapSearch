
/**
 * MapLink.java
 *
 * A link in a map
 * 
 * Phil Green 2013 version
 * Heidi Christensen (heidi.christensen@sheffield.ac.uk) 2021 version
 */

public class MapLink {
  private String city1;
  private String city2;
  private int cost;

  public  MapLink(String c1, String c2, int c){
    city1=c1;
    city2=c2;
    cost=c;
  }
  public String getCity1(){return city1;}
  public String getCity2(){return city2;}
  public int getCost() {return cost;}

  public String toString() {return city1+ " <--> "+city2+" "+ cost;}

}

