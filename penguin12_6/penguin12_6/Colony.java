package penguin12_6;
import java.util.Random;

public class Colony extends GUI {

  private final int[][] landscape;
  public int[][] getLandscape() {
    return landscape;
  }
  private final Penguin[][] placed;
  public Penguin [][] getPenguin(){
    return placed;
  }
  public final Object[][] squareLocks;
  public final Object drawLock = new Object();


  public Colony(int width, int height,boolean standard) {
    placed = new Penguin[width][height];
    landscape = new int[placed.length][placed[0].length];
    squareLocks = new Object[placed.length][placed[0].length]; // all still null
    generateAntarctic(landscape,placed,standard);
    
    for(int i =0;i<placed.length;i++) {
      for(int k = 0;k<placed[0].length;k++) {
        squareLocks[i][k] = new Object();
        }
      }
    
    int count=0;
    for(int i =0;i<placed.length;i++) {
      for(int k = 0;k<placed[0].length;k++) {
        if(placed[i][k]!=null) {
          count++;
        }
      }
    }
    
    Thread[] penguins = new Thread[count];
    
    count = 0;
    for(int i =0;i<placed.length;i++) {
      for(int k = 0;k<placed[0].length;k++) {
        if(placed[i][k]!=null) {
          penguins[count]=new Thread(placed[i][k]);
          penguins[count].start();
          count++;
          
        }
      }
    }
    
    for(Thread t:penguins) {
      try {
        t.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    synchronized(drawLock) {
      draw(landscape);
    }
  }

  public void move(Penguin peng, int x, int y, int xNew, int yNew) {

    if(xNew>=24 || xNew<0 || yNew>=20 || yNew<0) {
      return;
    }
    synchronized(squareLocks[xNew][yNew]) {
    if(placed[xNew][yNew]!=null) {
      return;
    }
    int ox = peng.xc;
    int oy = peng.yc;
    
    if(peng.pregnant)
      peng.pregnant=false;
    peng.age++;
    placed[x][y]=null;
    placed[xNew][yNew] = peng;
    setForeground(landscape,x,y,GUI.NIXUIN);
    peng.xc = xNew;
    peng.yc = yNew;
    setForeground(landscape,xNew,yNew,peng.getFg());
    
    
    synchronized(drawLock) {
      draw(landscape);
    }
    
    
    Random r = new Random();
    int ra = r.nextInt(4);
    if((peng.xc==23 && peng.yc==19) || 
        (peng.xc==23 && peng.yc==0) ||
        (peng.xc==0 && peng.yc==19) ||
        (peng.xc==0 && peng.yc==0)) {
      if(ra==0 || ra==1) {
        peng.run = false;
        setForeground(landscape,xNew,yNew,GUI.NIXUIN);
        placed[xNew][yNew] = null;
      }
    }
    else if(peng.xc==23 || peng.yc==0 || peng.xc==0 || peng.yc==19) {
      if(ra==3) {
        peng.run = false;
        setForeground(landscape,xNew,yNew,GUI.NIXUIN);
        placed[xNew][yNew] = null;
      }
    }
    
   }
  }
}
