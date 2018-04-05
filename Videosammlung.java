class Videosammlung {
  private int n;
  private Video [] album;
  private int verbleibende;
  Videosammlung (int n){
  this.n = n;  
  this.album = new Video[this.n];
  this.verbleibende = this.n;
  }
  int addVideo(Video v) {
    if (album[n-1]!=null) {
      return -1;
    }
    for (int i = 0 ;i<n;i++) {
      if (album[i]==null) {
        album[i]=v;
        verbleibende--;
        return i;
      }
    }
    return -1;
  }
  
  Video verkaufen(int position) {
    if (position+1>n) {
      return null;
    }
    
    if (album[position]==null) {
      return null;
    }
    else {
      Video temp = album[position];
      album[position]=null;
      verbleibende++;
      return temp;
      
    }
  }
  
  Video verkaufen(String titel) {
    for (int i = 0;i<this.n;i++) {
      if (album[i]!=null) {
      if (album[i].getTitel().equals(titel)) {
        Video temp = album[i];
        album[i]=null;
        verbleibende++;
        return temp;
        }
      }
    }
    return null;
    
  }
  
  public int getVerbleibende() {
    return this.verbleibende;
  }
  
  String[] videosInGenre (String genre) {
    String[] genrevideos = new String [n];
    int rank=0;
    for (int i = 0;i<n;i++) {
      if (album[i]==null) {
        continue;
      }
      else {
      String [] ss = album[i].getGenres();
      for (int j =0;j<ss.length;j++) {
        if (ss[j]!=null) {
        if (ss[j].equals(genre)) {
          genrevideos[rank] = album[i].getTitel();
          rank++;
          break;
        }
        }
      }
      }
    }
    
    if (rank == 0) {
      return null;
    }
    else {
      String[] gvideos = new String [rank];
      for (int i = 0;i<rank;i++) {
        gvideos[i]=genrevideos[i];
      }
      return gvideos;
    }
    
  }
  
}
