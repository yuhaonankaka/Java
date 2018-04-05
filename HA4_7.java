
public class HA4_7 extends MiniJava {

  public static void main(String[] args) {
    String s;
    s = readString("Please type in a word!");

    boolean wrong = true;

    while (wrong) {
      int count = 0;
      for (int i = 0; i < s.length(); i++) {
        if ((s.charAt(i) >= 'a' && s.charAt(i) <= 'z')
            || (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')) {
          count = count + 1;
        }
      }
      if (count == s.length()) {
        wrong = false;
      } else {
        s = readString("It's not a word!Please type in a word!");
      }
    }

    boolean cond = true;
    String p = readString("Please type in some other words");
    while (cond) {

      if (p.equals("")) {
        cond = false;
        break;
      }
      int count = 0;
      for (int i = 0; i < p.length(); i++) {
        if ((p.charAt(i) >= 'a' && p.charAt(i) <= 'z')
            || (p.charAt(i) >= 'A' && p.charAt(i) <= 'Z')) {
          count = count + 1;
        }
      }
      if (count == p.length()) {
        s = s + " " + p;
        p = readString("Please type in some other words");
      }

      else {
        p = readString("It's not a word!Please type in a word!");
      }
    }



    write(s);

    String s1 = "";
    if ((s.charAt(0) >= 'a') && (s.charAt(0) <= 'z')) {
      s1 = s1 + (char) (s.charAt(0) - 32);
    } else {
      s1 = s1 + s.charAt(0);
    }

    for (int i = 1; i < s.length(); i++) {
      if (s.charAt(i) != ' ') {
        if ((s.charAt(i) >= 'a') && (s.charAt(i) <= 'z')) {
          s1 = s1 + s.charAt(i);
        } else {
          s1 = s1 + (char) (s.charAt(i) + 32);
        }
      }
    }
    write("Startcase: " + s1);

    String s2 = "";
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) != ' ') {
        if ((s.charAt(i) >= 'a') && (s.charAt(i) <= 'z')) {
          s2 = s2 + (char) (s.charAt(i) - 32);
        } else {
          s2 = s2 + s.charAt(i);
        }
      }
    }
    write("UPPERCASE: " + s2);

    String s3 = "";
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == ' ') {
        s3 = s3 + '_';
      }

      else if ((s.charAt(i) >= 'a') && (s.charAt(i) <= 'z')) {
        s3 = s3 + s.charAt(i);
      } else {
        s3 = s3 + (char) (s.charAt(i) + 32);
      }

    }
    write("snake_case: " + s3);

    // PascalCase:
    String s4 = "";
    if ((s.charAt(0) >= 'a') && (s.charAt(0) <= 'z')) {
      s4 = s4 + (char) (s.charAt(0) - 32);
    } else {
      s4 = s4 + s.charAt(0);
    }

    for (int i = 1; i < s.length(); i++) {
      if (s.charAt(i) == ' ') {
        if ((s.charAt(i + 1) >= 'a') && (s.charAt(i + 1) <= 'z')) {
          s4 = s4 + (char) (s.charAt(i + 1) - 32);
        } else {
          s4 = s4 + s.charAt(i + 1);
        }
        i = i + 1;
      }

      else if ((s.charAt(i) >= 'a') && (s.charAt(i) <= 'z')) {
        s4 = s4 + s.charAt(i);
      } else {
        s4 = s4 + (char) (s.charAt(i) + 32);
      }

    }
    write("PascalCase: " + s4);


  }
}
