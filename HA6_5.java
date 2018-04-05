@SuppressWarnings("serial")
public class HA6_5 extends Maze {

  static int[][] maze;
  static int[] typein = typein();
  static int penguinnum = 0;
  static int length = typein[0];
  static int width = typein[1];
  static int maxDistance = typein[2];

  static int[][] direction = new int[length][width];


  public static int[] typein() {
    int typein[] = new int[3];
    int l = MiniJava.readInt("What's the length?");
    int w = MiniJava.readInt("What's the width?");
    int d = MiniJava.readInt("What's the max distance?");
    while (l <= 0 || w <= 0 || d <= 0) {
      MiniJava.write("Please type in the right numbers!!!!Let's try again!!!");
      l = MiniJava.readInt("What's the length?");
      w = MiniJava.readInt("What's the width?");
      d = MiniJava.readInt("What's the max distance?");
    }
    typein[0] = l;
    typein[1] = w;
    typein[2] = d;
    return typein;

  }


  public static int walk(int x, int y, int maxDistance) {

    if (maze[x][y] == PENGUIN) {
      penguinnum++;
    }
    maze[x][y] = PLAYER;
    draw(maze);
    maze[x][y] = OLD_PATH_ACTIVE;

    // 上

    if (makesense(x, y - 1) && lowerthanmaxdistance() && (free(x, y - 1) || penguin(x, y - 1))
        && safe(x, y - 1)) {


      direction[x][y - 1] = 1;
      walk(x, y - 1, maxDistance);
    }
    // down
    else if (makesense(x, y + 1) && lowerthanmaxdistance() && (free(x, y + 1) || penguin(x, y + 1))
        && safe(x, y + 1)) {


      direction[x][y + 1] = 2;
      walk(x, y + 1, maxDistance);
    }
    // right
    else if (makesense(x + 1, y) && lowerthanmaxdistance() && (free(x + 1, y) || penguin(x + 1, y))
        && safe(x + 1, y)) {


      direction[x + 1][y] = 3;
      walk(x + 1, y, maxDistance);
    }
    // left
    else if (makesense(x - 1, y) && lowerthanmaxdistance() && (free(x - 1, y) || penguin(x, y + 1))
        && safe(x - 1, y)) {


      direction[x - 1][y] = 4;
      walk(x - 1, y, maxDistance);
    }

    else if (direction[x][y] == 1) {
      maze[x][y] = OLD_PATH_DONE;

      walk(x, y + 1, maxDistance);
    }



    else if (direction[x][y] == 2) {
      maze[x][y] = OLD_PATH_DONE;

      walk(x, y - 1, maxDistance);
    } else if (direction[x][y] == 3) {
      maze[x][y] = OLD_PATH_DONE;

      walk(x - 1, y, maxDistance);
    } else if (direction[x][y] == 4) {
      maze[x][y] = OLD_PATH_DONE;

      walk(x + 1, y, maxDistance);
    }



    return penguinnum;

  }

  public static boolean wall(int x, int y) {
    return maze[x][y] == WALL;
  }

  public static boolean lowerthanmaxdistance() {
    int distancecount = 0;
    for (int i = 0; i < length; i++) {
      for (int j = 0; j < width; j++) {
        if (maze[i][j] == OLD_PATH_ACTIVE) {
          distancecount++;
        }

      }
    }
    return distancecount < maxDistance;
  }

  public static boolean makesense(int x, int y) {
    if (x >= 0 && (y) >= 0 && maze[x][y] != OLD_PATH_DONE && maze[x][y] != OLD_PATH_ACTIVE) {
      return true;
    } else {
      return false;
    }
  }

  public static boolean free(int x, int y) {
    return maze[x][y] == FREE;
  }

  public static boolean penguin(int x, int y) {
    return maze[x][y] == PENGUIN;
  }

  public static boolean safe(int x, int y) {
    if (wall(x - 1, y - 1) || wall(x, y - 1) || wall(x + 1, y - 1) || wall(x - 1, y)
        || wall(x + 1, y) || wall(x - 1, y + 1) || wall(x, y + 1) || wall(x + 1, y + 1)) {
      return true;
    } else {
      return false;
    }
  }


  public static void main(String[] args) {


    maze = generatePenguinMaze(length, width);



    int a = walk(1, 0, maxDistance);
    MiniJava.write("You have saved " + a + " Penguin!!!");

  }
}
