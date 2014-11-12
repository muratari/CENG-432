/**
 * Simple Mutable List. I implemented
 * like a LinkedList for simplicity,
 * because there was no requirements
 * other than mutability and add method.
 *
 * PS: Sorry for spaghetti code.
 *
 * Created by Murat on 11.11.2014.
 */
class IyteImmutableList {

  private var head : IyteListNode = null;

  def this(integers : Array[Int]){
    this();
    if(integers.length > 0){
      head = new IyteListNode();
      var nextNode = head;
      nextNode.setElement(integers(0));

      for( i : Int <- 1 until integers.length ){
        nextNode.setNextNode(new IyteListNode(integers(i)));
        nextNode = nextNode.getNextNode();
      }
    }
  }

  def add(integers : Int*) : IyteImmutableList ={
    add(integers.toArray);
  }
  def add(integers : Array[Int]) : IyteImmutableList = {
    val newList : IyteImmutableList = new IyteImmutableList(integers);

    var nextNode = newList.head;
    if(nextNode != null){
      while(nextNode.getNextNode() != null){
        nextNode = nextNode.getNextNode();
      }
    }

    nextNode.setNextNode(head);

    newList;
  }

  override def toString(): String ={
    val b : StringBuilder = new StringBuilder();
    var nextNode : IyteListNode = head;

    while(nextNode != null){
      b.append(nextNode.getElement().toString + ",");
      nextNode = nextNode.getNextNode();
    }

    if(b.size > 0){
      b.deleteCharAt(b.size-1);
    }

    return b.toString();
  }

}

object IyteImmutableList {
  def apply() = new IyteImmutableList();
  def apply(integers : Int*) = new IyteImmutableList(integers.toArray);

}
