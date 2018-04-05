package korrektur;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
public class Klausurkorrektur {
private static ArrayBlockingQueue<Klausur> q1 = new ArrayBlockingQueue<Klausur>(1700);
private static ArrayBlockingQueue<Klausur> q2 = new ArrayBlockingQueue<Klausur>(50);
private static ArrayBlockingQueue<Klausur> q3 = new ArrayBlockingQueue<Klausur>(50);
private static ArrayBlockingQueue<Klausur> q4 = new ArrayBlockingQueue<Klausur>(50);
private static ArrayBlockingQueue<Klausur> q5 = new ArrayBlockingQueue<Klausur>(50);
private static ArrayBlockingQueue<Klausur> q6 = new ArrayBlockingQueue<Klausur>(50);
private static ArrayBlockingQueue<Klausur> q7 = new ArrayBlockingQueue<Klausur>(50);
private static ArrayBlockingQueue<Klausur> q8 = new ArrayBlockingQueue<Klausur>(50);
private static ArrayBlockingQueue<Klausur> q9 = new ArrayBlockingQueue<Klausur>(1700);
private static ArrayBlockingQueue<Klausur> q10 = new ArrayBlockingQueue<Klausur>(1700);


public static void main(String [] args) {
  for(int i = 0;i<1700;i++) {
    Klausur k = new Klausur();
    try {
      q1.put(k);
      System.out.println("put !!!");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  Tutor [] tutors = new Tutor[60];
  for(int i = 0;i<60;i++) {
    if(i<7) {
      tutors[i] = new Tutor(q1,q2,1,false);
      tutors[i].start();
    }
    else if(i<14) {
      tutors[i] = new Tutor(q2,q3,2,false);
      tutors[i].start();
    }
    else if(i<21) {
      tutors[i] = new Tutor(q3,q4,3,false);
      tutors[i].start();
    }
    else if(i<28) {
      tutors[i] = new Tutor(q4,q5,4,false);
      tutors[i].start();
    }
    else if(i<35) {
      tutors[i] = new Tutor(q5,q6,5,false);
      tutors[i].start();
    }
    else if(i<42) {
      tutors[i] = new Tutor(q6,q7,6,false);
      tutors[i].start();
    }
    else if(i<49) {
      tutors[i] = new Tutor(q7,q8,7,false);
      tutors[i].start();
    }
    else {
      tutors[i] = new Tutor(q8,q9,8,true);
      tutors[i].start();
    }
  }
  
  
    Uebungsleitung u1 = new Uebungsleitung(q9,q10);
    Uebungsleitung u2 = new Uebungsleitung(q9,q10);
    u1.start();
    u2.start();
  
  for(int i=0;i<60;i++) {
    try {
      tutors[i].join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
 
  try {
    u1.join();
    u2.join();
  } catch (InterruptedException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
  
  if(q9.size()==1700) {
    System.out.println("Korrektur der Info 1 Klausur beendet :)");
  System.exit(0);
  }
  
  
}

}
