import java.util.Scanner

object Main extends App {
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
