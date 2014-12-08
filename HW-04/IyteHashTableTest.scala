import java.io.PrintWriter

import scala.collection.mutable

/**
 * Created by Murat on 29.11.2014.
 */
object IyteHashTableTest extends App {
  val filename = "HW-04\\wlist_match1.txt"
  val strings = scala.io.Source.fromFile(filename).getLines.toList;


  for(i<-0 to 5){
    val t = IyteHashTable();

    val tSet1 = System.currentTimeMillis()
    for(line<-strings){
      t.set(line,line);
    }
    val tSet2 = System.currentTimeMillis()

    val tGet1 = System.currentTimeMillis()
    for(line<-strings){
      t.get(line)
    }
    val tGet2 = System.currentTimeMillis()

    println("tSet #" + i + " " + (tSet2-tSet1));
    println("tGet #" + i + " " + (tGet2-tGet1));
    println();
  }

  for(i<- 0 to 5){
    val s = mutable.HashMap[String,String]()

    val sSet1 = System.currentTimeMillis()
    for(line<-strings){
      s.put(line,line);
    }
    val sSet2 = System.currentTimeMillis()

    val sGet1 = System.currentTimeMillis()
    for(line<-strings){
      s.get(line)
    }
    val sGet2 = System.currentTimeMillis()

    println("sSet #" + i + " " + (sSet2-sSet1));
    println("sGet #" + i + " " + (sGet2-sGet1));
    println();
  }


}