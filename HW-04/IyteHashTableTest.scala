import java.io.PrintWriter
import java.util

/**
 * Created by Murat on 29.11.2014.
 */
object IyteHashTableTest extends App {

  val t = new IyteHashTable();
  val j = new util.HashMap[String,String]()

  val filename = "HW-04\\wlist_match1.txt"
  val strings = scala.io.Source.fromFile(filename).getLines.toList;

  val tSet1 = System.nanoTime()
  for (line <- strings) {
    t.set(line,line);
  }
  val tSet2 = System.nanoTime()

  val jSet1 = System.nanoTime()
  for (line <- strings) {
    j.put(line,line);
  }
  val jSet2 = System.nanoTime()


  val jGet1 = System.nanoTime()
  var eCount1 = 0;
  for(line <- strings){
    if(line != j.get(line)){
      eCount1 += 1;
    }
  }
  val jGet2 = System.nanoTime()

  val tGet1 = System.nanoTime()
  var eCount2 = 0;
  for(line <- strings){
    if(line != t.get(line)){
      eCount2 += 1;
    }
  }
  val tGet2 = System.nanoTime()

  println("ERROR1: " + eCount1)
  println("ERROR2: " + eCount2)
  println("tSet: " + (tSet2 - tSet1));
  println("tGet: " + (tGet2 - tGet1));
  println("jSet: " + (jSet2 - jSet1));
  println("jGet: " + (jGet2 - jGet1));
  println(t.getSize());
}