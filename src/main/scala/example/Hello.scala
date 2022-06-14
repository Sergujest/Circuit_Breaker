package example

import akka.actor.{ActorSystem, Props}
import akka.actor.TypedActor.dispatcher
import akka.pattern.CircuitBreaker

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt


object Hello extends App {
  val actorSystem = ActorSystem("system")
  val myActor = actorSystem.actorOf(Props[DangerousActor], "dangerous")


  val breaker = new CircuitBreaker(
    actorSystem.dispatcher,
    scheduler = actorSystem.scheduler,
    5,
    500.milliseconds,
    30 seconds
  )

  def run =
    myActor ! "is my middle name"
  //    .get(
  //    timeout = UCD_TIMEOUT_MS,
  //    maxFailures = 5,
  //    resetTimeout = 30,
  //    name = CircuitBreakerName.UCD_PROFILE_EVENTS
  //  )
}

//trait Greeting {
//  lazy val greeting: String = "hello"
//}
