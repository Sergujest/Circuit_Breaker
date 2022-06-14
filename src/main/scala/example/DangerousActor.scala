package example

import akka.actor.{Actor, ActorLogging}
import akka.pattern.{CircuitBreaker, pipe}

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt


class DangerousActor extends Actor with ActorLogging {

  import context.dispatcher

  val breaker =
    new CircuitBreaker(context.system.scheduler, maxFailures = 5, callTimeout = 500.milliseconds, resetTimeout = 30.seconds)
      .onOpen(notifyMeOnOpen())

  def notifyMeOnOpen(): Unit =
    log.warning("My CircuitBreaker is now open, and will not close for one minute")

  def dangerousCall: String = "This really isn't that dangerous of a call after all"

  def receive = {
    case "is my middle name" =>
      println("k")
      breaker.withCircuitBreaker{
        Future({
          Thread.sleep(3000)
          println("icerdeyim")
          "icerde"
        })
      }.recover{
        case exception: Exception =>
          println("disardayim")
          "disarda"
      }
    case "block for me" =>
      sender() ! breaker.withSyncCircuitBreaker(dangerousCall)
  }
}