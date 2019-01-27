package cz.martinheralecky.edu.driving_school.business_proxy;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.logging.Logger;

/**
 * Business-proxy OSGi bundle activator.
 */
public class Activator implements BundleActivator {
    private static final Logger LOG = Logger.getLogger(Activator.class.getName());

    @Override
    public void start(BundleContext context) throws Exception {
        LOG.info("Starting business-proxy bundle.");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        LOG.info("Stopping business-proxy bundle.");
    }
}
