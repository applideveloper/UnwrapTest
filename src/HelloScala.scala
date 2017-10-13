import scala.util.{Success, Try}

object HelloScala {
  def main(args: Array[String]): Unit = {
    val helloJava = new HelloJava()
    // In Scala, null is to wrap with option type.
    val string1 = Option(helloJava.get1()) // Option[String]
    val string2 = Option(helloJava.get2()) // Option[String]
    val string3 = Option(helloJava.getNull()) // Option[String]
    // val string4  = helloJava.getNull() // String

    // Expression of type None.type doesn't confirm to expected type String
    // In Scala,null of Java wraps with Option, converts null to None,
    // and checks None before run,but Kotlin need run
    // In Scala, if you assign None to String type, the IDE checks it before executing it.

    println(string1) // Some(1)
    println(string2) // Some(2)
    println(string3) // None
    // println(string4) // null
    // println(string4.toInt)
    // Exception in thread "main" java.lang.NumberFormatException: null
    
    try {
      (string1, string2, string3) match {
        case (Some(string1), Some(string2), Some(string3)) =>
          println(string1) // String
          println(string2) // String
          println(string3) // String
          println(s"$string1 + $string2 + $string3 = ${string1.toInt + string2.toInt + string3.toInt}") // Int
        case _ => throw new Exception("string1 is None or string2 is None or string3 is None")
      }
    } catch {
      case numberFormatException: NumberFormatException =>
        println(numberFormatException.getMessage)
      case exception: Exception => println(exception.getMessage)
    }

    def toIntTry(option: Option[String]): Option[Try[Int]] = option.map{string => Try(string.toInt)}

    try {
      for {
        try1 <- toIntTry(string1) // Try[Int] <- Option[Try[Int]]
        try2 <- toIntTry(string2) // Try[Int] <- Option[Try[Int]]
        try3 <- toIntTry(string3) // Try[Int] <- Option[Try[Int]]
      } yield {
        (try1, try2, try3) match {
          case (Success(string1), Success(string2), Success(string3)) =>
            println(s"string1 + string2 + string3 = ${string1 + string2 + string3}")
          case _ =>
            throw new Exception("string1 is None or string2 is None or string3 is None")
        }
      }
    } catch {
      case exception: Exception =>
        println(exception.getMessage)
    }

    try {
      if (string1 != None && string2 != None && string3 != None) {
        // get is Not Recommend
        println(s"string1 + string2 + string3 = ${string1.get.toInt + string2.get.toInt + string3.get.toInt}")
      } else {
        throw new Exception("string1 is None or string2 is None or string3 is None")
      }
    } catch {
      case numberFormatException: NumberFormatException =>
        println(numberFormatException.getMessage)
      case exception: Exception =>
        println(exception.getMessage)
    }
 }
}