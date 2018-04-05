package suchtbaum12_5;
import static org.junit.Assert.assertEquals;
import java.util.HashSet;
import java.util.Random;


public class Test {
  private static Random random = new Random();
  
  
  
  
  public static void main(String[] args) throws InterruptedException{
    Suchtbaum<Integer> suchti = new Suchtbaum<>();
    
    for (int i = 0; i < 3; i++) {  
      new Thread(new Runnable() {  

          @Override  
          public void run() {  
            HashSet<Integer> testSet = new HashSet<>();
            int n = 10000;
            for (int i = 0; i < n; i++)
              testSet.add(random.nextInt(20*n));
            Suchtbaum<Integer> suchti = new Suchtbaum<>();
            for(Integer i : testSet)
              try {
                suchti.insert(i);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }

          }  
      }).start();  
    
    }
    
     
    
    
    for (int i = 0; i < 3; i++) {  
      new Thread(new Runnable() {  

          @Override  
          public void run() {  
            HashSet<Integer> testSet = new HashSet<>();
            int n = 10000;
            for (int i = 0; i < n; i++)
              testSet.add(random.nextInt(20*n));
            Suchtbaum<Integer> suchti = new Suchtbaum<>();
            for(Integer i : testSet)
              try {
                suchti.insert(i);
              } catch (InterruptedException e) {
               
                e.printStackTrace();
              }
            for (int i = 0; i < n; i++) {
              int next = random.nextInt(20*n);
              if(testSet.contains(next)) {
                try {

                  suchti.remove(next);
                } catch (InterruptedException e) {
                  
                  e.printStackTrace();
                }
                testSet.remove(next);
              }
            }

          }  
      }).start();  
    
    }
    
    
    
    
  }
}

