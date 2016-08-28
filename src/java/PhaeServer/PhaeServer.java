package phaeserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matthew Jackson <mwj8410@gmail.com>
 */
public class PhaeServer {

  /**
   * Creates and returns an instance of an HTTP server.
   * @param port specifies which port number the server should open.
   * @return HttpServer Initialized, but not started server.
   */
  public static HttpServer createServer (int port) {
    HttpServer server = null;
    try {
      server = HttpServer.create(new InetSocketAddress(port), 0);
    } catch (IOException ex) {
      Logger.getLogger(PhaeServer.class.getName()).log(Level.SEVERE, null, ex);
    }
    return server;
  }

  /**
   * Associates a URI with a handling method. This assumes that the handling
   * method makes it's own distinction on the HTTP method used to access the
   * handler.
   *
   * @param server a configured HttpServer to mount the rout handler onto.
   * @param uri a string representing the URI rout to the provided handler.
   * @param handler
   * @return HttpServer Initialized with rout methods, but not started server.
   */
  public static HttpServer mountRout (HttpServer server, String uri, HttpHandler handler) {
    server.createContext(uri, handler);
    return server;
  }

  /**
   * Starts a configured HTTP server.
   * @param server HttpServer instance to be started.
   * @return HttpServer Initialized and started server.
   */
  public static HttpServer startServer (HttpServer server) {
    server.setExecutor(null); // creates a default executor
    server.start();
    return server;
  }

  /**
   *
   * @param request String content of the request body.
   * @return String content of the request body
   */
  public static String readRequestBody (HttpExchange request) {
    InputStream is = request.getRequestBody();
    int size = 0;
    int index = 0;
    String output = "";

    try {
      size = is.available();
    } catch (IOException ex) {
      Logger.getLogger(PhaeServer.class.getName()).log(Level.SEVERE, null, ex);
    }

    try {
      System.out.println("Starting Read Operation");
      while (index < size) {
        output += (char)is.read();
        index++;
      }
    } catch (IOException ex) {
      Logger.getLogger(PhaeServer.class.getName()).log(Level.SEVERE, null, ex);
    }
    return output;
  }
}
