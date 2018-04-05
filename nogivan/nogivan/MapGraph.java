package nogivan;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import nogivan.OSMNode;
import heap.*;

/**
 * Diese Klasse repräsentiert den Graphen der Straßen und Wege aus
 * OpenStreetMap.
 */
public class MapGraph {
  public Map<Long, OSMNode> nodes;
  public Map<Long, Set<MapEdge>> edges;
  public Map<Long, Point> nodess = new HashMap<Long, Point> ();
  
public MapGraph(Map<Long, OSMNode> m1,Map<Long, Set<MapEdge>> m2) {
   this.nodes = m1;
   this.edges = m2;
}
  /**
   * Ermittelt, ob es eine Kante im Graphen zwischen zwei Knoten gibt.
   * 
   * @param from der Startknoten
   * @param to der Zielknoten
   * @return 'true' falls es die Kante gibt, 'false' sonst
   * 
   */
  boolean hasEdge(OSMNode from, OSMNode to) {
    boolean count = false;
    Set<MapEdge> nodeedges = edges.get(from.getId());
    for (MapEdge ed : nodeedges) {  
      if(ed.getTo() == to.getId()) {
        count = true; 
      break;}
    }  
    
    return count;
  }

  /**
   * Diese Methode findet zu einem gegebenen Kartenpunkt den
   * nähesten OpenStreetMap-Knoten. Gibt es mehrere Knoten mit
   * dem gleichen kleinsten Abstand zu, so wird derjenige Knoten
   * von ihnen zurückgegeben, der die kleinste Id hat.
   * 
   * @param p der Kartenpunkt
   * @return der OpenStreetMap-Knoten
   */
  public OSMNode closest (MapPoint p) {
    OSMNode minnode = null;
    int min = Integer.MAX_VALUE;
    for (OSMNode value : nodes.values()) {  
      if(value == null)
        continue;
      int distance = value.getLocation().distance(p);
      if(distance < min) {
        minnode = value;
        min = distance;
      }
      else if(distance == min) {
        long id = value.getId();
        if(id<minnode.getId())
          minnode = value;
      }
    
  }  
    return minnode;
    
  }

  /**
   * Diese Methode sucht zu zwei Kartenpunkten den kürzesten Weg durch
   * das Straßen/Weg-Netz von OpenStreetMap.
   * 
   * @param from der Kartenpunkt, bei dem gestartet wird
   * @param to der Kartenpunkt, der das Ziel repräsentiert
   * 
   * @return eine mögliche Route zum Ziel und ihre Länge; die Länge
   * des Weges bezieht sich nur auf die Länge im Graphen, der Abstand
   * von 'from' zum Startknoten bzw. 'to' zum Endknoten wird
   * vernachlässigt.
   */
  public RoutingResult route (MapPoint from, MapPoint to) throws NullPointerException{
    PriorityQueue<Point> nachbar;
    nachbar = new PriorityQueue<Point>(new PointComparator());
    for(Long l:nodes.keySet()) {
      Point p = new Point(nodes.get(l));
      nodess.put(l,p);
    }
    
    Point pstart = nodess.get(this.closest(from).getId());
    Point pend = nodess.get(this.closest(to).getId());
    pstart.setDistance(0);
    pstart.setState(1);
    nachbar.add(pstart);
    
    while(true) {
      Point p = nachbar.poll();
      MapPoint m1 = p.getNode().getLocation();
      p.setState(2);
      if(edges.get(p.getNode().getId()) != null) {
        Set<MapEdge> set = edges.get(p.getNode().getId());
        for(MapEdge me:set) {
          
          Long id = me.getTo();
          if(nodess.get(id).getState()!=2) {
            nodess.get(id).setState(1);
          MapPoint npoint = nodes.get(id).getLocation();
          int d1 = m1.distance(npoint);
          int d2 = d1+p.getDistance();
          Point n = nodess.get(id);
         if(d2< n.getDistance()) {
           n.setDistance(d2);
           n.setVorgaenger(p.getNode());
           //if(nachbar.contains(n)) {
             //nachbar.remove(n);
            // nachbar.add(n);
           //}
          // else
             nachbar.add(n);
         }
         }
          
        }
      }
      
      if(pend.getState()==2) {
        System.out.println("Found!!!!!!");
        break;}
      if(nachbar.isEmpty()) {
        System.out.println("Not Found!!!!!!");
        break;}
      
      
    }
    
   int finaldistance = pend.getDistance();
   
   List<OSMNode> result = new ArrayList<OSMNode>();
   result.add(0,pend.getNode());
   if(finaldistance!=Integer.MAX_VALUE) {
     Point current = pend;
    while(current.getVorgaenger()!=null){
     result.add(0,current.getVorgaenger());
     current=nodess.get(current.getVorgaenger().getId());
    }
    OSMNode [] re = new OSMNode[result.size()];
    for(int i=0;i<re.length;i++) {
      re[i]=result.get(i);
    }
    RoutingResult rr = new RoutingResult(re,finaldistance);
    return rr;
   }
   else
     return null;
    
    
    
  }
}
