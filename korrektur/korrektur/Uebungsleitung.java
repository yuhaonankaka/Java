package korrektur;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Uebungsleitung extends Thread{
  BlockingQueue<Klausur> queue1;
  BlockingQueue<Klausur> queue2;
  public Uebungsleitung(BlockingQueue<Klausur> q1,BlockingQueue<Klausur> q2) {
    queue1=q1;
    queue2=q2;
  }
  
  public void run() {
    while(true) {
      try {
        Klausur k = queue1.take();
        int gesat = k.getGesamtpunktzahl();
        for(int i =0;i<8;i++) {
          
        Random r = new Random();
        if(r.nextInt(10)==0  && k.getPunkte()[i]<=5)
          k.setZweitkorrektur(i,k.getPunkte()[i]+1);
        if(r.nextInt(10)==1 && k.getPunkte()[i]>=1)
          k.setZweitkorrektur(i,k.getPunkte()[i]-1);
        }
        int sum =0;
        for(int ki:k.getZweitkorrektur()) {
          sum=sum+ki;
        }
        
        k.setGesamtpunktzahl(sum);
        float f = Korrekturschema.note(k.getGesamtpunktzahl());
        k.setNote(f);
        queue2.put(k);
        
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      
    }
  }
}
