import zio._
import zio.Console._

object MyApp extends ZIOAppDefault {

  def run =
    for {
      _    <- printLine("Hello! What is your name?")
    } yield ()
}

@main def test = println("hello world!")