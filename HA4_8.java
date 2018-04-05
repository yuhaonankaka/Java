
public class HA4_8 extends MiniJava {

  public static void main(String[] args) {
    int key = readInt("Please type in a key!");
    while (key < 0 || key > 63) {
      key = readInt("Please type in a valid key between 0 and 63!");
    }
    int iv = readInt("Please type in a initialization vector!");
    while (iv < 0 || iv > 63) {
      iv = readInt("Please type in a initialization valid vector between 0 and 63!");
    }

    String s = readString("Please type in a text!");
    for (int i = 0; i < s.length(); i++) {
      if ((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') || (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')
          || (s.charAt(i) >= '0' && s.charAt(i) <= '9') || (s.charAt(i) == ' ')
          || (s.charAt(i) == '.')) {
        continue;
      } else {
        write("It's not a valid text! The program will be ended!");
        System.exit(0);
      }
    }

    int[] code1 = new int[s.length()];
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
        code1[i] = (int) s.charAt(i) - 97;
      } else if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
        code1[i] = (int) s.charAt(i) - 39;
      } else if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
        code1[i] = (int) s.charAt(i) + 4;
      } else if (s.charAt(i) == ' ') {
        code1[i] = 62;
      } else {
        code1[i] = 63;
      }
    }

    int[] xorcode1 = new int[s.length()];
    xorcode1[0] = (int) (code1[0] ^ iv);
    xorcode1[0] = (int) (xorcode1[0] ^ key);
    for (int i = 1; i < s.length(); i++) {
      xorcode1[i] = (int) (code1[i] ^ xorcode1[i - 1]);
      xorcode1[i] = (int) (xorcode1[i] ^ key);
    }

    String vs = "";
    for (int i = 0; i < s.length(); i++) {
      if (xorcode1[i] >= 0 && xorcode1[i] <= 25) {
        xorcode1[i] = xorcode1[i] + 97;
        vs = vs + (char) xorcode1[i];
      } else if (xorcode1[i] >= 26 && xorcode1[i] <= 51) {
        xorcode1[i] = xorcode1[i] + 39;
        vs = vs + (char) xorcode1[i];
      } else if (xorcode1[i] >= 52 && xorcode1[i] <= 61) {
        xorcode1[i] = xorcode1[i] - 4;
        vs = vs + (char) xorcode1[i];
      } else if (xorcode1[i] == 62) {
        vs = vs + ' ';
      } else {
        vs = vs + '.';
      }
    }

    write("verschlüsselte Text: " + vs);


    int[] code2 = new int[vs.length()];
    for (int i = 0; i < vs.length(); i++) {
      if (vs.charAt(i) >= 'a' && vs.charAt(i) <= 'z') {
        code2[i] = (int) vs.charAt(i) - 97;
      } else if (vs.charAt(i) >= 'A' && vs.charAt(i) <= 'Z') {
        code2[i] = (int) vs.charAt(i) - 39;
      } else if (vs.charAt(i) >= '0' && vs.charAt(i) <= '9') {
        code2[i] = (int) vs.charAt(i) + 4;
      } else if (vs.charAt(i) == ' ') {
        code2[i] = 62;
      } else {
        code2[i] = 63;
      }
    }


    int[] xorcode2 = new int[vs.length()];
    xorcode2[0] = (int) (code2[0] ^ key);
    xorcode2[0] = (int) (xorcode2[0] ^ iv);
    for (int i = 1; i < vs.length(); i++) {
      xorcode2[i] = (int) (code2[i] ^ key);
      xorcode2[i] = (int) (xorcode2[i] ^ code2[i - 1]);
    }

    String es = "";
    for (int i = 0; i < vs.length(); i++) {
      if (xorcode2[i] >= 0 && xorcode2[i] <= 25) {
        xorcode2[i] = xorcode2[i] + 97;
        es = es + (char) xorcode2[i];
      } else if (xorcode2[i] >= 26 && xorcode2[i] <= 51) {
        xorcode2[i] = xorcode2[i] + 39;
        es = es + (char) xorcode2[i];
      } else if (xorcode2[i] >= 52 && xorcode2[i] <= 61) {
        xorcode2[i] = xorcode2[i] - 4;
        es = es + (char) xorcode2[i];
      } else if (xorcode2[i] == 62) {
        es = es + ' ';
      } else {
        es = es + '.';
      }
    }

    write("entschlüsselte Text: " + es);



  }

}
