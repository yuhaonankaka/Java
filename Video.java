
class Video {
private String titel;
private static int countvideo = 0;
private int id;
private String[] genres;

Video(String titel){
  this.titel = titel;
  this.id = countvideo;
  genres=new String [5];
  
  countvideo++;
}


String getTitel() {
  return this.titel;
}
int getId() {
  return this.id;
}
String[] getGenres() {
  return this.genres;
}

int addGenre(String genre) {
  
  if (genres[4]!=null) {
    System.out.println("This video already has 5 genres!");
    return -1;
  }
  int numofgenre = 0;
  
  
  for (int i =0;i<5;i++) {
    
    
    if (genres[i]==null) {
      genres[i] = genre;
      numofgenre++;
      break;
    }
    if (genres[i].equals(genre)) {
      System.out.println("This genre is already in the genres!");
      return -1;
    }
    
  }
  return numofgenre;
}




}








