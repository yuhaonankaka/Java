
public class HA5_7 extends MiniJava {

  private static int lines;



  public static int[] readMatrix() {

    int column = lines + 1;
    int[] matrix = new int[column * lines];
    for (int i = 0; i < lines; i++) {
      for (int j = 0; j < column; j++) {
        matrix[i * column + j] =
            read("Please type in the value of row:" + (i + 1) + " column: " + (j + 1));
      }
    }
    return matrix;
  }

  public static void printMatrix(int[] matrix) {

    int column = lines + 1;
    for (int i = 0; i < lines; i++) {
      for (int j = 0; j < column; j++) {
        writeConsole(matrix[i * column + j] + " ");
      }
      writeLineConsole("");
    }
  }


  public static int get(int[] matrix, int line, int column) {
    return matrix[(line - 1) * (lines + 1) + (column - 1)];
  }


  public static void set(int[] matrix, int line, int column, int value) {
    matrix[(line - 1) * (lines + 1) + (column - 1)] = value;
  }

  public static void multLine(int[] matrix, int line, int factor) {
    for (int i = 0; i < lines + 1; i++) {
      matrix[(lines + 1) * (line - 1) + i] = factor * get(matrix, line, i + 1);
    }
  }

  public static void multAddLine(int[] matrix, int line, int otherLine, int factor) {
    for (int i = 0; i < lines + 1; i++) {
      matrix[(lines + 1) * (line - 1) + i] =
          matrix[(lines + 1) * (line - 1) + i] + factor * matrix[(lines + 1) * (otherLine - 1) + i];
    }
  }


  public static void swap(int[] matrix, int lineA, int lineB) {
    int[] temp = new int[lines + 1];
    for (int i = 0; i < lines + 1; i++) {
      temp[i] = get(matrix, lineA, i + 1);
      set(matrix, lineA, i + 1, get(matrix, lineB, i + 1));
      set(matrix, lineB, i + 1, temp[i]);
    }
  }


  public static void searchSwap(int[] matrix, int fromLine) {
    int rowsbelow = fromLine + 1;
    while (get(matrix, fromLine, fromLine) == 0) {
      if (rowsbelow == lines + 1) {
        write("Failed to swap!There is no way to swap a right form!");
        System.exit(1);
      }
      swap(matrix, fromLine, rowsbelow);
      rowsbelow++;
    }
  }


  public static int kgv(int a, int b) {
    int kgv = 0;
    for (int i = 1; a * i <= a * b; i++) {
      if (a * i % b == 0) {
        kgv = a * i;
        break;
      }
    }
    return kgv;
  }


  public static int[] rowEchelonToResult(int[] matrix) {
    for (int i = 1; i <= lines; i++) {
      searchSwap(matrix, i);
    }
    for (int i = 1; i <= lines; i++) {

      if (i >= 2) {
        for (int j = 1; j <= i - 1; j++) {
          if (get(matrix, i, j) != 0) {
            if (get(matrix, i, j) > 0 && get(matrix, j, j) > 0) {
              multLine(matrix, j, kgv(get(matrix, i, j), get(matrix, j, j)) / get(matrix, j, j));
              multLine(matrix, i, kgv(get(matrix, i, j), get(matrix, j, j)) / get(matrix, i, j));
              multAddLine(matrix, i, j, -1);
            } else if (get(matrix, i, j) < 0 && get(matrix, j, j) > 0) {
              multLine(matrix, j, kgv((-get(matrix, i, j)), get(matrix, j, j)) / get(matrix, j, j));
              multLine(matrix, i,
                  kgv((-get(matrix, i, j)), get(matrix, j, j)) / (-get(matrix, i, j)));
              multAddLine(matrix, i, j, 1);
            } else if (get(matrix, i, j) > 0 && get(matrix, j, j) < 0) {
              multLine(matrix, j,
                  kgv(get(matrix, i, j), (-get(matrix, j, j))) / (-get(matrix, j, j)));
              multLine(matrix, i, kgv(get(matrix, i, j), (-get(matrix, j, j))) / get(matrix, i, j));
              multAddLine(matrix, i, j, 1);
            } else if (get(matrix, i, j) < 0 && get(matrix, j, j) < 0) {

              multLine(matrix, j,
                  kgv((-get(matrix, i, j)), (-get(matrix, j, j))) / (-get(matrix, j, j)));
              multLine(matrix, i,
                  kgv((-get(matrix, i, j)), (-get(matrix, j, j))) / (-get(matrix, i, j)));
              multAddLine(matrix, i, j, -1);

            }
            if (matrix[(i - 1) * (lines + 1) + i - 1] == 0) {
              searchSwap(matrix, i);
              i = i - 1;
            }
          }

        }
      }
    }
    return matrix;
  }


  public static int[] solveSystem(int[] matrix) {
    int[] x = new int[lines];
    x[0] = get(matrix, lines, lines + 1) / get(matrix, lines, lines);
    for (int i = 1; i < lines; i++) {
      int sum = 0;
      for (int j = 0; j < i; j++) {
        sum = sum + get(matrix, lines - i, lines - j) * x[j];
      }
      x[i] = (get(matrix, lines - i, lines + 1) - sum) / get(matrix, lines - i, lines - i);
    }

    int[] xresult = new int[lines];
    for (int j = 0; j < lines; j++) {
      xresult[j] = x[lines - 1 - j];
    }
    return xresult;

  }


  public static void main(String[] args) {
    // TODO Auto-generated method stub

    lines = read("Geben Sie die Anzahl der Gleichungen ein.");
    int[] matrix = readMatrix();

    rowEchelonToResult(matrix);
    printMatrix(matrix);

    int[] x = solveSystem(matrix);
    for (int i = 0; i < lines; i++) {
      writeLineConsole("x" + (i + 1) + "=" + x[i] + " ");
    }


  }

}
