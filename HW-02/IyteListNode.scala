/**
 * Created by Murat on 11.11.2014.
 */
private class IyteListNode {
  private var element : Int = Int.MinValue;
  private var next : IyteListNode = null;

  def this(x : Int){
    this();
    element = x;
  }

  def this(x : Int, nextNode : IyteListNode){
    this(x);
    next = nextNode;
  }

  def getElement(): Int ={
    this.element;
  }
  def getNextNode(): IyteListNode ={
    this.next;
  }

  def setElement(x : Int): Unit ={
    this.element = x;
  }
  def setNextNode(nextNode : IyteListNode): Unit ={
    this.next = nextNode;
  }
}
