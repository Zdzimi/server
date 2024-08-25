package pl.zdzimi.server.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread implements Runnable {

    private final RequestParser requestParser = new RequestParser();
    private final ServerSocket serverSocket;

    public ServerThread(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                Request request = requestParser.getRequest(inputStream);
                System.out.println(request);

                byte[] answer = Answer.answer(request).getAnswer();
                outputStream.write(answer);

                closeAll(socket, inputStream, outputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeAll(Socket socket, InputStream inputStream, OutputStream outputStream) throws IOException {
        socket.close();
        inputStream.close();
        outputStream.close();
    }

}
