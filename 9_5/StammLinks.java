
public class StammLinks extends Background{
  public StammLinks(int x, int y) {
    super(x,y,WeihnachtsElfen.BACKGROUND_TRUNK_LEFT,0);
  }
  
  
  @Override
  public boolean moveDown(boolean[][] staticObjects,int [][] landscape){
    if(staticObjects[x][y+1]!=true) {
      if(landscape[x][y]!=0)
        landscape[x][y]=landscape[x][y]-WeihnachtsElfen.BACKGROUND_TRUNK_LEFT;
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
  
  @Override
  public int moveLeft(boolean[][] staticObjects,int [][] landscape) { 
    if((x-1)==0) {
      markedForDeath = true;
      fallend = false;
      if(landscape[x][y]!=0)
        landscape[x][y]=landscape[x][y]-WeihnachtsElfen.BACKGROUND_TRUNK_LEFT;
      x--;
      return -1;
      }
    if(staticObjects[x-1][y+1]!=true) {
      if(landscape[x][y]!=0)
        landscape[x][y]=landscape[x][y]-WeihnachtsElfen.BACKGROUND_TRUNK_LEFT;
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
      fallend = false;
      return -1; 
    }
    };
    
    @Override
    public int moveRight(boolean[][] staticObjects,int [][] landscape) { 
      if((x+1)==29) {
        markedForDeath = true;
        fallend = false;
        if(landscape[x][y]!=0)
          landscape[x][y]=landscape[x][y]-WeihnachtsElfen.BACKGROUND_TRUNK_LEFT;
        x++;
        return -1;
        }
      if(staticObjects[x+1][y+1]!=true) {
        if(landscape[x][y]!=0)
          landscape[x][y]=landscape[x][y]-WeihnachtsElfen.BACKGROUND_TRUNK_LEFT;
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
        fallend = false;
        return -1; 
      }
      };
  
  
}
