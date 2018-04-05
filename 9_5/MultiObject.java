import java.util.LinkedList;
import java.util.List;

public abstract class MultiObject extends Weihnachtsobjekt {

  protected List<SingleObject> parts;
  protected int breite;

  public MultiObject(int x, int y, int breite) {
    super(x, y);
    this.breite = breite;
    this.parts = new LinkedList<>();
  }

  @Override
  public void addObjektToSpielfeld(int[][] spielfeld) {
    for(SingleObject s:parts){  
      spielfeld[s.x][s.y]=spielfeld[s.x][s.y]+s.background+s.foreground;
    }
      
    }
  
  
  public boolean moveDown(boolean[][] staticObjects,int [][] landscape){ 
    int b = 0;
     for(SingleObject s:parts) {
       if(s.fallend==false) {
         b = 2;
       }
     }
     if(b==2) {
       for(SingleObject s:parts) {
         s.fallend=false;
         staticObjects[s.x][s.y]=true;
       }
       super.fallend=false;
       return false;
     }
     
     else {
       int con=0;
       for(SingleObject s:parts) {
         s.moveDown(staticObjects, landscape);
         if(s.fallend==false) {
           this.fallend=false;
           con=1;
          
         }
         
           
       }
       if(con==1) {
         for(SingleObject s:parts) {
         staticObjects[s.x][s.y]=true;
         }
       }
       return true;
     }
         
       
      };
     
   public int moveLeft(boolean[][] staticObjects,int [][] landscape) { 
     boolean b=false;
     for(SingleObject s:parts) {
       if(staticObjects[s.x-1][s.y+1]==true) {
         b=true;
       }
     }
     if(b==true)
       return -1;
     else {
       for(SingleObject s:parts) {
         if((s.x-1)==0) 
           b=true;
         
       } 
       if(b==true) {
         for(SingleObject s:parts) {
           s.moveLeft(staticObjects, landscape);
           s.fallend=false;
           s.markedForDeath=true;
           super.markedForDeath=true;
           
           
         }
         return -1;
       }
       else {
         int con=0;
         for(SingleObject s:parts) {
           s.moveLeft(staticObjects, landscape);
           if(s.fallend==false) {
             this.fallend=false;
            con=1;
            
           }
         }
         if(con==1) {
         for(SingleObject s:parts) {
           staticObjects[s.x][s.y]=true;
         }
         }
         return 1;
       }
     }
    
   };
   
   
   public int moveRight(boolean[][] staticObjects,int [][] landscape) { 
     boolean b=false;
     for(SingleObject s:parts) {
       if(staticObjects[s.x+1][s.y+1]==true) {
         b=true;
       }
     }
     if(b==true)
       return -1;
     
     else {
       for(SingleObject s:parts) {
         if((s.x+1)==29) 
           b=true;
         
       } 
       if(b==true) {
         for(SingleObject s:parts) {
           s.moveRight(staticObjects, landscape);
           s.fallend=false;
           s.markedForDeath=true;
           super.markedForDeath=true;
           
           
         }
         return -1;
       }
       else {
         int con=0;
         for(SingleObject s:parts) {
           s.moveRight(staticObjects, landscape);
           if(s.fallend==false) {
             this.fallend=false;
            con=1;
            
           }
         }
         if(con==1) {
         for(SingleObject s:parts) {
           staticObjects[s.x][s.y]=true;
         }
         }
         return 1;
       }
     }
    
   };
   
   
  
  
  @Override
  public String toString() {
    return "MultiObject{" +
        "parts=" + parts +
        ", breite=" + breite +
        '}';
  }
}
