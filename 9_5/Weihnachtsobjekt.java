/*
     _   _
    ( >o< )                         _
    ,%%/'^`\%%.                      (@)                       _
    %%'V   V`%%      6               |-|\____________________ (@)
    .     %%.     ,%%     ,~.              | |                     \|-|
    |     *%%.   ,%%*     | |              |-|   Happy Holidays!    | |
    |       *%%%%%*       | |      ____    |_|\___________________  |-|
    =l-------------------l===l----(oooo)   (@)                    `\|_|
    _______________________________|--|     ~                       (@)
    ================,==============|  |                              ~
    ============,,~'')=============|  |
    -------,,~''......)------------|  |
    ('............)           |  |        ,
    (........,###            |  |        I\,
    (....,#######           | __     ,_/__ \__A
    ``###########          |/'o\,   1 @, `/ ,/    ___,
    '  ###########         |I    `-''   ,  /__,--'_,-\
    ###########         |\>--.______/  /V     /
    `    ###########          |  |   `---._  \`\==. `-.
    ##########            |  |/\,--==,.) ,\\  \==. \
    #######  ,           |  /__\    `\`-. \)  _ \=.\
    ) ,*****               |  |        (`=,`-.'/ `),`-)
    / \( /  \               |  |     __(_(  \=,`--.'
    ( {/ \ ,\ )              |  |  ,=--(__,\   i\=, `-.
    \(\6/\\//               |  |  `//'\__  `  I  \=,  `-.
    /============\             |  |   ``(__ `-._,' __ \=,   \
    ______________________________(elya)    (__,     ,'  `-\=,  .\
    \     (___,  (       )=,  ,)
    ====================================I  ,--`(_,--.`_..  /=,  ,/
    ====================================I /w_,--/w_,--.__,(==__,'
*/
public class Weihnachtsobjekt {
  protected int x, y;
  protected boolean fallend = true;
  protected boolean markedForDeath = false;

  public Weihnachtsobjekt(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void addObjektToSpielfeld(int[][] spielfeld) {
    
  };
  public boolean moveDown(boolean[][] staticObjects){ 
    return true; 
    };
    public boolean moveDown(boolean[][] staticObjects, int[][] lanscape){ 
      return true; 
    }
   
  public int moveLeft(boolean[][] staticObjects) { 
   System.out.print("doiown");
    return 1; };
  public int moveRight(boolean[][] staticObjects) { 
    
    
    return 1; }

  public int moveRight(boolean[][] staticObjects, int[][] landscape) {
    // TODO Auto-generated method stub
    return 1;
    
  }

  public int moveLeft(boolean[][] staticObjects, int[][] landscape) {
    // TODO Auto-generated method stub
    return -1;
  };
  
}
