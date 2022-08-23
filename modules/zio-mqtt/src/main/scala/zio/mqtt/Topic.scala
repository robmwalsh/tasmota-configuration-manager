package zio.mqtt

sealed trait Topic {
    import Topic.*
    def /:(subTopic: Topic): Topic = Cons(this, subTopic)
}

object Topic {
  given Conversion[String, Value] = Value(_)

  //TODO validation
  def apply(topic: String): Topic =
    topic.split("/").map(x => x match {
      case "#" => `#` // must be last
      case "+" => `+`
      case _ => Value(x)
    }
    ).foldLeft[Topic](Empty)(Cons(_, _))


  sealed trait TopicComponent(val value: String) extends Topic {
    override def toString: String = value
  }
  sealed case class Value(override val value: String) extends TopicComponent(value)
  case object `#` extends TopicComponent("#")
  case object `+` extends TopicComponent("+")
  case object Empty extends Topic
  sealed case class Cons(left: Topic, right: Topic) extends Topic {
    override def toString: String = s"($left/$right)"
  }

}