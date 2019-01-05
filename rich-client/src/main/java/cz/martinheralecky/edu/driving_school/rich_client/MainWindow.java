package cz.martinheralecky.edu.driving_school.rich_client;

import cz.martinheralecky.edu.driving_school.rich_client.controller.QuitAction;
import cz.martinheralecky.edu.driving_school.utils.Messages;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The main window of the application.
 */
class MainWindow extends Stage {
    MainWindow() {
        setTitle(Messages.app_name.getCapitalized());

        var root = new VBox(createMenuBar());

        var scene = new Scene(root, 800, 600);
        setScene(scene);

        show();
    }

    /**
     * Creates menu bar.
     */
    private Node createMenuBar() {
        var fileMenu = new Menu(Messages.menu_file.getCapitalized());
        fileMenu.getItems().add(new ActionMenuItem(Messages.menu_file_quit.getCapitalized(), new QuitAction()));

        return new MenuBar(fileMenu);
    }
}
