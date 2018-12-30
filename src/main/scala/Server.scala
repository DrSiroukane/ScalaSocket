import java.io.{BufferedReader, InputStreamReader, PrintWriter}
import java.net.{ServerSocket, Socket}

object Server extends App {
  var serverSocket: ServerSocket = new ServerSocket(1027)
  while (true) {
    val socket: Socket = serverSocket.accept()
    val input: BufferedReader =
      new BufferedReader(new InputStreamReader(socket.getInputStream))
    val output: PrintWriter = new PrintWriter(socket.getOutputStream)
    var query: String = null
    var continue: Boolean = true
    while (continue) {
      query = input.readLine()
      if (query != null) {
        println(query + " from client")
        output.println(query + " from server")
        output.flush()
        continue = false
      }
    }
    input.close()
    output.close()
    socket.close()
  }
  serverSocket.close()
}