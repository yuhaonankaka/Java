
public class HA4_6 extends MiniJava {

  public static void main(String[] args) {
    String s = readString("Please write some text!");
    int hauptmenu = read("What do you want to do with this text?" + "\n"
        + "type in 1 for: Häufigkeit der Buchstaben.\n" + "type in 2 for: Buchstaben ersetzen\n"
        + "type in 3 for: Wortweise Spiegeln");
    while (!(hauptmenu == 1 || hauptmenu == 2 || hauptmenu == 3)) {
      write("Wrong input!!!please type in a valid number!!!!");
      hauptmenu = read("What do you want to do with this text?" + "\n"
          + "type in 1 for: Häufigkeit der Buchstaben.\n" + "type in 2 for: Buchstaben ersetzen\n"
          + "type in 3 for: Wortweise Spiegeln");
    }

    if (hauptmenu == 1) {
      String upper = "";
      for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
          char r = (char) (s.charAt(i) - 32);
          upper = upper + r;
        } else {
          upper = upper + s.charAt(i);
        }
      }


      String sss = "";
      for (char j = 'A'; j <= 'Z'; j++) {
        int n = 0;
        for (int i = 0; i < upper.length(); i++) {
          if (upper.charAt(i) == j) {
            n = n + 1;
          }
        }
        if (n > 0) {
          sss = sss + (j + ":" + n + " ");
        }
      }
      write(sss);

    }



    if (hauptmenu == 2) {

      String s1 = readString("Please type in the first letter");
      String s2 = readString("Please type in the second letter");
      boolean typeright = false;
      while (!typeright) {
        if ((s1.length() == 1) || (s2.length() == 1)) {
          if (((s1.charAt(0) >= 'a') && (s1.charAt(0) <= 'z'))
              || ((s1.charAt(0) >= 'A') && (s1.charAt(0) <= 'Z')))
            typeright = true;
          break;
        }
        write("Your input has some problems! Please type in two single letter!!!");
        s1 = readString("Please type in the first letter");
        s2 = readString("Please type in the second letter");
      }

      char c2 = s1.charAt(0);
      if (c2 >= 'A' && c2 <= 'Z') {
        c2 = (char) (c2 + 32);
      }

      char c3 = s2.charAt(0);
      if (c3 >= 'A' && c3 <= 'Z') {
        c3 = (char) (c3 + 32);
      }



      String out = "";

      for (int i = 0; i < s.length(); i++) {
        if ((s.charAt(i) == c2) || (s.charAt(i) == (char) (c2 - 32))) {
          if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
            out = out + c3;// lower
          } else {
            out = out + (char) (c3 - 32); // upper
          }
        }

        else {
          out = out + s.charAt(i);
        }
      }
      write(out);
    }

    if (hauptmenu == 3) {

      String out2 = "";
      int n = -1;
      for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) == ' ') {
          for (int j = i - 1; j > n; j--) {
            out2 = out2 + s.charAt(j);
          }
          n = i;
          out2 = out2 + ' ';
        }

      }
      for (int j = s.length() - 1; j > n; j--) {
        out2 = out2 + s.charAt(j);
      }
      write(out2);
    }

  }

}
