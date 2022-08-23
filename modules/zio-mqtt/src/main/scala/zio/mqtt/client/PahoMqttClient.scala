package zio.mqtt.client

import zio.*
import zio.mqtt.*
import zio.mqtt.client.*
import zio.stream.*
import org.eclipse.paho.mqttv5.client.{MqttClient => PMqttClient, *}

case class PahoMqttClient private (private val pMqttClient: PMqttClient) extends MqttClient {
  def publish(topic: Topic, payload: Chunk[Byte]): ZIO[Any, MqttError, Unit] = ???
  def subscribe(topic: Topic): ZStream[Any, MqttError, Message] = ???
  def unsubscribe(topic: Topic): ZIO[Any, Nothing, Unit] = ???
}

object PahoMqttClient {
  def apply(
      serverConfig: MqttServerConfig,
      clientId: String,
      persistence: MqttClientPersistence
  ): ZIO[Any, Throwable, PahoMqttClient] =
    ZIO.attemptBlocking(
      PahoMqttClient(
        new PMqttClient(
          s"${serverConfig.host}:${serverConfig.port}",
          clientId,
          persistence
        )
      )
    )
}



final case class MqttServerConfig(host: String, port: Int)

