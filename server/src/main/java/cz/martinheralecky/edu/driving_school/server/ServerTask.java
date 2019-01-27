package cz.martinheralecky.edu.driving_school.server;

import cz.martinheralecky.edu.driving_school.utils.Config;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Listener for client connections that executes {@link ClientTask} for every client.
 */
public class ServerTask implements Runnable {
    private static final Logger LOG = Logger.getLogger(ClientTask.class.getName());

    private final ExecutorService threadPool;

    public ServerTask() {
        threadPool = Executors.newCachedThreadPool();
    }

    @Override
    public void run() {
        LOG.info("ServerTask starting execution.");

        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(Config.SERVER_PORT);
            serverSocket.setSoTimeout(1000);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Could not create the server socket.", ex);
            return;
        }

        while (!Thread.interrupted()) {
            try {
                var clientSocket = serverSocket.accept();
                threadPool.submit(new ClientTask(clientSocket));
            } catch (SocketTimeoutException ex) {
                // ignore, continue
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, "Error while establishing connection with the client.", ex);
            }
        }

        threadPool.shutdownNow();

        LOG.info("ServerTask terminating.");
    }
}
