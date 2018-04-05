
public class HA6_6 extends MiniJava {



  public static int[] quadraticFormula(double[] coefficients) {
    int[] solution;
    double delta = Math.pow(coefficients[1], 2) - 4 * coefficients[0] * coefficients[2];
    if (delta > 0) {
      solution = new int[2];
      solution[0] = (int) ((Math.sqrt(delta) - coefficients[1]) / (2 * coefficients[0]));
      solution[1] = (int) ((-Math.sqrt(delta) - coefficients[1]) / (2 * coefficients[0]));
    } else if (delta == 0) {
      solution = new int[1];
      solution[0] = (int) (-coefficients[1] / (2 * coefficients[0]));
    } else {
      solution = new int[0];
    }
    return solution;
  }


  public static double[] hornerSchema(double[] coefficients, int x0) {
    double[] reduco = new double[3];
    reduco[0] = coefficients[0];
    reduco[1] = coefficients[1] + reduco[0] * x0;
    reduco[2] = coefficients[2] + reduco[1] * x0;
    return reduco;
  }

  public static double calculateY(double[] coefficients, int x) {
    double y = 0;
    for (int i = 0; i < coefficients.length; i++) {
      y = y + coefficients[i] * Math.pow(x, coefficients.length - 1 - i);
    }
    return y;
  }

  public static int[] findIntervalRecursive(double[] coefficients, int a, int b, int factor) {


    if (a >= b || a >= 0 || b <= 0) {
      write("the Interval is wrong!!!!The program will be end!!");
      System.exit(-1);

    }

    if (calculateY(coefficients, a) * calculateY(coefficients, b) < 0) {
      int[] interval = {a, b};
      return interval;

    } else {
      return findIntervalRecursive(coefficients, a * factor, b * factor, factor);
    }

  }

  public static int findRootRecursive(double[] coefficients, int a, int b) {
    int m = (a + b) / 2;
    if (calculateY(coefficients, a) == 0) {
      return a;
    } else if (calculateY(coefficients, b) == 0) {
      return b;
    } else if (calculateY(coefficients, m) == 0) {
      return m;
    }


    else if ((calculateY(coefficients, m) * calculateY(coefficients, a)) < 0) {
      return findRootRecursive(coefficients, a, m);
    } else if ((calculateY(coefficients, m) * calculateY(coefficients, b)) < 0) {
      return findRootRecursive(coefficients, m, b);
    } else {
      write("There must be something wrong!");
      return -1;
    }


  }


  public static void main(String[] args) {
    // TODO Auto-generated method stub
    double[] coefficients = new double[4];
    coefficients[0] = readDouble("Please type in a3");
    coefficients[1] = readDouble("Please type in a2");
    coefficients[2] = readDouble("Please type in a1");
    coefficients[3] = readDouble("Please type in a0");
    int[] interval = new int[2];
    interval = findIntervalRecursive(coefficients, -2, 2, 10);
    int x;
    x = findRootRecursive(coefficients, interval[0], interval[1]);
    double[] coefficientsd = new double[3];
    coefficientsd = hornerSchema(coefficients, x);
    int[] solution = new int[2];
    solution = quadraticFormula(coefficientsd);
    if (solution.length == 2) {
      write("The result is x1 = " + solution[0]);
      write("The result is x2 = " + solution[1]);
      write("The result is x3 = " + x);
    } else if (solution.length == 1) {
      write("The result is x1 = " + solution[0]);
      write("The result is x2 = " + x);
    } else {
      write("The result is x1 = " + x);
    }



  }

}
