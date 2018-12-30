import java.io.{BufferedReader, InputStreamReader, PrintWriter}
import java.net.Socket

object Client extends App {
  var socket: Socket = new Socket("127.0.0.1", 1027)
  var input: BufferedReader =
    new BufferedReader(new InputStreamReader(socket.getInputStream))
  var output: PrintWriter = new PrintWriter(socket.getOutputStream)
  var message: String = "test"
  println("send to server " + message)
  output.println(message)
  output.flush()
  var answer: String = input.readLine()
  println("received from server " + answer)
}
