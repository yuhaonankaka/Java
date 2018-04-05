
public class Schneeflocke extends Foreground{
  public Schneeflocke(int x, int y) {
    super(x,y,0,WeihnachtsElfen.FOREGROUND_SNOWFLAKE);
  }
  
  public boolean moveDown(boolean[][] staticObjects,int [][] landscape){ 
    if(staticObjects[x][y+1]!=true && landscape[x][y+1]!=1
        && landscape[x][y+1]!=2
        && landscape[x][y+1]!=3) {
      if(landscape[x][y]!=0)
        landscape[x][y]=landscape[x][y]-WeihnachtsElfen.FOREGROUND_SNOWFLAKE;
      y++;
      if(y==31 || staticObjects[x][y+1]==true) {
        staticObjects[x][y]=true;
        fallend = false;
      }
      return true; 
    }
    else {
      fallend=false;
      staticObjects[x][y]=true;
      return false;
    }
   
      
    
    };
  public int moveLeft(boolean[][] staticObjects,int [][] landscape) { 
    if(staticObjects[x][y+1]!=true && landscape[x][y+1]!=1
        && landscape[x][y+1]!=2
        && landscape[x][y+1]!=3) {
      if(landscape[x][y]!=0)
        landscape[x][y]=landscape[x][y]-WeihnachtsElfen.FOREGROUND_SNOWFLAKE;
      y++;
      if(y==31) {
        staticObjects[x][31]=true;
        fallend = false;
      }
      if(staticObjects[x][y+1]==true) {
        staticObjects[x][y]=true;
        fallend = false;
      }
      return 1; 
    }
    else if (x-1!=0 && staticObjects[x-1][y+1]!=true && landscape[x-1][y+1]!=1
        && landscape[x-1][y+1]!=2
        && landscape[x-1][y+1]!=3) {
      if(landscape[x][y]!=0)
        landscape[x][y]=landscape[x][y]-WeihnachtsElfen.FOREGROUND_SNOWFLAKE;
      x--;
      y++;
      if(y==31 || staticObjects[x][y+1]==true) {
        staticObjects[x][y]=true;
        fallend = false;
      }
      return 1;
    }
    else
    {
    staticObjects[x][y]=true;
    fallend=false;
    return -1;
      }
    };
    
    
  public int moveRight(boolean[][] staticObjects,int [][] landscape) { 
    if(staticObjects[x][y+1]!=true&& landscape[x][y+1]!=1
        && landscape[x][y+1]!=2
        && landscape[x][y+1]!=3) {
      if(landscape[x][y]!=0)
        landscape[x][y]=landscape[x][y]-WeihnachtsElfen.FOREGROUND_SNOWFLAKE;
      y++;
      if(y==31) {
        staticObjects[x][31]=true;
        fallend = false;
      }
      if(staticObjects[x][y+1]==true) {
        staticObjects[x][y]=true;
        fallend = false;
      }
      
      return 1; 
    }
    else if (x+1!=29 && staticObjects[x+1][y+1]!=true && landscape[x+1][y+1]!=1
        && landscape[x+1][y+1]!=2
        && landscape[x+1][y+1]!=3) {
      if(landscape[x][y]!=0)
        landscape[x][y]=landscape[x][y]-WeihnachtsElfen.FOREGROUND_SNOWFLAKE;
      x++;
      y++;
      if(y==31 || staticObjects[x][y+1]==true) {
        staticObjects[x][y]=true;
        fallend = false;
      }
      return 1;
    }
    else
    {
      staticObjects[x][y]=true;
      fallend=false;
      return -1;
        }
    };
    
  
  
}
