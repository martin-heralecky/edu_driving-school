package cz.martinheralecky.edu.driving_school.dispatcher;

import cz.martinheralecky.edu.driving_school.protocol.Command;

import java.io.IOException;

/**
 * Can connect to the server and send {@link Command}s.
 */
public interface Dispatcher {
    /**
     * Connects to the server.
     */
    void connect(String host, int port) throws IOException;

    /**
     * Sends a {@link Command} to the server and returns the received result. The connection has to be established
     * via {@link Dispatcher#connect(String, int)} prior to calling this method.
     */
    <T, R> R send(Command<T> command) throws IOException;
}
