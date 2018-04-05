package korrektur;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Tutor extends Thread{
  BlockingQueue<Klausur> queue1;
  BlockingQueue<Klausur> queue2;
  int num;
  boolean last;
  public Tutor(BlockingQueue<Klausur> q1,BlockingQueue<Klausur> q2,int aufgabe,boolean last) {
    queue1=q1;
    queue2=q2;
    num = aufgabe;
    this.last = last;
  }
  public void run() {
    try {
      while(true) {
      Klausur k = queue1.take();
      System.out.print(this.getId()+"take an exam!"+"Queue size: "+queue1.size());
      int p = Korrekturschema.punkte(num, k.getAntwort(num-1));
      k.setPunkte(num-1, p);
      if(last) {
      int [] pun = k.getPunkte();
      int sum=0;
      for(int i = 0;i<pun.length;i++) {
        sum=sum+pun[i];
      }
      k.setGesamtpunktzahl(sum);
      k.setNote(Korrekturschema.note(sum));
      }
      queue2.put(k);
      }
      
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
