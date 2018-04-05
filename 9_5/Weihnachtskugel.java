
public class Weihnachtskugel extends Foreground{
  public Weihnachtskugel(int x, int y) {
    super(x,y,0,WeihnachtsElfen.FOREGROUND_BAUBLE);
  }
  
  public boolean moveDown(boolean[][] staticObjects, int [][] landscape){
    if(staticObjects[x][y+1]!=true) {
      if(landscape[x][y]==2+WeihnachtsElfen.BACKGROUND_GREEN_MIDDLE ||
          landscape[x][y]==2+WeihnachtsElfen.BACKGROUND_GREEN_RIGHT ||
          landscape[x][y]==2+WeihnachtsElfen.BACKGROUND_GREEN_LEFT)
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
        landscape[x][y]=landscape[x][y]-WeihnachtsElfen.FOREGROUND_BAUBLE;
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
        landscape[x][y]=landscape[x][y]-WeihnachtsElfen.FOREGROUND_BAUBLE;
      x--;
      return -1;
      }
    if(staticObjects[x-1][y+1]!=true && landscape[x-1][y+1]!=1) {
      if(landscape[x][y]==2+WeihnachtsElfen.BACKGROUND_GREEN_MIDDLE ||
          landscape[x][y]==2+WeihnachtsElfen.BACKGROUND_GREEN_RIGHT ||
          landscape[x][y]==2+WeihnachtsElfen.BACKGROUND_GREEN_LEFT)
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
        landscape[x][y]=landscape[x][y]-WeihnachtsElfen.FOREGROUND_BAUBLE;
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
          landscape[x][y]=landscape[x][y]-WeihnachtsElfen.FOREGROUND_BAUBLE;
        x++;
        return -1;
        }
      if(staticObjects[x+1][y+1]!=true && landscape[x-1][y+1]!=1) {
        if(landscape[x][y]==2+WeihnachtsElfen.BACKGROUND_GREEN_MIDDLE ||
            landscape[x][y]==2+WeihnachtsElfen.BACKGROUND_GREEN_RIGHT ||
            landscape[x][y]==2+WeihnachtsElfen.BACKGROUND_GREEN_LEFT)
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
          landscape[x][y]=landscape[x][y]-WeihnachtsElfen.FOREGROUND_BAUBLE;
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
        staticObjects[x][y]=true;
       // fallend = false;
        return -1;
      }
      };
      
}
