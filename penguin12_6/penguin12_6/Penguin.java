package penguin12_6;
import java.util.Random;

public class Penguin implements Runnable {

  public static final int adultAge = 26;
  public int age;
  private boolean female;
  private Colony colony;
  public int xc;
  public int yc;
  public volatile boolean pregnant;
  public volatile boolean run = true;

  public Penguin(boolean female, int x, int y, int age, Colony col) {
    this.age = age;
    this.female=female;
    xc = x;
    yc= y;
    pregnant = false;
    this.colony = col;
  }

  public void run() {
    
    while(run) {
    try {
      Thread.sleep((int)(200+Math.random()*(500-200+1)));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
    Random random = new Random();
    int ra = random.nextInt(4);
    System.out.println(Thread.currentThread().getName() + "  Direction: "+ra);
    int[][] land = colony.getLandscape();
    Penguin[][]penguins = colony.getPenguin();
    if(getFg()==GUI.SCHWANGUIN) {
      
        
      try {
        Thread.sleep(9000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      
      int ox = xc;
      int oy = yc;
      
      synchronized(colony.squareLocks[xc][yc]){
       
        
        if(ra==0) {
          //up
          colony.move(this,xc,yc,xc,yc-1);
        }else if(ra==1) {
          //right
          colony.move(this,xc,yc,xc+1,yc);
        }else if(ra==2) {
          //down
          colony.move(this,xc,yc,xc,yc+1);
        }else {
          //left
          colony.move(this,xc,yc,xc-1,yc);
        }
        
        
      }
      
     
        if(colony.getPenguin()[ox][oy] == null) {
          
          
          Random r2 = new Random();
          int ra2 = r2.nextInt(2);
          if(ra2==0) {
            Penguin p = new Penguin(true,ox,oy,0,colony);
            colony.getPenguin()[ox][oy] = p;
            GUI.setForeground(colony.getLandscape(),ox,oy,GUI.KLEINUININ);
            Thread t = new Thread(p);
            t.start();
            
          }
          else {
            Penguin p = new Penguin(false,ox,oy,0,colony);
            colony.getPenguin()[ox][oy] = p;
            GUI.setForeground(colony.getLandscape(),ox,oy,GUI.KLEINUIN);
            Thread t = new Thread(p);
            t.start();
            try {
              t.join();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          continue;
        }
        
       
        
      

      
      
    }
    else {
    
        
        if(getFg()==GUI.MANNUIN) {
          if(!((xc+1)>=24)) {
        
            if(colony.getPenguin()[xc+1][yc]!=null) {
              if(colony.getPenguin()[xc+1][yc].getFg()==GUI.FRAUIN) {
                Random rr = new Random();
                int r1 = random.nextInt(20);
                if(r1<=10 && !GUI.ocean(colony.getLandscape(), xc, yc)) {
                  this.pregnant=true;
                 GUI.setForeground(colony.getLandscape(), xc, yc, GUI.SCHWANGUIN);
                 continue;
                }
              }
            }
          
          }
        }
        
        else if(getFg()==GUI.FRAUIN){
          if(!((xc-1)<0)) {
           
            if(colony.getPenguin()[xc-1][yc]!=null) {
              if(colony.getPenguin()[xc-1][yc].getFg()==GUI.MANNUIN) {
                Random rr = new Random();
                int r1 = random.nextInt(20);
                if(r1>=19 && !GUI.ocean(colony.getLandscape(), xc, yc)) {
                  this.pregnant=true;
                  GUI.setForeground(colony.getLandscape(), xc, yc, GUI.SCHWANGUIN);
                  continue;
                }
              }
            }
          }
          
        }
      
        synchronized(colony.squareLocks[xc][yc]){
        
      if(ra==0) {
        //up
        colony.move(this,xc,yc,xc,yc-1);
      }else if(ra==1) {
        //right
        colony.move(this,xc,yc,xc+1,yc);
      }else if(ra==2) {
        //down
        colony.move(this,xc,yc,xc,yc+1);
      }else {
        //left
        colony.move(this,xc,yc,xc-1,yc);
      }
      }
      }
    }
  }

  public int getFg() {
    if(pregnant) {
      return GUI.SCHWANGUIN;
    }
    else if(female) {
      if(age>25)
        return GUI.FRAUIN;
      else
        return GUI.KLEINUININ;
    }
    else {
      if(age>25)
        return GUI.MANNUIN;
      else
        return GUI.KLEINUIN;
    }
  }

}
