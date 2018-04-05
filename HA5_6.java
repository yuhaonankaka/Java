
public class HA5_6 extends Aerger {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    int[] arrYellow = {-1, -1, -1, -1};
    int[] arrBlue = {-2, -2, -2, -2};
    int[] arrRed = {-1, -1, -1, -1};
    int[] arrGreen = {-1, -1, -1, -1};
    int[] player1step = {0, 0, 0, 0};
    int[] player2step = {0, 0, 0, 0};

    boolean game_on = true;

    while (game_on) {

      Aerger.paintField(arrYellow, arrBlue, arrRed, arrGreen);
      int player1dice = dice();
      write("player1's dice:" + player1dice);

      int i = readInt("Which rock do you want to move? please type in number from 1-4;");
      int count1 = 0;// count the numbers that maybe the same as s.
      for (int j = 0; j < 4; j++) {
        if ((player1step[i - 1] + player1dice) == (player1step[j])) {
          if (player1step[j] < 40) {
            count1 = count1 + 1;
          }
        }
      }
      int count2 = 0;// count the numbers that to decide whether it's blocked.
      for (int j = 0; j < 4; j++) {
        if ((player1step[i - 1] < player1step[j])
            && ((player1step[i - 1] + player1dice) > player1step[j])) {
          count2 = count2 + 1;
        }
      }
      while (count1 > 0 || (player1step[i - 1] > 39) || count2 > 0) {

        i = readInt("There must be something wrong!Please type in a right i!");
        count1 = 0;
        for (int j = 0; j < 4; j++) {
          if ((player1step[i - 1] + player1dice) == (player1step[j])) {
            if (player1step[j] < 40) {
              count1 = count1 + 1;
            }
          }
        }
        count2 = 0;// count the numbers that to decide whether it's blocked.
        for (int j = 0; j < 4; j++) {
          if ((player1step[i - 1] < player1step[j])
              && ((player1step[i - 1] + player1dice) > player1step[j])) {
            if (player1step[j] < 40) {
              count2 = count2 + 1;
            }
          }
        }
      }



      player1step[i - 1] = player1step[i - 1] + player1dice;
      arrYellow[i - 1] = player1step[i - 1];

      for (int j = 0; j < 4; j++) {
        if (arrYellow[i - 1] == arrBlue[j]) {
          if (player2step[j] < 40) {
            arrBlue[j] = -2;
            player2step[j] = 0;
          }
        }
      }

      if ((player1step[0] >= 40) && (player1step[1] >= 40) && (player1step[2] >= 40)
          && (player1step[3] >= 40)) {
        write("Player1 has won the game!!!");
        break;
      }

      Aerger.paintField(arrYellow, arrBlue, arrRed, arrGreen);
      int player2dice = dice();
      write("player2's dice:" + player2dice);

      int i2 = readInt("Which rock do you want to move? please type in number from 1-4;");
      int count12 = 0;// count the numbers that maybe the same as s.
      for (int j = 0; j < 4; j++) {
        if ((player2step[i2 - 1] + player2dice) == (player2step[j])) {
          if (player2step[j] < 40) {
            count12 = count12 + 1;
          }
        }
      }
      int count22 = 0;// count the numbers that to decide whether it's blocked.
      for (int j = 0; j < 4; j++) {
        if ((player2step[i2 - 1] < player2step[j])
            && ((player2step[i2 - 1] + player2dice) > player2step[j])) {
          count22 = count22 + 1;
        }
      }
      while (count12 > 0 || (player2step[i2 - 1] > 39) || count22 > 0) {

        i2 = readInt("There must be something wrong!Please type in a right i!");
        count12 = 0;
        for (int j = 0; j < 4; j++) {
          if ((player2step[i2 - 1] + player2dice) == (player2step[j])) {
            if (player2step[j] < 40) {
              count12 = count12 + 1;
            }
          }
        }
        count22 = 0;// count the numbers that to decide whether it's blocked.
        for (int j = 0; j < 4; j++) {
          if ((player2step[i2 - 1] < player2step[j])
              && ((player2step[i2 - 1] + player2dice) > player2step[j])) {
            if (player2step[j] < 40) {
              count22 = count22 + 1;
            }
          }
        }
      }



      player2step[i2 - 1] = player2step[i2 - 1] + player2dice;
      arrBlue[i2 - 1] = player2step[i2 - 1] + 10;
      if (arrBlue[i2 - 1] > 39 && arrBlue[i2 - 1] < 50) {
        arrBlue[i2 - 1] = arrBlue[i2 - 1] - 40;
      }



      for (int j = 0; j < 4; j++) {
        if (arrBlue[i2 - 1] == arrYellow[j]) {
          if (player1step[j] < 40) {
            arrYellow[j] = -1;
            player1step[j] = 0;
          }
        }
      }

      if ((player2step[0] >= 40) && (player2step[1] >= 40) && (player2step[2] >= 40)
          && (player2step[3] >= 40)) {
        write("Player2 has won the game!!!");
        break;
      }


    }

    Aerger.paintField(arrYellow, arrBlue, arrRed, arrGreen);

  }

}
