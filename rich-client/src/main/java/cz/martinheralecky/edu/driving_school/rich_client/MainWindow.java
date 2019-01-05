package cz.martinheralecky.edu.driving_school.rich_client;

import cz.martinheralecky.edu.driving_school.utils.Messages;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The main window of the application.
 */
class MainWindow extends Stage {
    MainWindow() {
        setTitle(Messages.app_name.getCapitalized());

        VBox root = new VBox();

        Scene scene = new Scene(root, 800, 600);
        setScene(scene);

        show();
    }
}
