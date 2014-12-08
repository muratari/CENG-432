import scala.collection.mutable

/**
 * Created by Murat on 05.12.2014.
 */
trait RandomStuffTrait {
  def transform(list: List[Int], op: (Int) => Int): List[Int]

  def allValid(list: List[Int], op: (Int) => Boolean): Boolean

  def executeWithRetry(retryCount: Int, op: => Int): Option[Int]
}

object RandomStuff extends RandomStuffTrait {

  def transform(list: List[Int], op: (Int) => Int): List[Int] = {
    for (element <- list) yield op(element)
  }

  def allValid(list: List[Int], op: (Int) => Boolean): Boolean = {
    if (list == null || list.length == 0) {
      true
    } else {
      isValidIteration(list, 0, op)
    }
  }

  def executeWithRetry(retryCount: Int, op: => Int): Option[Int] = {
    if (retryCount == 0) {
      None
    } else {
      try {
        Some[Int](op)
      } catch {
        case e: Exception => executeWithRetry(retryCount - 1, op)
      }
    }
  }

  /* Tail-recursion instead of "break" keyword, I guess this is
   * the "functional programming" that they were talking about */
  private def isValidIteration(list: List[Int], currentIndex: Int, op: Int => Boolean): Boolean = {
    if (currentIndex >= list.length) {
      true
    } else if (!op(list(currentIndex))) {
      false
    } else {
      isValidIteration(list, currentIndex + 1, op)
    }
  }

}
