/**
 * Created by Murat on 19.11.2014.
 */
class IyteImmutableSet {

  private var storage : Array[Int] = null;

  def this(array : Array[Int]){
    this();
    storage = array.distinct;
    storage = this.sort(storage);
  }
  def this(integers : Int*){
    this(integers.toArray);
  }

  def add(integers : Int*) : IyteImmutableSet = {
    return add(integers.toArray);
  }
  def add(integers : Array[Int]) : IyteImmutableSet = {
    return new IyteImmutableSet(storage ++ integers);
  }
  def add(x : Int) : IyteImmutableSet = {
    return new IyteImmutableSet(storage :+ x);
  }

  def contains(x:Int) : Boolean ={
    if(getIndex(x) != -1){
      return true;
    }else{
      return false;
    }
  }

  private def createDistinctArray(array : Array[Int]) : Array[Int] = {
    return null;
  }

  private def sort(arrayToBeSorted:Array[Int]): Array[Int] = {
    if (arrayToBeSorted.length < 2) arrayToBeSorted
    else {
      val midPoint = arrayToBeSorted(arrayToBeSorted.length / 2)
      sort(arrayToBeSorted filter (midPoint >)) ++ (arrayToBeSorted filter (midPoint ==)) ++ sort(arrayToBeSorted filter (midPoint <))
    }
  }

  private def getIndex(x : Int): Int ={
    if(storage == null){
      return -1;
    }

    def calcPivot(iMin : Int, iMax : Int) : Int = {
      iMax - ( (iMax - iMin) / 2 );
    }

    var iMin = 0;
    var iMax = storage.length;

    while(iMin != iMax){
      val iMid = calcPivot(iMin,iMax);

      if(x < storage(iMid)){
        iMax = iMid - 1;
      }else{
        iMin = iMid;
      }
    }

    if(x == storage(iMax)){
      return iMax;
    }else{
      return -1;
    }
  }

  override def toString(): String ={
    val b : StringBuilder = new StringBuilder();

    for( i : Int <- 0 until storage.length if storage != null){
      b.append(storage(i) + ",");
    }

    if(b.size > 0){
      b.deleteCharAt(b.size-1);
    }

    return b.toString();
  }

}
object IyteImmutableSet {
  def apply() = new IyteImmutableSet();
  def apply(integers : Array[Int]) = new IyteImmutableSet(integers);
  def apply(integers : Int*) = new IyteImmutableSet(integers.toArray);
}
