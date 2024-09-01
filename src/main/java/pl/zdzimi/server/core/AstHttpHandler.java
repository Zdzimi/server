package pl.zdzimi.server.core;

import static java.net.HttpURLConnection.HTTP_OK;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;

public class AstHttpHandler implements HttpHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {

    String[] split = exchange.getRequestURI().toString().split("/");
    Response response = Response.getResponse(
        split[split.length - 2],
        split[split.length - 1]
        );

    String res = response.getResponse();

    OutputStream outStream = exchange.getResponseBody();

    try {
      exchange.sendResponseHeaders(HTTP_OK, res.length());
      exchange.getResponseHeaders().put("Content-Type", response.getContentType());
      outStream.write(res.getBytes());
      outStream.flush();
      outStream.close();
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }

}
