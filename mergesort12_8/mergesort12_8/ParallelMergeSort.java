package mergesort12_8;

public class ParallelMergeSort {
  
  
  
  
  public static int numberOfThreads = 8;

  public static void mergeSort(int[] arr) {
    int l = arr.length;
    int g = l/numberOfThreads;
    int last = l-(numberOfThreads-1)*g;
    int[][] teilarray = new int[numberOfThreads][g];
    teilarray[numberOfThreads-1]= new int[last];
    for(int i =0;i<numberOfThreads;i++) {
      if(i!=numberOfThreads-1)
      System.arraycopy(arr, g*i, teilarray[i], 0, g);
      else
      System.arraycopy(arr, g*i, teilarray[i], 0, teilarray[i].length);
      
    }
    
    
    sortthread[] st = new sortthread[numberOfThreads];
    for(int i = 0;i<numberOfThreads;i++) {
      st[i] = new sortthread(teilarray[i]);
      st[i].start();
    }
    for(int i = 0;i<numberOfThreads;i++) {
      try {
        st[i].join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    
    int [] result = fiMerge(st[0].getTarray(),st[1].getTarray());
    for(int i =2;i<numberOfThreads;i++) {
      result = fiMerge(result,st[i].getTarray());
    }
    
    for(int i =0;i<arr.length;i++) {
      arr[i]=result[i];
    }
    
    
   }
  
  
  
  
  
  public static int[] fiMerge(int[] first, int[] second) {
    int n,v,g;
    n=0;
    v=0;
    g=0;
    
    int[] ret = new int[first.length + second.length];
    while ((n < first.length) && (v < second.length)) {
        if (first[n] <= second[v]) {
            ret[g]=first[n];
            n++;
            g++;
        } else {
            ret[g]=second[v];
            v++;
            g++;
        }
        
    }
    
    if (n==first.length) {
      while (v<second.length) {
          ret[g]=second[v];
          g++;
          v++;
      }
  }
    else if (v==second.length) {
      while (n<first.length) {
          ret[g]=first[n];
          g++;
          n++;
      }
  }
    
    return ret;
}
  
   
}


class sortthread extends Thread {
  private int [] tarray;
  public int[] getTarray() {
    return tarray;
}
  
  public sortthread(int [] s) {
    tarray=s;
  }
  public void run() {
   NormalMergeSort.mergeSort(tarray);
}
  
}
