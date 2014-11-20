/**
 * Created by Murat on 19.11.2014.
 *
 * This is mah ImmutableSet implementation. Since "contains" method
 * had to be fast ( must be below O(n) ) for this homework,
 * I optimized for it. So, add method in this immutable thing
 * is ... let say unbearable (compared to "contains" method, adding
 * an element to a 100,000-elemented set tooks 1.2 ms, so you decide
 * if it is OK). But "contains" method is kinda superfast.
 * It is so fast that I needed nanosecond precision while testing this.
 *
 * It is actually a SortedSet... Whoopss...
 */
class IyteImmutableSet {

  private var storage: Array[Int] = null;

  private def this(oldStorage: Array[Int], newValues: Array[Int]) {
    this();

    if (newValues != null) {

      val distinctSortedNewValues = sort(distinct(newValues));

      if (oldStorage != null) {

        val willBeAdded: Array[Boolean] = new Array[Boolean](distinctSortedNewValues.length);
        var growAmount: Int = 0;

        for (i: Int <- 0 until distinctSortedNewValues.length) {
          if (getIndex(distinctSortedNewValues(i), oldStorage, oldStorage.length - 1) == -1) {
            growAmount += 1;
            willBeAdded(i) = true;
          }
        }

        storage = new Array[Int](oldStorage.length + growAmount);
        for (i: Int <- 0 until oldStorage.length) {
          storage(i) = oldStorage(i);
        }

        var lastFilledIndex = oldStorage.length - 1;

        for (i: Int <- 0 until willBeAdded.length) {
          if (willBeAdded(i)) {
            val index = findIndexes(distinctSortedNewValues(i), storage, lastFilledIndex) _2;
            lastFilledIndex += 1;
            shiftToRight(storage, index, index + 1);
            storage(index) = distinctSortedNewValues(i);
          }
        }
      } else {
        storage = distinctSortedNewValues;
      }
    } else if (oldStorage != null) {
      storage = new Array[Int](oldStorage.length);
      for (i: Int <- 0 until oldStorage.length) {
        storage(i) = oldStorage(i);
      }
    }
  }
  private def this(oldStorage: Array[Int], newValue: Int) {
    this();
    if (oldStorage != null ) {

      if(getIndex(newValue, oldStorage, oldStorage.length-1) != -1){
        throw new Exception("Element is already in the set");
      }

      storage = new Array[Int](oldStorage.length + 1);

      val index = findIndexes(newValue, oldStorage, oldStorage.length-1)_2;

      for (i: Int <- 0 until index) {
        storage(i) = oldStorage(i);
      }

      storage(index) = newValue;

      for(i: Int <- index until oldStorage.length){
        storage(i+1) = oldStorage(i);
      }

    } else {
      storage = Array(newValue);
    }
  }

  def this(array: Array[Int]) {
    this(null, array);
  }
  def this(integers: Int*) {
    this(integers.toArray);
  }

  def add(integers: Int*): IyteImmutableSet = {
    return add(integers.toArray);
  }
  def add(integers: Array[Int]): IyteImmutableSet = {
    return new IyteImmutableSet(storage, integers);
  }
  def add(x: Int): IyteImmutableSet = {
    return new IyteImmutableSet(storage, x);
  }

  def contains(x: Int): Boolean = {
    if (getIndex(x) != -1) {
      return true;
    } else {
      return false;
    }
  }

  private def distinct(array: Array[Int]): Array[Int] = {
    val buffer: Array[Int] = new Array[Int](array.length);
    var lastFilledIndex = -1;

    for (x <- array) {
      if (getIndex(x, array, lastFilledIndex) == -1) {
        lastFilledIndex += 1;
        buffer(lastFilledIndex) = x;
      }
    }

    val returnArray: Array[Int] = new Array[Int](lastFilledIndex + 1);
    for (i: Int <- 0 to lastFilledIndex) {
      returnArray(i) = buffer(i);
    }

    return returnArray;
  }

  private def findIndexes(x: Int, inArray: Array[Int], lastFilledIndex: Int): (Int, Int) = {

    if (lastFilledIndex < 0) {
      return (-1, 0)
    } else if (inArray(0) > x) {
      return (-1, 0)
    } else if (inArray(lastFilledIndex) < x) {
      return (lastFilledIndex, lastFilledIndex + 1)
    }

    def calcPivot(iMin: Int, iMax: Int): Int = {
      iMin + ((iMax - iMin) / 2);
    }

    var iMin = 0;
    var iMax = lastFilledIndex;


    while (iMax > iMin + 1) {
      val iMid = calcPivot(iMin, iMax);

      if (inArray(iMid) < x) {
        iMin = iMid;
      } else {
        iMax = iMid;
      }
    }

    return (iMin, iMax);
  }

  private def getIndex(x: Int, inArray: Array[Int], lastFilledIndex: Int): Int = {
    if (lastFilledIndex < 0) {
      return -1;
    }

    def calcPivot(iMin: Int, iMax: Int): Int = {
      iMax - ((iMax - iMin) / 2);
    }

    var iMin = 0;
    var iMax = lastFilledIndex;

    while (iMin != iMax) {
      val iMid = calcPivot(iMin, iMax);

      if (x < inArray(iMid)) {
        iMax = iMid - 1;
      } else {
        iMin = iMid;
      }
    }

    if (x == inArray(iMax)) {
      return iMax;
    } else {
      return -1;
    }
  }

  private def shiftToRight(inArray: Array[Int], fromIndex: Int, shiftAmount: Int): Unit = {
    for (i: Int <- inArray.length - 1 - shiftAmount to fromIndex by -1) {
      inArray(i + shiftAmount) = inArray(i);
    }
  }

  private def sort(arrayToBeSorted: Array[Int]): Array[Int] = {
    if (arrayToBeSorted.length < 2) arrayToBeSorted
    else {
      val midPoint = arrayToBeSorted(arrayToBeSorted.length / 2)
      sort(arrayToBeSorted filter (midPoint >)) ++ (arrayToBeSorted filter (midPoint ==)) ++ sort(arrayToBeSorted filter (midPoint <))
    }
  }

  def getIndex(x: Int): Int = {
    return getIndex(x, storage, storage.length-1);
  }

  override def toString(): String = {
    val b: StringBuilder = new StringBuilder();

    if (storage != null) {
      for (i: Int <- 0 until storage.length) {
        b.append(storage(i) + ",");
      }
    }

    if (b.size > 0) {
      b.deleteCharAt(b.size - 1);
    }

    return b.toString();
  }

}

object IyteImmutableSet {
  def apply() = new IyteImmutableSet();

  def apply(integers: Array[Int]) = new IyteImmutableSet(integers);

  def apply(integers: Int*) = new IyteImmutableSet(integers.toArray);
}
