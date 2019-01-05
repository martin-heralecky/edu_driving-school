package cz.martinheralecky.edu.driving_school.rich_client.controller;

import cz.martinheralecky.edu.driving_school.rich_client.Activator;
import javafx.application.Platform;
import org.osgi.framework.BundleException;
import org.osgi.framework.FrameworkUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class representing an action that quits the application, when executed.
 */
public class QuitAction implements Action {
    private static final Logger LOG = Logger.getLogger(QuitAction.class.getName());

    @Override
    public void execute() {
        try {
            Platform.exit();

            FrameworkUtil.getBundle(Activator.class).stop();
        } catch (BundleException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
}
