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
class IyteMutableList {

  private var head : IyteListNode = null;

  def add(integers : Int*): Unit ={
    integers.foreach(this.add);
  }
  def add(x:Int): Unit ={
    val newNode : IyteListNode = new IyteListNode(x);

    if(head != null){
      var nextNode : IyteListNode = head;

      while(nextNode.getNextNode() != null){
        nextNode = nextNode.getNextNode();
      }

      nextNode.setNextNode(newNode);
    }else{
      head = newNode;
    }
    
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

object IyteMutableList {

  def apply() = new IyteMutableList();
  def apply(integers : Int*) = {
    val list = new IyteMutableList();
    integers.foreach(list.add);
    list;
  }

}
