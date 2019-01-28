package cz.martinheralecky.edu.driving_school.server;

import cz.martinheralecky.edu.driving_school.protocol.Command;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * {@link Runnable} which handles one client.
 */
public class ClientTask implements Runnable {
    private static final Logger LOG = Logger.getLogger(ClientTask.class.getName());

    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public ClientTask(Socket socket) throws IOException {
        ois = new ObjectInputStream(socket.getInputStream());
        oos = new ObjectOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        LOG.info("ClientTask starting execution.");

        try {
            while (!Thread.interrupted()) {
                // todo: while blocked, the thread cannot be gracefully stopped
                var command = (Command) ois.readObject();

                Object result;

                try {
                    result = command.execute();
                } catch (Exception ex) {
                    result = ex;
                }

                oos.writeObject(result);
                oos.flush();
            }
        } catch (IOException | ClassNotFoundException ex) {
            LOG.log(Level.SEVERE, "Error while communicating with the client.", ex);
        }

        LOG.info("ClientTask terminating.");
    }
}
