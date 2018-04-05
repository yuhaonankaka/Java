package suchtbaum12_5;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Suchtbaum<T extends Comparable<T>> {
  
  ReadWriteLock rwlock = new ReentrantReadWriteLock();  
  
  private class SuchtbaumElement {
    private T element = null;
    private SuchtbaumElement left = null;
    private SuchtbaumElement right = null;
    public T getElement() {
      return element;
    }
    public SuchtbaumElement getLeft() {
      return left;
    }
    public SuchtbaumElement getRight() {
      return right;
    }
    public void setElement(T element) {
      this.element = element;
    }
    public void setLeft(SuchtbaumElement left) {
      this.left = left;
    }
    public void setRight(SuchtbaumElement right) {
      this.right = right;
    }
    @Override
    public String toString() {
      String s = "";
      s+=this.getElement()+";\n";
      if(this.getLeft()!=null) {
      s+=this.getElement()+"->"+this.getLeft().getElement()+"[label = left];\n";
      String s2 = this.getLeft().toString();
      s=s+s2;
      }
      if(this.getRight()!=null) {
        s+=this.getElement()+"->"+this.getRight().getElement()+"[label = right];\n";
        String s2 = this.getRight().toString();
        s=s+s2;
        }
      return s;
      
      
    }
    
    
  }

  SuchtbaumElement e = new SuchtbaumElement();




  
  public void insert(T element) throws InterruptedException {
    rwlock.writeLock().lock();  

    try {
    SuchtbaumElement current = e;
    SuchtbaumElement last = null;
    int sit=0;
    
    while(current!=null) {
      if(current.element==null)
        break;
      if(element.compareTo(current.getElement())>0) {
        sit = 1;
        last = current;
        current = current.getRight();
      }
      else if(element.compareTo(current.getElement())<0) {
        sit = 0;
        last = current;
        current = current.getLeft();
      }
      else {
        throw new RuntimeException();
      }
    }
    if(last!=null && sit==1) {
      current = new SuchtbaumElement();
    last.setRight(current);}
    else if(last!=null && sit==0) {
      current = new SuchtbaumElement();
      last.setLeft(current);}
    current.setElement(element);
    }
    catch (Exception e){
    }
    finally {
      rwlock.writeLock().unlock();  
    }
  }

  public boolean contains(T element) {
    rwlock.readLock().lock();  

    try {
      
    SuchtbaumElement current = e;
    while(current!=null) {
      if (current.element==null)
        break;
      if(element.compareTo(current.getElement())>0) {
        current = current.getRight();
      }
      else if(element.compareTo(current.getElement())<0) {
        current = current.getLeft();
      }
      else {
        return true;
      }
    }
    return false;}
    catch(Exception e) {
      
    }
    finally {
      rwlock.readLock().unlock();  
    }
    
    return false;
  }

  public void remove(T element) throws InterruptedException {
    rwlock.writeLock().lock();  
    try {
    
    if(this.contains(element)==false) {
      System.out.println("Don't have this element! Can not remove!!!");
      return;
    }
    
    SuchtbaumElement current = e;
    SuchtbaumElement last = null;
    int direction=0;
    while(true) {
      System.out.println("Start to remove!");
      
      if(element.compareTo(current.getElement())>0) {
        last = current;
        direction=1;
        current = current.getRight();
      }
      else if(element.compareTo(current.getElement())<0) {
        last = current;
        direction=0;
        current = current.getLeft();
      }
      else {
        break;
      }
    }
    
    
    if(current.getLeft()==null && current.getRight()==null) {
      if(last==null)
        current.setElement(null);
      else {
        if(direction ==0)
          last.setLeft(null);
        else
          last.setRight(null);
      }
    }
    else if(current.getLeft()==null) {
      if (last==null) {
        current = current.getRight();
      }
      else {
        if(direction ==0)
          last.setLeft(current.getRight());
        else
          last.setRight(current.getRight());
      }
    }
    else if(current.getRight()==null) {
      if (last==null) {
        current = current.getLeft();
      }
      else {
        if(direction == 0)
          last.setLeft(current.getLeft());
        else
          last.setRight(current.getLeft());
      }
    }
    else {
      SuchtbaumElement biggest = current.getLeft();
      SuchtbaumElement biggestlast=current;
      while(biggest.getRight()!=null) {
        biggestlast=biggest;
        biggest=biggest.getRight();
      }
      
      if(last==null) {
        if(biggestlast==current) {
            biggest.setRight(current.getRight());
            current=biggest;         
        }
        else {
          if(biggest.getLeft()==null) {
            biggestlast.setRight(null);
            biggest.setLeft(current.getLeft());
            biggest.setRight(current.getRight());
            current=biggest;
          }
          else {
            biggest.setRight(biggest.getLeft());
            biggest.setLeft(current.getLeft());
            biggest.setRight(current.getRight());
            current=biggest;
          }
          
        }
      }
      
      
      if(biggest.getLeft()==null) {
        if(biggestlast==current) {
          current.setLeft(null);
        if(direction==0)
          last.setLeft(biggest);
        else
          last.setRight(biggest);
        }
        else {
          biggestlast.setRight(null);
          if(direction==0)
            last.setLeft(biggest);
          else
            last.setRight(biggest);
          biggest.setLeft(current.getLeft());
          biggest.setRight(current.getRight());
        }
      }
      // 左边有 右边没有
      else {
        if(biggestlast==current) {
          if(direction==0)
            last.setLeft(biggest);
          else
            last.setRight(biggest);
          biggest.setLeft(current.getLeft());
          biggest.setRight(current.getRight());
        }
        else {
          biggestlast.setRight(biggest.getLeft());
          if(direction==0)
            last.setLeft(biggest);
          else
            last.setRight(biggest);
          biggest.setLeft(current.getLeft());
          biggest.setRight(current.getRight());
        }
      }
      
    }
      
    } 
    catch(Exception e) {
      
    }
    finally {
      rwlock.writeLock().unlock();  
    }
  }

  
  @Override
  public String toString() {
    rwlock.readLock().lock();
    try {
    String s = "";
    s+="digraph G {\n";
    s+=e.toString();
    s+="}\n";
    return s;
    }
    catch (Exception e) {
      
    }
    finally {
      rwlock.readLock().lock();  
    }
    return null;
  }
}
