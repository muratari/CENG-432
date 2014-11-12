/**
 * Created by Murat on 12.11.2014.
 */
object Test extends App{

  println("##### Testing IyteMutableList #####");

  println("Creating empty a1");
  val a1 : IyteMutableList = IyteMutableList();
  println("a1:"+a1.toString());

  println("Adding 1,2,3 to a1")
  a1.add(1,2,3);
  println("a1:"+a1.toString);

  println("Creating a2");
  val a2 = a1;
  println("Adding 4,5,6 to a2")
  a2.add(4,5,6);

  println("a2:"+a2.toString());
  println("a1:"+a1.toString());

  println("##### Testing IyteImmutableList #####");
  
  println("Creating empty b1")
  val b1 = IyteImmutableList();
  println("b1:"+b1.toString());

  println("Creating b2 by adding 1,2,3 to b1");
  val b2 : IyteImmutableList = b1.add(1,2,3);
  println("b1:" + b1.toString());
  println("b2:" + b2.toString());

  println("Creating b3 by adding 4,5,6 to b2")
  val b3 : IyteImmutableList = b2.add(4,5,6);
  println("b2:" + b2.toString());
  println("b3:" + b3.toString());
}
