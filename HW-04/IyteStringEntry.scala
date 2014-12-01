/**
 * Created by Murat on 30.11.2014.
 */
protected class IyteStringEntry(val hash: Int, val key: String,
                      var value: String, var next: IyteStringEntry) {

  def setNext(newNext: IyteStringEntry): IyteStringEntry = {
    val oldNext = this.next;
    this.next = newNext;
    oldNext;
  }
  def setValue(newValue: String) : String = {
    val oldValue = this.value;
    this.value = newValue;
    oldValue;
  }


}
