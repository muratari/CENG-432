/**
 * Created by Murat on 06.12.2014.
 */
object RandomStuffTest extends App{
  println(RandomStuff.executeWithRetry(3, 5/0));
  println(RandomStuff.executeWithRetry(3, 5/2));
  println(RandomStuff.executeWithRetry(3, 5/4));
  println(RandomStuff.transform(List(1,2,3), (x:Int) => x*2));
  println(RandomStuff.transform(List(2,4,6), (x:Int) => x/2));
  println(RandomStuff.transform(List(1,1,1), (x:Int) => x+2));
  println(RandomStuff.allValid(List(1,2,3), (x:Int)=>x<5));
  println(RandomStuff.allValid(List(1,2,3), (x:Int)=>x<1));
  println(RandomStuff.allValid(List(1,2,3), (x:Int)=>x<3));
}
