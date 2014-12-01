/**
 * Created by Murat on 29.11.2014.
 */
class IyteHashTable {

  private val DEFAULT_LOAD_FACTOR : Float = 0.7f;
  private val DEFAULT_CAPACITY : Int = 160000;
  private val DEFAULT_HORIZONTAL_LIMIT = 8;
  private val DEFAULT_GROW_FACTOR = 2;

  private val LOAD_FACTOR = DEFAULT_LOAD_FACTOR;
  private val GROW_FACTOR = DEFAULT_GROW_FACTOR;

  private var capacity = DEFAULT_CAPACITY;
  private var threshold : Int = (LOAD_FACTOR * capacity).asInstanceOf[Int];
  private var size : Int = 0;

  private var table : Array[IyteStringEntry] = new Array[IyteStringEntry](capacity);

  def set(key: String, value: String): Unit = {

    val h = hash(key);

    val keyExists: Boolean = put(h,key,value);

    if(!keyExists){
      size += 1;
    }

    if(size > threshold){
      this.resize();
    }

  }

  def get(key: String): String = {
    val h: Int = hash(key);
    val index: Int = indexFor(h);

    var p: IyteStringEntry = table(index);

    var value: String = null;

    if(p != null){

      var keyFound: Boolean = false;

      do{
        if(p.key == key){
          keyFound = true;
        }else{
          p = p.next;
        }
      }while(p != null && !keyFound);

      if(keyFound){
        value = p.value;
      }

    }

    value
  }

  def getSize(): Int ={
    this.size
  }

  private def resize(): Unit ={
    val oldTable = table;
    val oldCapacity = capacity;

    capacity *= GROW_FACTOR;
    table = new Array[IyteStringEntry](capacity);
    threshold = (capacity * LOAD_FACTOR).asInstanceOf[Int];

    for(i:Int <- 0 until oldCapacity){
      var entry = oldTable(i);

      if(entry != null){
        do{
          val next = putEntryToEmptyTable(entry);
          entry = next;
        }while(entry != null);
      }
    }
  }


  private def put(hash: Int, key: String, value: String): Boolean = {
    val index = indexFor(hash);

    var p = table(index);

    var keyExists : Boolean = false;

    if(p == null){
      table(index) = new IyteStringEntry(hash,key,value,null);
    }else{

      while(!keyExists && p.next != null){

        if(p.next.hash != hash || p.next.key != key){
          p = p.next
        }else{
          keyExists = true;
        }
      }

      p.next = new IyteStringEntry(hash,key,value, p.next);
    }

    keyExists
  }

  private def putEntryToEmptyTable(entry: IyteStringEntry): IyteStringEntry = {
    val index = indexFor(entry.hash);
    var p = table(index);
    var oldNextEntry: IyteStringEntry = null;

    if(p == null){
      table(index) = entry;
    }else{

      while(p.next != null){
        p = p.next
      }
      p.next = entry;
    }

    oldNextEntry = entry.next;
    entry.next = null;

    oldNextEntry
  }

  private def indexFor(hash: Int): Int ={
    val index = hash % capacity;
    if(index < 0){
      index + capacity
    }else{
      index
    }
  }

  private final def hash(str: String): Int = {

    var h = 0xf7ca7fd2
    var i = 0
    while (i + 1 < str.length) {
      val data = (str.charAt(i) << 16) + str.charAt(i + 1)
      h = mixLast(h, data)
      h = java.lang.Integer.rotateLeft(h, 13)
      h = h * 5 + 0xe6546b64
      i += 2
    }

    if (i < str.length) h = mixLast(h, str.charAt(i).toInt)

    h ^= str.length
    h ^= h >>> 16
    h *= 0x85ebca6b
    h ^= h >>> 13
    h *= 0xc2b2ae35
    h ^= h >>> 16

    h
  }
  private final def mixLast(hash: Int, data: Int): Int = {
    var k = data

    k *= 0xcc9e2d51
    k = java.lang.Integer.rotateLeft(k, 15)
    k *= 0x1b873593

    hash ^ k
  }
}
