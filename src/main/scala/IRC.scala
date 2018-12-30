import java.io.{BufferedReader, InputStreamReader, PrintWriter}
import java.net.Socket
import java.util.Scanner

class IRC(host: String, var port: Int) {
  var socket: Socket = _
  var out: PrintWriter = _
  var in: BufferedReader = _

  def startIrc = {
    connect
    register("ChatBot")
  }

  def connect = {
    socket = new Socket(host, port)
    out = new PrintWriter(socket.getOutputStream, true)
    in = new BufferedReader(new InputStreamReader(socket.getInputStream))
  }

  def register(nickname: String) = {
    out.printf("USER %s localhost %s %s\n", nickname, host, nickname)
    out.printf("NICK %s\n", nickname)
  }

  def joinChannel(channel: String) = {
    out.printf("JOIN #%s\n", channel)
  }
}

class IRCListener(socket: Socket) extends Thread {
  val out: PrintWriter = new PrintWriter(socket.getOutputStream, true)
  val in: BufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream))

  @Override
  override def run() = {
    var receivedMessage: String = null
    while (true) {
      receivedMessage = in.readLine()
      if (receivedMessage != null) {
        println(receivedMessage)
        if (receivedMessage.matches("^PING.*")) {
          System.out.println(receivedMessage.replace("PING", "PONG"))
          out.println(receivedMessage.replace("PING", "PONG"))
          out.flush()
        }
      }
    }
  }
}

object IRC {
  def main(args: Array[String]): Unit = {
    var irc: IRC = new IRC(host = "127.0.0.1", port = 6667)
    irc.startIrc

    var ircListener: IRCListener = new IRCListener(irc.socket)
    ircListener.start()

    Thread.sleep(1000)
    irc.joinChannel("JeuDeRole")

    var scanner: Scanner = new Scanner(System.in)
    var query: String = null
    while (true) {
      query = scanner.nextLine()
      irc.out.println(query)
      irc.out.flush()
    }
  }
}
