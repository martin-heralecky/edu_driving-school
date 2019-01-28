package cz.martinheralecky.edu.driving_school.rich_client.controller;

import cz.martinheralecky.edu.driving_school.dispatcher.Dispatcher;
import cz.martinheralecky.edu.driving_school.rich_client.FormDialog;
import cz.martinheralecky.edu.driving_school.utils.Messages;
import javafx.scene.control.ButtonType;
import org.osgi.framework.FrameworkUtil;

import java.io.IOException;

/**
 * Class representing an action that asks the user for data (via {@link FormDialog}) and then attempts to connect to
 * server.
 */
public class ConnectAction implements Action {
    /**
     * The dispatcher service.
     */
    private final Dispatcher dispatcher;

    /**
     * The on-success callback.
     */
    private final Runnable onSuccess;

    public ConnectAction() {
        this(null);
    }

    /**
     * @param onSuccess Callback called if the connection was established successfully.
     */
    public ConnectAction(Runnable onSuccess) {
        var bundleContext = FrameworkUtil.getBundle(ConnectAction.class).getBundleContext();
        var dispatcherServiceRef = bundleContext.getServiceReference(Dispatcher.class);
        dispatcher = bundleContext.getService(dispatcherServiceRef);

        this.onSuccess = onSuccess;
    }

    @Override
    public void execute() throws IOException {
        var dialog = new FormDialog.Builder()
            .addField(ConnectData.HOST, Messages.connectDialog_host.getCapitalized())
            .addField(ConnectData.PORT, Messages.connectDialog_port.getCapitalized())
            .build();

        var dialogRes = dialog.showAndWait();

        if (dialogRes.isPresent() && dialogRes.get().equals(ButtonType.OK)) {
            dispatcher.connect(
                dialog.getFieldValue(ConnectData.HOST),
                Integer.parseInt(dialog.getFieldValue(ConnectData.PORT))); // todo: handle invalid value

            if (onSuccess != null) {
                onSuccess.run();
            }
        }
    }

    enum ConnectData {
        HOST,
        PORT
    }
}
