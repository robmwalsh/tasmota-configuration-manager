package zio.mqtt.client

import zio.mqtt.*
import zio._
import zio.stream.*

trait MqttClient {
  def publish(topic: Topic, payload: Chunk[Byte]): ZIO[Any, MqttError, Unit]
  def subscribe(topic: Topic): ZStream[Any, MqttError, Message]
  def unsubscribe(topic: Topic): ZIO[Any, Nothing, Unit]
}