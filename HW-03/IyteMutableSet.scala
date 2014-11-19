/**
 * Created by Murat on 18.11.2014.
 */
class IyteMutableSet {

  private var storage : Array[Int] = new Array[Int](IyteMutableSet.DEFAULT_INITIAL_SIZE);
  private var lastFilledIndex = -1;

  def this(initialSize : Int){
    this();
    if(initialSize > 0){
      storage = new Array[Int](initialSize);
    }
  }
  def this(integers : Array[Int]){
    this(integers.length * IyteMutableSet.GROW_FACTOR);
    for( x : Int <- integers){
      this.add(x);
    }
  }
  def this(integers : Int*){
    this(integers.toArray);
  }

  def add(x:Int) : Boolean = {
    if(this.contains(x)){
      return false;
    }

    if(isStorageFull()){
      growStorage();
    }

    val (smallerNoIndex,biggerNoIndex) : (Int,Int) = findIndexes(x);

    val shiftAmount = 1;

    if(lastFilledIndex > -1){
      shiftToRight(biggerNoIndex, shiftAmount);
    }

    storage(biggerNoIndex) = x;
    lastFilledIndex += shiftAmount;

    return true;
  }

  def contains(x:Int) : Boolean ={
    if(lastFilledIndex == -1){
      return false;
    }

    if(getIndex(x) != -1){
      return true;
    }else{
      return false;
    }
  }

  private def getIndex(x : Int): Int ={
    if(lastFilledIndex < 0){
      return -1;
    }

    def calcPivot(iMin : Int, iMax : Int) : Int = {
      iMax - ( (iMax - iMin) / 2 );
    }

    var iMin = 0;
    var iMax = lastFilledIndex;

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

  /**This method returns indexes where the given integer
   * should be inserted between.
   * <br>
   * Example: <br>
   * Consider val a = Array(10,20,30,40,50); <br>
   * 35 should be inserted between 30 and 40, so findIndexes(35) will return (2,3)<br>
   * 55 should be inserted after 50, so findIndexes(50) will return (4,5)<br>
   * 5 should be inserted before 10, so findIndexes(5) will return (-1,0)<br>
   */
  private def findIndexes(x:Int): (Int, Int) ={

    if(lastFilledIndex < 0){
      return (-1,0)
    }else if(storage(0) > x){
      return (-1,0)
    }else if( storage(lastFilledIndex) < x ){
      return (lastFilledIndex,lastFilledIndex + 1)
    }

    def calcPivot(iMin : Int, iMax : Int) : Int = {
      iMin + ( (iMax - iMin) / 2 );
    }

    var iMin = 0;
    var iMax = lastFilledIndex;


    while(iMax > iMin + 1){
      val iMid = calcPivot(iMin, iMax);

      if(storage(iMid) < x){
        iMin = iMid;
      }else{
        iMax = iMid;
      }
    }

    return (iMin,iMax);
  }

  private def isStorageFull() : Boolean ={
    if(lastFilledIndex == storage.length-1){
      return true;
    }else{
      return false;
    }
  }

  private def growStorage() : Unit ={
    growStorage(storage.length * IyteMutableSet.GROW_FACTOR);
  }

  private def growStorage(newCapacity : Int) : Unit ={
    var newLength = storage.length;

    while(newLength < newCapacity){
      newLength *= IyteMutableSet.GROW_FACTOR;
    }

    var newStorage : Array[Int] = new Array[Int](newLength);

    for( i : Int <- 0 until storage.length){
      newStorage(i) = storage(i);
    }

    storage = newStorage;
  }

  private def shiftToRight(fromIndex : Int, shiftAmount : Int) : Unit = {
    for( i : Int <- lastFilledIndex to fromIndex by -1){
      storage(i+shiftAmount) = storage(i);
    }
  }

  override def toString(): String ={
    val b : StringBuilder = new StringBuilder();

    for( i : Int <- 0 to lastFilledIndex){
      b.append(storage(i) + ",");
    }

    if(b.size > 0){
      b.deleteCharAt(b.size-1);
    }

    return b.toString();
  }

}

object IyteMutableSet {

  val DEFAULT_INITIAL_SIZE : Int = 16;
  val GROW_FACTOR : Int = 2;

  def apply() = new IyteMutableSet();
  def apply(integers : Array[Int]) = new IyteMutableSet(integers);
  def apply(integers : Int*) = new IyteMutableSet(integers.toArray);

}
