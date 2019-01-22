package cz.martinheralecky.edu.driving_school.dispatcher.impl;

import cz.martinheralecky.edu.driving_school.dispatcher.Dispatcher;
import cz.martinheralecky.edu.driving_school.protocol.Command;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Default implementation of the {@link Dispatcher} service.
 */
@Component
public class DispatcherDefault implements Dispatcher {
    private static final Logger LOG = Logger.getLogger(DispatcherDefault.class.getName());

    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    @Override
    public void connect(String host, int port) throws IOException {
        try {
            socket = new Socket(host, port);
            socket.setSoTimeout(3000);
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Could not connect to the server.", ex);
            throw ex;
        }
    }

    @Override
    public <T, R> R send(Command<T> command) throws IOException {
        if (socket == null) {
            throw new IOException("Connection is not active.");
        }

        try {
            oos.writeObject(command);
            oos.flush();

            Object result = ois.readObject();

            return (R) result;
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Could not communicate with the server.", ex);
            throw ex;
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}
