package cz.martinheralecky.edu.driving_school.server;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

/**
 * Server OSGi bundle activator.
 */
public class Activator implements BundleActivator {
    private static final Logger LOG = Logger.getLogger(Activator.class.getName());

    private Future serverTaskFuture;

    @Override
    public void start(BundleContext context) throws Exception {
        LOG.info("Starting server bundle.");

        serverTaskFuture = Executors.newCachedThreadPool().submit(new ServerTask());
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        LOG.info("Stopping server bundle.");

        serverTaskFuture.cancel(true);
    }
}
