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

  println("##### But wait! There is more xD #####");

  val c1 : IyteImmutableList = new IyteImmutableList(10,12,14);
  println("c1:" + c1.toString());

  val c2 : IyteMutableList = new IyteMutableList(16,18,20);
  println("c2:" + c2.toString());

  val c3 : IyteImmutableList = new IyteImmutableList();
  println("c3:" + c3.toString())

  val c4 : IyteMutableList = new IyteMutableList();
  println("c4:" + c4.toString())

  val c5 : IyteImmutableList = new IyteImmutableList(100);
  println("c5:" + c5.toString())

  val c6 : IyteMutableList = new IyteMutableList(200);
  println("c6:" + c6.toString())
}
