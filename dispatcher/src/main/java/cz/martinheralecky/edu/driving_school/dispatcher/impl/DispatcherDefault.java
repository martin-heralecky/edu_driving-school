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
        socket = new Socket(host, port);
        socket.setSoTimeout(3000);

        oos = new ObjectOutputStream(socket.getOutputStream());
        oos.flush(); // flush the header

        ois = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public <T> T send(Command<T> command) throws Exception {
        if (socket == null) {
            throw new RuntimeException("Connection is not active.");
        }

        try {
            oos.writeObject(command);
            oos.flush();

            Object result = ois.readObject();

            if (result instanceof Exception) {
                throw (Exception) result;
            }

            return (T) result;
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Error while communicating with the server.", ex);
            throw ex;
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}
