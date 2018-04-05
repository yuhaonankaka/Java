
public class HA6_7 extends MiniJava {

  public static int[] readNumber() {
    String s = readString("Please type in a number!");
    int count = 0;
    int l = s.length();
    for (int i = 0; i < l; i++) {
      if (((int) s.charAt(i) >= 48 && (int) s.charAt(i) <= 57)
          || ((int) s.charAt(i) >= 65 && (int) s.charAt(i) <= 90)) {
        count = count + 1;
      } else {
        continue;
      }
    }
    while (count != s.length()) {
      s = readString("Please type in a right number!");
      l = s.length();
      count = 0;
      for (int i = 0; i < l; i++) {
        if (((int) s.charAt(i) >= 48 && (int) s.charAt(i) <= 57)
            || ((int) s.charAt(i) >= 65 && (int) s.charAt(i) <= 90)) {
          count = count + 1;
        } else {
          continue;
        }
      }
    }
    int[] array = new int[l];
    for (int i = 0; i < l; i++) {
      if ((int) s.charAt(l - i - 1) <= 57) {
        array[i] = (int) s.charAt(l - i - 1) - 48;
      } else {
        array[i] = (int) s.charAt(l - i - 1) - 55;
      }
    }
    return array;
  }


  public static String toString(int[] number) {
    String s = "";
    for (int i = number.length - 1; i > -1; i--) {
      if (i == number.length - 1 && number[i] == 0) {
        continue;
      }
      if (number[i] <= 9) {
        s = s + (char) (number[i] + 48);
      } else {
        s = s + (char) (number[i] + 55);
      }
    }
    return s;
  }


  public static int[] add(int[] a, int[] b, int base) {
    int max = a.length > b.length ? a.length : b.length;
    int min = a.length < b.length ? a.length : b.length;
    int[] remainder = new int[max + 1];
    int[] result = new int[max + 1];
    for (int i = 0; i < min; i++) {
      if ((remainder[i] + a[i] + b[i]) / base >= 1) {
        result[i] = (a[i] + b[i] + remainder[i]) % base;
        remainder[i + 1] = 1;
      } else {
        result[i] = (a[i] + b[i] + remainder[i]) % base;
      }
    }
    if (max != min) {
      for (int i = min; i < max; i++) {
        if (max == a.length) {
          if ((remainder[i] + a[i]) / base >= 1) {
            result[i] = (a[i] + remainder[i]) % base;
            remainder[i + 1] = 1;
          } else {
            result[i] = (a[i] + remainder[i]) % base;
          }
        }


        else {
          if ((remainder[i] + b[i]) / base >= 1) {
            result[i] = (b[i] + remainder[i]) % base;
            remainder[i + 1] = 1;
          } else {
            result[i] = (b[i] + remainder[i]) % base;
          }
        }
      }
      result[max] = remainder[max];
    } else {
      result[max] = remainder[max];
    }
    return result;
  }



  public static int[] mulDigit(int[] a, int digit, int shift, int base) {
    int[] result = new int[a.length + 1 + shift];
    int[] remainder = new int[a.length + 1 + shift];
    for (int i = shift; i < a.length + shift; i++) {
      result[i] = (a[i - shift] * digit + remainder[i]) % base;
      remainder[i + 1] = (a[i - shift] * digit + remainder[i]) / base;
    }
    if (remainder[a.length + shift] != 0) {
      result[a.length + shift] = remainder[a.length + shift];
    }
    for (int i = 0; i < shift; i++) {
      result[i] = 0;
    }
    return result;
  }


  public static int[] mul(int[] a, int[] b, int base) {
    int[] temp = {0};
    for (int i = 0; i < b.length; i++) {
      temp = add(temp, mulDigit(a, b[i], i, base), base);
    }
    return temp;

  }



  public static void main(String[] args) {
    // TODO Auto-generated method stub
    int base = readInt("Please type in an Integer as Base!!");
    while (base < 2 || base > 36) {
      base = readInt("Please type in an Integer between 2 and 36!!");
    }



    int[] a = readNumber();

    boolean trigger = false;
    for (int i = 0; i < a.length; i++) {
      if (a[i] >= base) {
        trigger = true;
      }
    }
    while (trigger) {
      write("your input is not a valid number! Check it !");
      trigger = false;
      a = readNumber();
      for (int i = 0; i < a.length; i++) {
        if (a[i] >= base) {
          trigger = true;
        }
      }

    }
    int[] b = readNumber();
    for (int i = 0; i < b.length; i++) {
      if (b[i] >= base) {
        trigger = true;
      }
    }
    while (trigger) {
      write("your input is not a valid number! Check it !");
      trigger = false;
      b = readNumber();
      for (int i = 0; i < b.length; i++) {
        if (b[i] >= base) {
          trigger = true;
        }
      }

    }



    writeConsole("\\begin{tabular}{");
    for (int i = 0; i < a.length + b.length + 2; i++) {
      writeConsole("c");
    }

    writeLineConsole("}");
    for (int i = a.length - 1; i > -1; i--) {
      if (a[i] > 9) {
        writeConsole("&" + (char) (a[i] + 55));
      } else {
        writeConsole("&" + a[i]);
      }

    }
    writeConsole("&" + "$\\ast$");
    for (int i = b.length - 1; i > -1; i--) {
      if (b[i] > 9) {
        writeConsole("&" + (char) (b[i] + 55));
      } else {
        writeConsole("&" + b[i]);
      }
    }
    writeLineConsole("\\\\");
    writeLineConsole("\\hline");
    int[][] zwischen = new int[b.length][];
    for (int i = 0; i < b.length; i++) {
      zwischen[i] = mulDigit(a, b[i], i, base);
    }



    // 求每一个乘积的位数；
    int[] num = new int[b.length];

    for (int i = 0; i < b.length; i++) {
      num[i] = zwischen[i].length - 1;
    }
    for (int i = 0; i < b.length; i++) {
      while (zwischen[i][num[i]] == 0) {
        num[i]--;
      }
    }

    for (int j = 0; j < b.length; j++) {
      writeConsole("+");
      for (int i = 0; i < a.length + b.length - num[j]; i++) {
        writeConsole("&");
      }
      for (int i = num[j]; i > -1; i--) {
        if (zwischen[j][i] > 9) {
          writeConsole("&" + (char) (zwischen[j][i] + 55));
        } else {
          writeConsole("&" + zwischen[j][i]);
        }
      }
      writeLineConsole("\\\\");
    }

    writeLineConsole("\\hline");
    writeConsole("=");


    int[] result = mul(a, b, base);

    int n = result.length - 1;
    while (result[n] == 0) {
      n--;
    }

    for (int i = 0; i < a.length + b.length - n; i++) {
      writeConsole("&");
    }

    for (int i = n; i > -1; i--) {
      if (result[i] > 9) {
        writeConsole("&" + (char) (55 + result[i]));
      } else {
        writeConsole("&" + result[i]);
      }

    }

    writeLineConsole("\\\\");
    writeLineConsole("\\end{tabular}");



  }


}
