package dijkstra_angabe;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Seerettung {
  private static PriorityQueue<Eisscholle> nachbarschollen;

  public static List<Eisscholle> findeWeg
   (Eisscholle[] eisschollen,
    List<Seeweg> seewege,
    int startIndex,
    int endIndex)
  {
    nachbarschollen = new PriorityQueue<>(new EisschollenComparator());

    // TODO
    eisschollen[startIndex].setDistance(0);
    eisschollen[startIndex].setState(1);
    int vcount=0;
    for(int i = 0;i<eisschollen.length;i++) {
      if(eisschollen[i].getState()==1) {
        vcount++;
      }
    }
    nachbarschollen.add(eisschollen[startIndex]);
    while(vcount!=0) {
      Eisscholle e = nachbarschollen.poll();
      e.setState(2);
      for(Seeweg sw:seewege) {
        if(sw.getFrom().equals(e)) {
          if(!nachbarschollen.contains(sw.getTo()))
          nachbarschollen.add(sw.getTo());
          if(sw.getTo().getDistance()>e.getDistance()+sw.getDistance())
          {
            sw.getTo().setDistance((e.getDistance()+sw.getDistance()));
            sw.getTo().setVorgaenger(e);
          }
          sw.getTo().setState(1);
         
        }
      }
      vcount = 0;
      for(int i = 0;i<eisschollen.length;i++) {
        if(eisschollen[i].getState()==1) {
          vcount++;
        }
      }
      
    }
    
    List<Eisscholle> ret = new ArrayList<Eisscholle>(); 
    addWeg(ret,eisschollen[endIndex]);
    

    return ret;
  }
  public static void addWeg(List<Eisscholle> ret,Eisscholle e){
    ret.add(0,e);
    if(e.getVorgaenger()!=null)
      addWeg(ret,e.getVorgaenger());
    return;
    
  }
  
  
  private static List<Seeweg> findSeewegeFromEisscholle(List<Seeweg> seewege, Eisscholle eisscholle) {
    return seewege.stream()
        .filter(o -> o.getFrom().equals(eisscholle))
        .collect(Collectors.toList());
  }
}
