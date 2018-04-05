public class HilfPingu extends PenguinPen {
  
  static int height=24,width =17;
  private static final int[][] penguinPen = generatePenguinPen(height, width);
  static int x=1,y=0; //player position
  static int count=0; //Penguin Number saved
  
  
  
  
  public static void penguinmove(){
    penguin2();
    penguin3();
    penguin4();
    penguin5();
    
    
    
  }
  
  public static void penguin2() {
    int [][] penguins = new int [height][width];
    for (int i = 0; i < height;i++) {
      for (int j = 0; j < width; j++) {
        penguins[i][j] = penguinPen[i][j];
      }
    }
    
    for (int i = 0; i < height;i++) {
      for (int j = 0; j < width; j++) {
        if (penguins[i][j]==PENGUIN_OOI) {
          int number = (int) (4*Math.random());
          switch (number) {
            case 0:
              System.out.print(i+" "+j+" go left\n");
              if (penguinPen[i-1][j]==FREE) {
                penguinPen[i][j]=FREE;
                penguinPen[i-1][j]=PENGUIN_OOI;                
              }
              else {
                System.out.print(i+" "+j+" go left,but blocked\n");
              }
              break;
              
            case 1:
              System.out.print(i+" "+j+" go right\n");
              if (penguinPen[i+1][j]==FREE) {
                penguinPen[i][j]=FREE;
                penguinPen[i+1][j]=PENGUIN_OOI;                
              }
              else {
                System.out.print(i+" "+j+" go right,but blocked\n");
              }
              break; 
              
            case 2:
              System.out.print(i+" "+j+" go down\n");
              if (penguinPen[i][j+1]==FREE) {
                penguinPen[i][j]=FREE;
                penguinPen[i][j+1]=PENGUIN_OOI;                
              }
              else {
                System.out.print(i+" "+j+" go down,but blocked\n");
              }
              break;
              
            case 3:
              System.out.print(i+" "+j+" go up\n");
              if (penguinPen[i][j-1]==FREE) {
                penguinPen[i][j]=FREE;
                penguinPen[i][j-1]=PENGUIN_OOI;                
              }
              else {
                System.out.print(i+" "+j+" go up,but blocked \n");
              }
              break;
                          
          }
          
          
        }
      }
    }
    
    
  }
  
  
  
  public static void penguin4() {
    int [][] penguins = new int [height][width];
    for (int i = 0; i < height;i++) {
      for (int j = 0; j < width; j++) {
        penguins[i][j] = penguinPen[i][j];
      }
    }
    
    for (int i = 0; i < height;i++) {
      for (int j = 0; j < width; j++) {
        if (penguins[i][j]==PENGUIN_OII) {
          int number1 = (int) (height*Math.random());
          int number2 = (int) (width*Math.random());
          if (penguinPen[number1][number2]==FREE) {
            System.out.print(i+" "+j+" go to "+number1+" "+number2+"\n");
            penguinPen[i][j]=FREE;
            penguinPen[number1][number2]=PENGUIN_OII;
          }
          else {
            System.out.print(i+" "+j+"wants to go to "+number1+" "+number2+"but it's blocked\n");
          }
        }
      }
    }
    
  }
  
  
  public static void penguin5() {
    int [][] penguins = new int [height][width];
    for (int i = 0; i < height;i++) {
      for (int j = 0; j < width; j++) {
        penguins[i][j] = penguinPen[i][j];
      }
    }
    
    for (int i = 0; i < height;i++) {
      
    
      for (int j = 0; j < width; j++) {
        
      
        if (penguins[i][j]==PENGUIN_IOO) {
          int leftdistance = Math.abs(i-1-x)+Math.abs(j-y);
          int rightdistance = Math.abs(i+1-x)+Math.abs(j-y);
          int updistance = Math.abs(i-x)+Math.abs(j-y+1);
          int downdistance = Math.abs(i-x)+Math.abs(j-y-1);
          //写出来max的，然后去max的位置，如果有多个max，随意选择一个走。
          int max = Math.max(leftdistance,Math.max(rightdistance,Math.max(updistance,downdistance)));
          if (max == leftdistance && penguinPen[i-1][j]==FREE){
            penguinPen[i][j]=FREE;
            penguinPen[i-1][j]=PENGUIN_IOO;
            System.out.print(i+" "+j+" go left\n");
          }
          else if (max == rightdistance && penguinPen[i+1][j]==FREE){
            penguinPen[i][j]=FREE;
            penguinPen[i+1][j]=PENGUIN_IOO;
            System.out.print(i+" "+j+" go right\n");
          }
          else if (max == updistance && penguinPen[i][j-1]==FREE){
            penguinPen[i][j]=FREE;
            penguinPen[i][j-1]=PENGUIN_IOO;
            System.out.print(i+" "+j+" go  up\n");
          }
          else if (max == downdistance && penguinPen[i][j+1]==FREE){
            penguinPen[i][j]=FREE;
            penguinPen[i][j+1]=PENGUIN_IOO;
            System.out.print(i+" "+j+" go  down\n");
          }
          
          
          }    
        }
      }
  }
  
  
  static int [][] RHR = new int [height][width];// to see if need to use RHR;
  static int [][] direction = new int [height][width];
  
 
  //上0 左1 下2 右3
  
  public static void penguin3() {
    int [][] penguins = new int [height][width];
    for (int i = 0; i < height;i++) {
      for (int j = 0; j < width; j++) {
        penguins[i][j] = penguinPen[i][j];
      }
    }
    
    for (int i = 0; i < height;i++) {
      for (int j = 0; j < width; j++) {
        if (penguins[i][j]==PENGUIN_OIO) {
          if (i==1 && j==0) {
            direction[i][j]=2;
            System.out.print("penguin 3: Here is Eingang, turn back!");
            
          }
          if (RHR[i][j]!=99999) {
          if (penguinPen[i+1][j]==FREE) {
            penguinPen[i][j]=FREE;
            penguinPen[i+1][j]=PENGUIN_OIO;
            System.out.println(i +"  "+j +" go directly right to " + (i+1)+"  "+ j);
          }
          else if (penguinPen[i+1][j]==WALL) {
            RHR[i][j]=99999;
            direction[i][j] = 0; 
            System.out.println(i + "  "+j+ " have a direction 0");
          }
          else {
            System.out.println(i+" "+j+" there is another penguin blocks him");
          }
          }
          else {//已经开始实行RHR准则
            if (direction[i][j] == 0 && penguinPen[i+1][j]==WALL || direction[i][j] == 1 && penguinPen[i][j-1]==WALL
                || direction[i][j] == 2 && penguinPen[i-1][j]==WALL || direction[i][j] == 3 && penguinPen[i][j+1]==WALL) {
              if (direction[i][j] == 0 && penguinPen[i][j-1]!=FREE || direction[i][j] == 1 && penguinPen[i-1][j]!=FREE
                  || direction[i][j] == 2 && penguinPen[i][j+1]!=FREE || direction[i][j] == 3 && penguinPen[i+1][j]!=FREE) {
                if (direction[i][j] == 0 && penguinPen[i][j-1]==WALL || direction[i][j] == 1 && penguinPen[i-1][j]==WALL
                    ||direction[i][j] == 2 && penguinPen[i][j+1]==WALL|| direction[i][j] == 3 && penguinPen[i+1][j]==WALL) {
                  direction [i][j]=(direction[i][j] + 1) % 4;
                  System.out.println(i+"  "+j+" change direction to " +direction[i][j]);
                }
              }
              else if (direction[i][j] == 0 && penguinPen[i][j-1]==FREE || direction[i][j] == 1 && penguinPen[i-1][j]==FREE
                  || direction[i][j] == 2 && penguinPen[i][j+1]==FREE || direction[i][j] == 3 && penguinPen[i+1][j]==FREE){
                switch (direction[i][j]) {
                  case 0:
                    penguinPen[i][j]=FREE;
                    penguinPen[i][j-1]=PENGUIN_OIO;
                    RHR[i][j]=0;
                    RHR[i][j-1]=99999;
                    direction[i][j]=-1;
                    direction[i][j-1]=0;
                    System.out.println(i+"  "+j+" go up to " + i + " "+ (j-1));
                    System.out.println(i+"  "+j+"'s Direction=" + direction[i][j]);
                    System.out.println(i+"  "+(j-1)+"'s Direction=" + direction[i][j-1]);
                    break;
                    
                  case 1:
                    penguinPen[i][j]=FREE;
                    penguinPen[i-1][j]=PENGUIN_OIO;
                    RHR[i][j]=0;
                    RHR[i-1][j]=99999;
                    direction[i][j]=-1;
                    direction[i-1][j]=1;   
                    System.out.println(i+"  "+j+" go left to " + (i-1) + " "+ (j));
                    System.out.println(i+"  "+j+"'s Direction=" + direction[i][j]);
                    System.out.println((i-1)+"  "+(j)+"'s Direction=" + direction[i-1][j]);
                    break;
                  case 2:
                    penguinPen[i][j]=FREE;
                    penguinPen[i][j+1]=PENGUIN_OIO;
                    RHR[i][j]=0;
                    RHR[i][j+1]=99999;
                    direction[i][j]=-1;
                    direction[i][j+1]=2;  
                    System.out.println(i+"  "+j+" go down to " + i + " "+ (j+1));
                    System.out.println(i+"  "+j+"'s Direction=" + direction[i][j]);
                    System.out.println(i+"  "+(j+1)+"'s Direction=" + direction[i][j+1]);
                    break;
                  case 3:
                  
                    penguinPen[i][j]=FREE;
                    penguinPen[i+1][j]=PENGUIN_OIO;
                    RHR[i][j]=0;
                    RHR[i+1][j]=99999;
                    direction[i][j]=-1;
                    direction[i+1][j]=3;  
                    System.out.println(i+"  "+j+" go right to " + (i+1) + " "+ j);
                    System.out.println(i+"  "+j+"'s Direction=" + direction[i][j]);
                    System.out.println((i+1)+"  "+(j)+"'s Direction=" + direction[i+1][j]);
                    break;
              }
              // 使用右手准则往前走，记住，如果走路了，那么就把之前位置的direction删掉，把之前的RHR标记也删掉，加入新的direction和RHR标记。
               
            }
            
          }
          
            else if (direction[i][j] == 0 && penguinPen[i+1][j]==FREE || direction[i][j] == 1 && penguinPen[i][j-1]==FREE
                || direction[i][j] == 2 && penguinPen[i-1][j]==FREE || direction[i][j] == 3 && penguinPen[i][j+1]==FREE){
              switch (direction[i][j]) {
                case 0: //up
                  penguinPen[i][j]=FREE;
                  penguinPen[i+1][j]=PENGUIN_OIO;
                  RHR[i][j]=0;
                  RHR[i+1][j]=99999;
                  direction[i+1][j]=(direction[i][j] + 3) % 4;
                  direction[i][j]=-1;
                  
                  System.out.println("no wall!" + i+"  "+j+" go to right side" + (i+1)+ " "+ j);
                  System.out.println(i+"  "+j+"'s Direction=" + direction[i][j]);
                  System.out.println((i+1)+"  "+(j)+"'s Direction=" + direction[i+1][j]);
                  break;
                case 1://left
                  penguinPen[i][j]=FREE;
                  penguinPen[i][j-1]=PENGUIN_OIO;
                  RHR[i][j]=0;
                  RHR[i][j-1]=99999;
                  direction[i][j-1]=(direction[i][j] + 3) % 4;
                  direction[i][j]=-1;
                  
                  System.out.println("no wall!" + i+"  "+j+" go to right side" + (i)+ " "+ (j-1));
                  System.out.println(i+"  "+j+"'s Direction=" + direction[i][j]);
                  System.out.println(i+"  "+(j-1)+"'s Direction=" + direction[i][j-1]);
                  break;
                case 2://down
                  penguinPen[i][j]=FREE;
                  penguinPen[i-1][j]=PENGUIN_OIO;
                  RHR[i][j]=0;
                  RHR[i-1][j]=99999;
                  direction[i-1][j]=(direction[i][j] + 3) % 4;
                  direction[i][j]=-1;
                  
                  System.out.println("no wall!" + i+"  "+j+" go to right side" + (i-1)+ " "+ j);
                  System.out.println(i+"  "+j+"'s Direction=" + direction[i][j]);
                  System.out.println((i-1)+"  "+(j)+"'s Direction=" + direction[i-1][j]);
                  break;
                case 3://right
                
                  penguinPen[i][j]=FREE;
                  penguinPen[i][j+1]=PENGUIN_OIO;
                  RHR[i][j]=0;
                  RHR[i][j+1]=99999;
                  direction[i][j+1]=(direction[i][j] + 3) % 4;
                  direction[i][j]=-1;
                  System.out.println("no wall!" + i+"  "+j+" go to right side" + (i)+ " "+ (j+1));
                  System.out.println(i+"  "+j+"'s Direction=" + direction[i][j]);
                  System.out.println(i+"  "+(j+1)+"'s Direction=" + direction[i][j+1]);
                  break;
              }
            }
            else {
              System.out.println(i+" "+j+" This Penguin wants to go right, but there is another penguin on the right");
            }
          
        }
          
        }
        
       }
      }
    
    
    
    
    
  }
  
  
  
  
  public static boolean epenguin(int x, int y) { //check if there is penguin
    return penguinPen[x][y] == PENGUIN_OOO 
        || penguinPen[x][y] ==PENGUIN_OOI 
        || penguinPen[x][y] ==PENGUIN_OIO 
        || penguinPen[x][y] ==PENGUIN_OII 
        || penguinPen[x][y] ==PENGUIN_IOO;
  }
  
  public static void savepenguin(int x, int y) { 
    if (x==1 && y==0) {
      if (epenguin(x,y+1)) {
        penguinPen[x][y+1]=FREE;
        count++;
      } 
    }
    
    else {if (epenguin(x-1,y)) {
      penguinPen[x-1][y]=FREE;
      count++;
    }
    if (epenguin(x+1,y)) {
      penguinPen[x+1][y]=FREE;
      count++;
    }
    if (epenguin(x,y+1)) {
      penguinPen[x][y+1]=FREE;
      count++;
    }
    if (epenguin(x,y-1)) {
      penguinPen[x][y-1]=FREE;
      count++;
    }
    if (epenguin(x+1,y+1)) {
      penguinPen[x+1][y+1]=FREE;
      count++;
    }
    if (epenguin(x+1,y-1)) {
      penguinPen[x+1][y-1]=FREE;
      count++;
    }
    if (epenguin(x-1,y+1)) {
      penguinPen[x-1][y+1]=FREE;
      count++;
    }
    if (epenguin(x-1,y-1)) {
      penguinPen[x-1][y-1]=FREE;
      count++;
    }
    }
    
    
    
  }
  
  
  public static void move(int direction) {
    
    savepenguin(x,y);
    penguinmove();
    System.out.println(direction);
    if (direction==MOVE_LEFT && penguinPen[x-1][y] != WALL) {
      if (x-1==1&& y==0) {
        penguinPen[x][y]=FREE;
        x=x-1;
        
      
        penguinPen[x][y]=ZOOKEEPER;
        draw(penguinPen);
        MiniJava.write("You have saved "+count+" Penguins!");
        System.exit(0);
      }
      penguinPen[x][y]=FREE;
      x=x-1;
      
      penguinPen[x][y]=ZOOKEEPER; 
      
      
      draw(penguinPen);
    }
    else if (direction==MOVE_RIGHT && penguinPen[x+1][y] != WALL) {
      if (x+1==1&& y==0) {
        penguinPen[x][y]=FREE;
        x=x+1;
        
       
       
        penguinPen[x][y]=ZOOKEEPER;
        draw(penguinPen);
        MiniJava.write("You have saved "+count+" Penguins!");
        System.exit(0);
      }
      penguinPen[x][y]=FREE;
      x=x+1;
      
     
      
      penguinPen[x][y]=ZOOKEEPER;
      
      draw(penguinPen);
    }
    else if (direction==MOVE_UP && (x!=1 || y!=0) && penguinPen[x][y-1] != WALL ) {
      
      if (x==1&& y-1==0) {
        penguinPen[x][y]=FREE;
        y=y-1;
        
      
       
        penguinPen[x][y]=ZOOKEEPER;
        draw(penguinPen);
        MiniJava.write("You have saved "+count+" Penguins!");
        System.exit(0);
      }
      
      penguinPen[x][y]=FREE;
      y=y-1;
      
      
      
      penguinPen[x][y]=ZOOKEEPER;
      
      draw(penguinPen);
    }
    else if (direction==MOVE_DOWN && penguinPen[x][y+1] != WALL) {
      if (x==1 && y+1==0) {
        penguinPen[x][y]=FREE;
        y=y+1;
       
       
        penguinPen[x][y]=ZOOKEEPER;
        draw(penguinPen);
        MiniJava.write("You have saved "+count+" Penguins!");
        System.exit(0);
      }
      
      penguinPen[x][y]=FREE;
      y=y+1;
      
     
      penguinPen[x][y]=ZOOKEEPER;
      
      draw(penguinPen);
    }
    else {
      draw(penguinPen);
      System.out.print("It's a wall! you must go somewhere else!!!");
    }
  }








  /*********************************************/
  /* Ab hier soll nichts mehr geändert werden! */
  /*********************************************/

  public static void main(String[] args) {
    draw(penguinPen);
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
      if (step != NO_MOVE) {
        // System.out.print(step+",");
        move(step);
      }
    }
  }
}
