/**
 * Created by Murat on 10.10.2014.
 */
object simple extends App{
  val simpleArray = Array[Int](10, 25, 30)

  simpleArray.foreach( (arg:Int) => println(
  {
    if(arg % 2 == 0){
      arg * 2
    }else{
      arg * 3
    }
  }))
}
