package pl.zdzimi.server.app;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import pl.zdzimi.server.configuration.Configuration;

import java.io.IOException;
import java.util.concurrent.Executors;
import pl.zdzimi.server.core.AstHttpHandler;

public class Controller {

    public void start() {

        HttpServer server = null;

        try {
            server = HttpServer.create(new InetSocketAddress(
                Configuration.CONFIGURATION.getRoot(),
                Configuration.CONFIGURATION.getPort()
            ), 0);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        server.createContext("/", new AstHttpHandler());
        server.setExecutor(Executors.newFixedThreadPool(5));
        server.start();

    }

}
