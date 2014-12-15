import scala.collection.mutable

/**
 * Created by Murat on 13.12.2014.
 */
case class User(name: String, email: String, grade: Int)

trait UserManagerTrait {
  def add(name: String, email: String, grade: Int) : Option[User]
  def getUser(email: String) : Option[User]
  def getUserListForGrade(grade: Int) : List[User]
  def getCertainGrades(gradeSelector: (Int) => Boolean): List[String]
}
object UserManager extends UserManagerTrait {

  private val users: mutable.HashMap[String,User] = new mutable.HashMap[String,User]()

  def add(name: String, email: String, grade: Int) : Option[User] = {
    if(!users.contains(email)){
      val user = User(name,email,grade)
      users.put(email,user)
      Some(user)
    }else{
      None
    }
  }
  def getUser(email: String) : Option[User] = {
    users.get(email)
  }
  def getUserListForGrade(grade: Int) : List[User] = {
    users.filter( entry => entry._2.grade == grade ).values.toList
  }
  def getCertainGrades(gradeSelector: (Int) => Boolean): List[String] = {
    val selectedUsers = users.filter( entry => gradeSelector(entry._2.grade) ).values
    selectedUsers.map((user:(User)) => user.name).toList
  }
}
