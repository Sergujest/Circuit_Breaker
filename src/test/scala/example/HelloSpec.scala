package example

import org.scalatest._

class HelloSpec extends FlatSpec with Matchers {
  "The Hello object" should "print disarda 5 times" in {
    Hello.run
  }
}
