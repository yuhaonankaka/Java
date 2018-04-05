import java.util.ArrayList;

/*
.:*~*:._.:*~*:._.:*~*:._.:*~*:._.:*~*:._.:*~*:.
    .     *                                       .
    .    /.\                                      .
    .   /..'\                                     .
    .   /'.'\                                     .
    .  /.''.'\                                    .
    .  /.'.'.\                                    .
    . /'.''.'.\                                   .
    . ^^^[_]^^^                                   .
    .                                             .
    .                                             .
    .jgs                                          .
    .:*~*:._.:*~*:._.:*~*:._.:*~*:._.:*~*:._.:*~*:.
*/

public class Weihnachtsbaum extends BitteNichtAbgeben {

  private static final int[][] landscape = generateLandscape(30, 33);
  
  private static ArrayList<Weihnachtsobjekt> objekte = new ArrayList<>();
  private static boolean[][] staticObjects = new boolean[30][33];
  private static boolean[][] staticObjects2 = new boolean[30][33];//background
  private static int phase=0;
 

  public static Weihnachtsobjekt createRandomObjekt() {
    if(phase==0) {
      //TODO create Background
      /* 在multi里的每一个single都加入一个函数，判断每一个是否可以移动，不能移动则返回false
       * 在multi中，判断的时候使用这个新的函数，而不要用那个直接会变化的函数来判断；
       * 如果false了，则static给每一个single；fallen也都false；
       */
    }
    else {
      //TODO create Foreground
    }
    return null;
    
  }
  
  public static void keyPressed(int key) {
    
    
    if(key==2) {
      phase=(phase+1)%2;
      System.out.println("Phase changed to: "+phase);     
      return;
    }
    
    if(phase==0) {
      while(objekte.isEmpty()) {
        WeihnachtsElfen.newRandomObject();
        int a = WeihnachtsElfen.randomObjects[WeihnachtsElfen.currentRandomObject][0];
        int b = WeihnachtsElfen.randomObjects[WeihnachtsElfen.currentRandomObject][1];
        if(a==0) {
          Baumstamm baum = new Baumstamm(10,1,b);
          objekte.add(baum);
        }
        else if(a==1) {
          Ast ast = new Ast(10,1,b);
          objekte.add(ast);
        }
      }
    WeihnachtsElfen.newRandomObject();
    int a = WeihnachtsElfen.randomObjects[WeihnachtsElfen.currentRandomObject][0];
    int b = WeihnachtsElfen.randomObjects[WeihnachtsElfen.currentRandomObject][1];
    if(a==0) {
      Baumstamm baum = new Baumstamm(10,1,b);
      objekte.add(baum);
    }
    else if(a==1) {
      Ast ast = new Ast(10,1,b);
      objekte.add(ast);
    }
    
    }
    
    if(phase == 1) {
      
      
      
      
      int a =(int)(1+Math.random()*(30-1+1));
      if(a==1 || a==2) {
        int r1 =(int)(1+Math.random()*(28-1+1));
        Pinguin p1 = new Pinguin(r1,1);
        objekte.add(p1);
      }
      
      
     
     
      if(a==3 || a ==4) {
        int r1 =(int)(1+Math.random()*(28-1+1));
        Weihnachtskugel p3 = new Weihnachtskugel(r1,1);
        objekte.add(p3);
      }
      
      
      
      
    }
    
   
   
      WeihnachtsElfen.sortWeihnachtsbjectsByYCoordinate(objekte);
      WeihnachtsElfen.removeMarkedForDeath(objekte);
      for(Weihnachtsobjekt w:objekte) {
        if (w instanceof MultiObject) {
         if (w.fallend==true) {
          if(key==3) {
            if(w.moveDown(staticObjects2, landscape)) {
              w.addObjektToSpielfeld(landscape);
            }
          }
          if(key==0) {
            if(w.moveLeft(staticObjects2, landscape)==1) {
              w.addObjektToSpielfeld(landscape);
            }
          }
          if(key==1) {
            if(w.moveRight(staticObjects2, landscape)==1)
              w.addObjektToSpielfeld(landscape);
            }
         }
        }
        else if(w.fallend==true) {
          if(w instanceof Foreground) {
          if(key==3) {   
            
        if(w.moveDown(staticObjects,landscape)) {
        w.addObjektToSpielfeld(landscape);
        
        }
          }
          if(key==0) {
            if(w.moveLeft(staticObjects,landscape)==1) {
            w.addObjektToSpielfeld(landscape);
            }
            
              }
          if(key==1) {
            if(w.moveRight(staticObjects,landscape)==1) {
              w.addObjektToSpielfeld(landscape);
              }
            }
          }
          
          else {
            if(key==3) {        
              if(w.moveDown(staticObjects2,landscape)) {
              w.addObjektToSpielfeld(landscape);
              }
                }
                if(key==0) {
                  if(w.moveLeft(staticObjects2,landscape)==1) {
                  w.addObjektToSpielfeld(landscape);
                  }
                  
                    }
                if(key==1) {
                  if(w.moveRight(staticObjects2,landscape)==1) {
                    w.addObjektToSpielfeld(landscape);
                    }
                  }
          }
          
        }
      }
      
      
      if(phase == 1) {
        
          int r1 =(int)(1+Math.random()*(28-1+1));
          int r2 = (int)(1+Math.random()*(30-1+1));
          if(staticObjects[r1][r2]!=true && landscape[r1][r2]!=1
              && landscape[r1][r2]!=2
              && landscape[r1][r2]!=3) {
          Schneeflocke p1 = new Schneeflocke(r1,r2);
          p1.addObjektToSpielfeld(landscape);
          objekte.add(p1);
          }
        
      }
      draw(landscape);
      
      if (objekte.size()>400) {
        System.out.println("Congratulations! Enough objects!!! Gameover!!");
        System.exit(0);
      }
     
    if (key != WeihnachtsElfen.NO_KEY) {
      System.out.println(key);
    }
    
  }

  /*********************************************/
  /* Ab hier soll nichts mehr geändert werden! */
  /*********************************************/

  public static void main(String[] args) {
    
    draw(landscape);
    handleUserInput();
  }

  private static void handleUserInput() {
    while(true) {
      try {
        Thread.sleep(10);
      } catch (InterruptedException ie) {
        /* Intentionally left blank */
      }
      int step = nextStep();
      if (step != NO_KEY) {
        // System.out.print(step+",");
        keyPressed(step);
      }
    }
  }
}
