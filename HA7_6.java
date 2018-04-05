
public class HA7_6 {

  private static void swap(int[] array, int a, int b) {
    int temp = array[a];
    array[a] = array[b];
    array[b] = temp;
  }

  private static void slowSort(int[] array) {
    slowSorthelper(array, 0, array.length - 1);
  }

  private static void slowSorthelper(int[] array, int i, int j) {
    if (j <= i) {
      return;
    }
    int m = (i + j) / 2;
    slowSorthelper(array, i, m);
    slowSorthelper(array, m + 1, j);
    if (array[m] > array[j]) {
      swap(array, m, j);
    }
    slowSorthelper(array, i, j - 1);
  }



  private static void evenSlowerSort(int[] array) {
    evenSlowerSorthelper(array, 0, array.length - 1);
  }


  private static void evenSlowerSorthelper(int[] array, int i, int j) {
    if (j <= i) {
      return;
    }
    int m1 = (j - i) / 3 + i;
    int m2 = 2 * (j - i) / 3 + i;
    evenSlowerSorthelper(array, i, m1);
    evenSlowerSorthelper(array, m1 + 1, m2);
    evenSlowerSorthelper(array, m2 + 1, j);
    if (array[m1] > array[j]) {
      swap(array, m1, j);
    }
    if (array[m2] > array[j]) {
      swap(array, m2, j);
    }
    evenSlowerSorthelper(array, i, j - 1);
  }



  public static void main(String[] args) {
    // TODO Auto-generated method stub
    int[] array =
        {4, 6, 64, 33, 42, 58, 79, 11, 14, 8, 9, 2, 8, 32, 2, 1, 3, 4, 12, 19, 15, 16, 14, 18, 22};
    int[] array2 =
        {4, 6, 64, 33, 42, 58, 79, 11, 14, 8, 9, 2, 8, 32, 2, 1, 3, 4, 12, 19, 15, 16, 14, 18, 22};
    for (int i = 0; i < array.length; i++) {
      MiniJava.writeConsole(array[i] + " ");
    }
    MiniJava.writeLineConsole();
    evenSlowerSort(array);
    for (int i = 0; i < array.length; i++) {
      MiniJava.writeConsole(array[i] + " ");
    }

    MiniJava.writeLineConsole();
    slowSort(array2);
    for (int i = 0; i < array.length; i++) {
      MiniJava.writeConsole(array[i] + " ");
    }



  }

}
