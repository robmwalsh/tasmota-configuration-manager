package zio.mqtt

enum MqttError(condition: Int) {
 case InvalidIdentifier extends MqttError(50000)}
