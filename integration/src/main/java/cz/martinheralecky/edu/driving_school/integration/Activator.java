package cz.martinheralecky.edu.driving_school.integration;

import cz.martinheralecky.edu.driving_school.integration.impl.VehicleDAOMap;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.logging.Logger;

/**
 * Integration OSGi bundle activator.
 */
public class Activator implements BundleActivator {
    private static final Logger LOG = Logger.getLogger(Activator.class.getName());

    @Override
    public void start(BundleContext context) throws Exception {
        LOG.info("Starting integration bundle.");

        DAOManager.setBundleContext(context);

        // register default DAO implementations
        context.registerService(VehicleDAO.class, new VehicleDAOMap(), null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        LOG.info("Stopping integration bundle.");
    }
}
