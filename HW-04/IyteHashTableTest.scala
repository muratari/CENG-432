import java.io.PrintWriter

/**
 * Created by Murat on 29.11.2014.
 */
object IyteHashTableTest extends App {

  val t = IyteHashTable();

  val filename = "HW-04\\wlist_match1.txt"
  val strings = scala.io.Source.fromFile(filename).getLines.toList;

  val tSet1 = System.nanoTime()
  for (line <- strings) {
    t.set(line,line);
  }
  for (line <- strings) {
    t.set(line,line);
  }
  val tSet2 = System.nanoTime()

  val tGet1 = System.nanoTime()
  var eCount2 = 0;
  for(line <- strings){
    if(line != t.get(line)){
      eCount2 += 1;
    }
  }
  val tGet2 = System.nanoTime()

  println("ERROR2: " + eCount2)
  println("tSet: " + (tSet2 - tSet1));
  println("tGet: " + (tGet2 - tGet1));
  println("Size: " + t.getSize());
}