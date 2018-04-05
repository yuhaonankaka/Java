package nogivan;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class GPXWriter {
  private PrintWriter writer;

  public GPXWriter(String fileName) throws FileNotFoundException {
    writer = new PrintWriter(new File(fileName));
  }

  private void writeLine(String line) {
    writer.println(line);
  }

  public void close() {
    writer.close();
  }

  public void writeGPX(RoutingResult rr) {
    OSMNode[]nodes = rr.getPath();
    writeLine("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
    writeLine("<gpx version=\"1.1\" creator=\"Nogivan\">");
    for(int i = 0;i<nodes.length;i++) {
      String s1 = "<wpt lat=";
      MapPoint m = nodes[i].getLocation();
      double lat = m.getLat();
      double lon = m.getLon();
      s1+="\""+lat+"\" "+ "lon=\""+lon+"\"></wpt>";
      writeLine(s1);
    }
    writeLine("</gpx>");
    close();
  }
  
  public void write(RoutingResult rr) {
    OSMNode[]nodes = rr.getPath();
    writeLine("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
    writeLine("<gpx version=\"1.1\" creator=\"Nogivan\">");
    for(int i = 0;i<nodes.length;i++) {
      String s1 = "<wpt lat=";
      MapPoint m = nodes[i].getLocation();
      double lat = m.getLat();
      double lon = m.getLon();
      s1+="\""+lat+"\" "+ "lon=\""+lon+"\"></wpt>";
      writeLine(s1);
    }
    writeLine("</gpx>");
    close();
  }

  
}
