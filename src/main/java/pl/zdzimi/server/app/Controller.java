package pl.zdzimi.server.app;

import pl.zdzimi.server.configuration.Configuration;
import pl.zdzimi.server.core.ServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller {

    public void start() {

        try {
            ServerSocket serverSocket = new ServerSocket(Configuration.CONFIGURATION.getPort());

            ExecutorService executorService = Executors.newFixedThreadPool(5);
            for (int i = 0; i < 5; i++) {
                executorService.execute(new ServerThread(serverSocket));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
