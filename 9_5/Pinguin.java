
public class Pinguin extends Foreground{
  public Pinguin(int x, int y) {
    super(x,y,0,WeihnachtsElfen.FOREGROUND_PENGUIN);
  }
  
  @Override
  public void addObjektToSpielfeld(int[][] spielfeld) {
      spielfeld[x][y]=spielfeld[x][y]+background+foreground;
     
    }
  
  public boolean moveDown(boolean[][] staticObjects, int [][] landscape){
    if(staticObjects[x][y+1]!=true) {
      if(landscape[x][y]==3+WeihnachtsElfen.BACKGROUND_GREEN_MIDDLE ||
          landscape[x][y]==3+WeihnachtsElfen.BACKGROUND_GREEN_RIGHT ||
          landscape[x][y]==3+WeihnachtsElfen.BACKGROUND_GREEN_LEFT)
      {
        if(landscape[x][y+1]!=WeihnachtsElfen.BACKGROUND_GREEN_MIDDLE &&
            landscape[x][y+1]!=WeihnachtsElfen.BACKGROUND_GREEN_RIGHT &&
            landscape[x][y+1]!=WeihnachtsElfen.BACKGROUND_GREEN_LEFT) {
          fallend=false;
          staticObjects[x][y]=true;
          return false;
        }
      }
      if(landscape[x][y]!=0)
        landscape[x][y]=landscape[x][y]-WeihnachtsElfen.FOREGROUND_PENGUIN;
      y++;
      if(y==31 || staticObjects[x][y+1]==true) {
        staticObjects[x][y]=true;
        fallend = false;
      }
      
      return true;
    }
    else {
      staticObjects[x][y]=true;
      fallend = false;
      return false; 
    }
    
  }
  
  public int moveLeft(boolean[][] staticObjects,int [][] landscape) { 
    if((x-1)==0) {
      markedForDeath = true;
      fallend = false;
      if(landscape[x][y]!=0)
        landscape[x][y]=landscape[x][y]-WeihnachtsElfen.FOREGROUND_PENGUIN;
      x--;
      
      return -1;
      }
    if(staticObjects[x-1][y+1]!=true && landscape[x-1][y+1]!=1) {
      if(landscape[x][y]==3+WeihnachtsElfen.BACKGROUND_GREEN_MIDDLE ||
          landscape[x][y]==3+WeihnachtsElfen.BACKGROUND_GREEN_RIGHT ||
          landscape[x][y]==3+WeihnachtsElfen.BACKGROUND_GREEN_LEFT)
      {
        if(landscape[x-1][y+1]!=WeihnachtsElfen.BACKGROUND_GREEN_MIDDLE &&
            landscape[x-1][y+1]!=WeihnachtsElfen.BACKGROUND_GREEN_RIGHT &&
            landscape[x-1][y+1]!=WeihnachtsElfen.BACKGROUND_GREEN_LEFT) {
          fallend=false;
          staticObjects[x][y]=true;
          return -1;
        }
      }
      if(landscape[x][y]!=0)
        landscape[x][y]=landscape[x][y]-WeihnachtsElfen.FOREGROUND_PENGUIN;
      staticObjects[x][y]=false;
      x--;
      y++;
      if(y==31 || staticObjects[x][y+1]==true) {
        fallend = false;
        staticObjects[x][y]=true;
      }
      return 1;
    }
    else {
      System.out.println("Can't move");
      staticObjects[x][y]=true;
      //fallend = false;
      
      return -1;
    }
    };
    
    
    public int moveRight(boolean[][] staticObjects,int [][] landscape) { 
      if((x+1)==29) {
        markedForDeath = true;
        fallend = false;
        if(landscape[x][y]!=0)
          landscape[x][y]=landscape[x][y]-WeihnachtsElfen.FOREGROUND_PENGUIN;
        x++;
        return -1;
        }
      if(staticObjects[x+1][y+1]!=true && landscape[x+1][y+1]!=1) {
        if(landscape[x][y]==3+WeihnachtsElfen.BACKGROUND_GREEN_MIDDLE ||
            landscape[x][y]==3+WeihnachtsElfen.BACKGROUND_GREEN_RIGHT ||
            landscape[x][y]==3+WeihnachtsElfen.BACKGROUND_GREEN_LEFT)
        {
          if(landscape[x+1][y+1]!=WeihnachtsElfen.BACKGROUND_GREEN_MIDDLE &&
              landscape[x+1][y+1]!=WeihnachtsElfen.BACKGROUND_GREEN_RIGHT &&
              landscape[x+1][y+1]!=WeihnachtsElfen.BACKGROUND_GREEN_LEFT) {
            fallend=false;
            staticObjects[x][y]=true;
            return -1;
          }
        }
        if(landscape[x][y]!=0)
          landscape[x][y]=landscape[x][y]-WeihnachtsElfen.FOREGROUND_PENGUIN;
        staticObjects[x][y]=false;
        x++;
        y++;
        if(y==31 || staticObjects[x][y+1]==true) {
          fallend = false;
          staticObjects[x][y]=true;
        }
        return 1;
      }
      else {
        System.out.println("Can't move");
        staticObjects[x][y]=true;
        //fallend = false;
        return -1;
      }
      };
    
  
}
