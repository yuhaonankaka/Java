public class Worstorage {
  private Penguin [] ps;
  private int [] count;
  public Worstorage() {
    
    ps = new Penguin [1];
    count = new int [1];
    count[0] = 0;
    
  }

  public void add(Penguin penguin) {
    if (ps[0]==null) {
      ps[0]=penguin;
      count[0]++;
      return;
    } //if this is the first penguin;
    
    
    for (int i =0;i<ps.length;i++) {
      if (ps[i]==(penguin)) {
        return;
      }
    } //to see if there is a same penguin already exists;
    
  
    
   
    
    int currenttemp=1;
    int check=1;
    while(ps[currenttemp-1]!=null) {
      if (penguin.compareTo(ps[currenttemp-1])==-1) {//new penguin's cuteness < current penguin's cuteness;
        currenttemp=currenttemp*2;
        check++;
        if(check>count.length) {
          check =-1;
          break;
        }
      }
      else {
        currenttemp = currenttemp*2 + 1 ;
        check++;
        if(check>count.length) {
          check = -1;
          break;
        }
      }
      
    }
    
    
    if (check == -1) {
      Penguin [] pstemp = new Penguin [2*ps.length+1];
      
      for (int i =0;i<ps.length;i++) {
        pstemp[i] = ps[i];
      }
      ps = pstemp;
      
      int [] counttemp = new int [count.length+1];
      for (int i =0;i<count.length;i++) {
        counttemp[i] = count[i];
      }
      count = counttemp;      
    }
    
    int current = 1;
    int layer = 0;
    while(ps[current-1]!=null) {
      if (penguin.compareTo(ps[current-1])==-1) {//new penguin's cuteness < current penguin's cuteness;
        current=current*2;
        layer++;
      }
      else {
        current = current*2 + 1 ;
        layer++;
      }
    }
    count[layer]++;
    ps[current-1] = penguin;
    
    
  }

  public boolean find(Penguin penguin) {
    int current = 1;
    while(current<=ps.length) {
      if (ps[current-1]==(penguin)) {
        return true;
      }
      if (penguin.compareTo(ps[current-1])==-1) {//new penguin's cuteness < current penguin's cuteness;
        current=current*2;
      }
      else {
        current = current*2 + 1 ;
      }
    }
    
    return false;
    
    
    
  }

  public void move(Worstorage w, int c) {
    if ((2*c)>w.ps.length) {
      w.ps[c-1]=null;
      return;
    }
    if (w.ps[2*c-1]==null && w.ps[2*c]==null) {
      w.ps[c-1]=null;
      return;
    }
    
    else {
      if (c%2==0) {
      w.ps[c-1]=w.ps[2*c-1];
      w.ps[c]=w.ps[2*c];
      move(w,2*c);
      move(w,2*c+1);
      }
      else {
      w.ps[c-2]=w.ps[2*c-1];
      w.ps[c-1]=w.ps[2*c];
      move(w,2*c);
      move(w,2*c+1);
      }
    }   
  } 
  
  public void remove(Penguin penguin) {
    if (find(penguin) == false) {
      System.out.println("There is no this penguin in the set!");
      return;
    }
    
    int layer = 1;
    int current = 1;
    
    while(current<=ps.length) {
      if (ps[current-1]==(penguin)) {
        break;
      }
      if (penguin.compareTo(ps[current-1])==-1) {//new penguin's cuteness < current penguin's cuteness;
        current=current*2;
        layer++;
      }
      else {
        current = current*2 + 1 ;
        layer++;
      }
    }
    
    if (layer == count.length) {//在最底下；
      ps[current-1]=null;
      //TODO; 检查count 
      int countnum=0;
      for (int i = (int) (Math.pow(2, (count.length-1))-1);i<Math.pow(2, count.length)-1;i++) {
        if (ps[i]!=null) {
          countnum++;
        }
      }
      if (countnum==0) {
      int [] counttemp = new int [count.length-1];
      count = counttemp;
      Penguin [] pstemp = new Penguin [(ps.length-1)/2];
      for (int i = 0;i<pstemp.length;i++) {
        pstemp[i]=ps[i];
      }
      ps = pstemp;
          }  
      
      for (int i = 1;i<=count.length;i++) {
        int num=0;
        for (int j = (int) (Math.pow(2, (i-1))-1);j<Math.pow(2,i)-1;j++) {
          if (ps[j]!=null) {
            num++;
          }
        }
        count[i-1]=num;
      }
      
      return;
    }
    
    if (ps[current*2-1]==null && ps[current*2]==null) {//左右子节点为null；
      ps[current-1]=null;
      //TODO 检查count
      int countnum=0;
      for (int i = (int) (Math.pow(2, (count.length-1))-1);i<Math.pow(2, count.length)-1;i++) {
        if (ps[i]!=null) {
          countnum++;
        }
      }
      if (countnum==0) {
      int [] counttemp = new int [count.length-1];
      count = counttemp;
      Penguin [] pstemp = new Penguin [(ps.length-1)/2];
      for (int i = 0;i<pstemp.length;i++) {
        pstemp[i]=ps[i];
      }
      ps = pstemp;
          }  
      
      for (int i = 1;i<=count.length;i++) {
        int num=0;
        for (int j = (int) (Math.pow(2, (i-1))-1);j<Math.pow(2,i)-1;j++) {
          if (ps[j]!=null) {
            num++;
          }
        }
        count[i-1]=num;
      }
      return;
    }
    
    if (ps[current*2-1]==null ) {
       ps[current-1]=ps[current*2];
       
      //left child is null; just move all the right up;
     move(this,current*2+1);
     //TODO 检查count
     int countnum=0;
     for (int i = (int) (Math.pow(2, (count.length-1))-1);i<Math.pow(2, count.length)-1;i++) {
       if (ps[i]!=null) {
         countnum++;
       }
     }
     if (countnum==0) {
     int [] counttemp = new int [count.length-1];
     count = counttemp;
     Penguin [] pstemp = new Penguin [(ps.length-1)/2];
     for (int i = 0;i<pstemp.length;i++) {
       pstemp[i]=ps[i];
     }
     ps = pstemp;
         }  
     
     for (int i = 1;i<=count.length;i++) {
       int num=0;
       for (int j = (int) (Math.pow(2, (i-1))-1);j<Math.pow(2,i)-1;j++) {
         if (ps[j]!=null) {
           num++;
         }
       }
       count[i-1]=num;
     }
     
     return;
    }
    
    if (ps[current*2]==null ) {
      ps[current-1]=ps[current*2-1];
      move(this,current*2);
    //TODO 检查count
      int countnum=0;
      for (int i = (int) (Math.pow(2, (count.length-1))-1);i<Math.pow(2, count.length)-1;i++) {
        if (ps[i]!=null) {
          countnum++;
        }
      }
      if (countnum==0) {
      int [] counttemp = new int [count.length-1];
      count = counttemp;
      Penguin [] pstemp = new Penguin [(ps.length-1)/2];
      for (int i = 0;i<pstemp.length;i++) {
        pstemp[i]=ps[i];
      }
      ps = pstemp;
          }  
      
      for (int i = 1;i<=count.length;i++) {
        int num=0;
        for (int j = (int) (Math.pow(2, (i-1))-1);j<Math.pow(2,i)-1;j++) {
          if (ps[j]!=null) {
            num++;
          }
        }
        count[i-1]=num;
      }
      
      return;
    }
    
    if (ps[current*2-1]!=null && ps[current*2]!=null) {
      int min=2*current+1;
      
      while (min*2<=ps.length && ps[min*2-1]!=null  ) {
        min=2*min;
      }
      
      Penguin p = ps[min-1];
      remove(ps[min-1]);
      ps[current-1]=p;
      //TODO 检查count；
      int countnum=0;
      for (int i = (int) (Math.pow(2, (count.length-1))-1);i<Math.pow(2, count.length)-1;i++) {
        if (ps[i]!=null) {
          countnum++;
        }
      }
      if (countnum==0) {
      int [] counttemp = new int [count.length-1];
      count = counttemp;
      Penguin [] pstemp = new Penguin [(ps.length-1)/2];
      for (int i = 0;i<pstemp.length;i++) {
        pstemp[i]=ps[i];
      }
      ps = pstemp;
          }  
      
      for (int i = 1;i<=count.length;i++) {
        int num=0;
        for (int j = (int) (Math.pow(2, (i-1))-1);j<Math.pow(2,i)-1;j++) {
          if (ps[j]!=null) {
            num++;
          }
        }
        count[i-1]=num;
      }
        
      
      return;
      
      
    }
  }

  @Override
  public String toString() {
    // TODO
    String s ="";
    for (int i =0;i<ps.length-1;i++) {
      if (ps[i]!=null) {
        s=s+ps[i].getCuddliness()+",";
      }
      else {
        s=s+",";
      }
    }
    
    if (ps[ps.length-1]!=null) {
      s=s+ps[ps.length-1].getCuddliness();
    }
    
    return s;
  }

  // Zum Testen (Code sollte außerhalb der zu testenden Klasse liegen)
  public static void main(String[] args) {
    Test.main();
  }
}

class Penguin implements Comparable<Penguin> {

  private int cuddliness;

  public Penguin(int cuddliness) {
    this.cuddliness = cuddliness;
  }

  public int getCuddliness() {
    return this.cuddliness;
  }

  public void setCuddliness(int cuddliness) {
    this.cuddliness = cuddliness;
  }

  // Note: this class has a natural ordering that is inconsistent with equals.
  public int compareTo(Penguin other) {
    int oc = other.cuddliness;
    if (cuddliness < oc)
      return -1;
    if (cuddliness > oc)
      return 1;
    return 0;
  }
}

class Test {

  public static void main() {
    Worstorage w = new Worstorage();
    Penguin p1 = new Penguin(5);
    Penguin p2 = new Penguin(2);
    Penguin p3 = new Penguin(1);
    Penguin p4 = new Penguin(3);
    Penguin p5 = new Penguin(4);
    Penguin p6 = new Penguin(7);
    Penguin p7 = new Penguin(6);
    Penguin p8 = new Penguin(8);
    
    
    w.add(p1);
    w.add(p2);
    w.add(p3);
    w.add(p4);
  
    w.add(p6);
    
    w.add(p8);
    
    w.remove(p1);
    
    
    
    
    
   
    
    
    System.out.println(w.toString());
  }
}
