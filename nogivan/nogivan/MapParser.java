package nogivan;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import nogivan.MapGraph;
import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 * Diese Klasse erlaubt es, aus einer Datei im OSM-Format ein MapGraph-Objekt zu erzeugen. Sie nutzt
 * dazu einen XML-Parser.
 */


public class MapParser {
  public static void main(String[] args) {
    MapGraph map = parseFile("campus_garching.osm");
    
  }

  
  public static MapGraph parseFile(String fileName) {
    
    try {
      File inputFile = new File(fileName);
      SAXParserFactory factory = SAXParserFactory.newInstance();
      SAXParser saxParser = factory.newSAXParser();
      UserHandler userhandler = new UserHandler();
      
      saxParser.parse(inputFile, userhandler);  
      HashMap<Long,OSMNode> allnodesmap = userhandler.map1;
      List<OSMWay> allways = userhandler.ways;
      OSMWay[] ways = allways.toArray(new OSMWay[0]);
      List<Long> validnodes = userhandler.validnodes;
      HashMap<Long,OSMNode> map1 = new HashMap<Long,OSMNode>();
      Long[] valid = validnodes.toArray(new Long[0]);
      for(Long l:valid) {
        //if(allnodesmap.get(l)!=null)
        map1.put(l, allnodesmap.get(l));
      }
      Map<Long, Set<MapEdge>> map2 = new HashMap<Long, Set<MapEdge>>();
      List<MapEdge> alledge = new ArrayList<MapEdge>();
      for(int i=0;i<ways.length;i++) {
        Long [] nodes = ways[i].getNodes();
        for( int j =0;j<nodes.length;j++) {
          MapEdge m = new MapEdge(nodes[j],ways[i]);
          alledge.add(m);
        }
      }
      MapEdge [] alledges = alledge.toArray(new MapEdge[0]);
      
      for(int i =0;i<ways.length;i++) {
        if(ways[i].isOneWay()) {
          Long [] waynodes = ways[i].getNodes();
          for(int j = 0;j<waynodes.length-1;j++) {
            if(map2.containsKey(waynodes[j])) {
              Set<MapEdge> eset= map2.get(waynodes[j]);
              
              for(MapEdge e:alledges) {
                Long id = e.getTo();
                OSMWay way = e.getWay();
                if(id.equals(waynodes[j+1]) && way.equals(ways[i]))
                  eset.add(e);
              }
              
           }
            else {
              map2.put(waynodes[j], new HashSet<MapEdge>() );
              Set<MapEdge> eset= map2.get(waynodes[j]);
              
              for(MapEdge e:alledges) {
                Long id = e.getTo();
                OSMWay way = e.getWay();
                if(id.equals(waynodes[j+1]) && way.equals(ways[i]))
                  eset.add(e);
              }
            }
          }
        }
        else {
          Long [] waynodes = ways[i].getNodes();
          for(int j = 0;j<waynodes.length;j++) {
            if(map2.containsKey(waynodes[j])) {
              if(j==0) {
                Set<MapEdge> eset= map2.get(waynodes[j]);
                
                for(MapEdge e:alledges) {
                  Long id = e.getTo();
                  OSMWay way = e.getWay();
                  if(id.equals(waynodes[j+1]) && way.equals(ways[i]))
                    eset.add(e);
                }
                
                }
              else if(j==(waynodes.length-1)) {
                Set<MapEdge> eset= map2.get(waynodes[j]);
                
                for(MapEdge e:alledges) {
                  Long id = e.getTo();
                  OSMWay way = e.getWay();
                  if(id.equals(waynodes[j-1]) && way.equals(ways[i]))
                    eset.add(e);
                }
                
              }
              else {
                Set<MapEdge> eset= map2.get(waynodes[j]);
                
                for(MapEdge e:alledges) {
                  Long id = e.getTo();
                  OSMWay way = e.getWay();
                  if(id.equals(waynodes[j-1]) && way.equals(ways[i]))
                    eset.add(e);
                  if(id.equals(waynodes[j+1]) && way.equals(ways[i]))
                    eset.add(e);
                }
              }
            }
            else {
              if(j==0) {
              map2.put(waynodes[j], new HashSet<MapEdge>() );
              Set<MapEdge> eset= map2.get(waynodes[j]);
              
              for(MapEdge e:alledges) {
                Long id = e.getTo();
                OSMWay way = e.getWay();
                if(id.equals(waynodes[j+1]) && way.equals(ways[i]))
                  eset.add(e);
              }
              } else if(j==(waynodes.length-1)) {
                map2.put(waynodes[j], new HashSet<MapEdge>() );
                Set<MapEdge> eset= map2.get(waynodes[j]);
                
                for(MapEdge e:alledges) {
                  Long id = e.getTo();
                  OSMWay way = e.getWay();
                  if(id.equals(waynodes[j-1]) && way.equals(ways[i]))
                    eset.add(e);
                }
              } else {
                map2.put(waynodes[j], new HashSet<MapEdge>() );
                Set<MapEdge> eset= map2.get(waynodes[j]);
                
                for(MapEdge e:alledges) {
                  Long id = e.getTo();
                  OSMWay way = e.getWay();
                  if(id.equals(waynodes[j-1]) && way.equals(ways[i]))
                    eset.add(e);
                  if(id.equals(waynodes[j+1]) && way.equals(ways[i]))
                    eset.add(e);
                }
              }
            }
          }  
        }
      }
      
      MapGraph m = new MapGraph(map1,map2);
     return m; 
      
   } catch (Exception e) {
      e.printStackTrace();
   }
    return null;
  }
  
}
  
  class UserHandler extends DefaultHandler {
    boolean bnodes = false;
    long nodeid;
    double nodelat;
    double nodelon;
    boolean nodefilter = false;
    
    
    boolean bway = false;
    long wayid = 0;
    boolean oneway = false;
    boolean wayfilter = false;
    String wayname =""; 
 
    public HashMap<Long,OSMNode> map1 = new HashMap<Long,OSMNode>();

    public List<Long> waynodes = new ArrayList<Long>();
    public List<OSMWay> ways = new ArrayList<OSMWay>();
    
    public List<Long> validnodes = new ArrayList<Long>();
    
    @Override
    public void startElement(
       String uri, String localName, String qName, Attributes attributes)
       throws SAXException {
       
       if (qName.equalsIgnoreCase("node")) {
         bnodes = true;
          nodeid = Long.parseLong(attributes.getValue("id"));
          nodelat = Double.parseDouble(attributes.getValue("lat"));
          nodelon = Double.parseDouble(attributes.getValue("lon"));
                          
       } else if (qName.equalsIgnoreCase("way")) {
          bway = true;
          wayid = Long.parseLong(attributes.getValue("id"));
          
       } else if (qName.equalsIgnoreCase("nd") && bway) {
         Long id = Long.parseLong(attributes.getValue("ref"));
         waynodes.add(id);
         
       } else if (qName.equalsIgnoreCase("tag") && bway) {
         if(attributes.getValue("k").equals("oneway")) {
             oneway = true;
         }
         if(attributes.getValue("k").equals("name")) {
          wayname = attributes.getValue("v");
             
         }if(attributes.getValue("k").equals("construction")) {
           wayfilter = true;
         }
         if(attributes.getValue("k").equals("building")) {
           wayfilter = true;
         }
         if(attributes.getValue("k").equals("landuse")) {
           wayfilter = false;
         }
         if(attributes.getValue("k").equals("room")) {
           wayfilter = false;
         }
         if(attributes.getValue("k").equals("highway")) {
           wayfilter = false;
         }
         if(attributes.getValue("v").equals("tree_row")) {
           wayfilter = true;
         }
       } else if (qName.equalsIgnoreCase("tag") && bnodes) {
         if(attributes.getValue("k").equals("highway"))
           nodefilter = true;
           
       }
    }

    @Override
    public void endElement(String uri, 
       String localName, String qName) throws SAXException {
      
      
      if (qName.equalsIgnoreCase("node")) {
//        if(nodefilter == false) {
          OSMNode o = new OSMNode(nodeid,nodelat,nodelon);
          map1.put(nodeid, o);  
//        }
        nodefilter = false;
        bnodes = false;
        
      }
      
      else if (qName.equalsIgnoreCase("way")) {
      if(wayfilter == false) {
//        if(true) {
        Long[] ids = waynodes.toArray(new Long[0]);
        OSMWay way = new OSMWay(wayid,ids,oneway,wayname);
        ways.add(way);
        for(Long l:ids) {
          if(!validnodes.contains(l))
          validnodes.add(l);
        }
      }
      bway = false;
      wayid = 0;
      oneway = false;
      wayfilter = false;
      wayname =""; 
      waynodes.clear();
    }
      
  }

    @Override
    public void endDocument() {
    
    }
    
}
