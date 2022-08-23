package zio.mqtt

import zio.stream.ZStream
import zio.mqtt.client.*
import zio.{*, given}
import ZIO.*
import zio.stm.*

trait MqttServer

sealed trait MqttConnection {
  private val client: MqttClient = ???
  def publish(topic: Topic, payload: Chunk[Byte]): Unit
  def subscribe(topic: Topic): ZStream[Scope, MqttError, Message] = ???

  private def unsubscribe(topic: String): ZIO[Any, Nothing, Unit] = ???
  protected def disconnect(): Unit
}

sealed case class Message(topic: Topic, payload: Array[Byte])

object MqttConnection {
  def apply(host: String, port: Int, clientId: String): MqttConnection =
    ???
}



object Examples extends App{
    import Topic.{*, given}

    val topic5 = Topic("a/b/c")
    val topic7 = Topic("a/+/c")
    val topic8 = Topic("#/c")

    println(topic5)
    println(topic7)
    println(topic8)
    
}