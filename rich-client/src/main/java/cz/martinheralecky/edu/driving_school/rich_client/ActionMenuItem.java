package cz.martinheralecky.edu.driving_school.rich_client;

import cz.martinheralecky.edu.driving_school.rich_client.controller.Action;
import javafx.scene.control.MenuItem;

/**
 * {@link MenuItem} that executes the action given in constructor.
 */
class ActionMenuItem extends MenuItem {
    /**
     * @param label  Label of the menu-item.
     * @param action Action to execute.
     */
    ActionMenuItem(String label, Action action) {
        super(label);

        setOnAction(actionEvent -> action.execute());
    }
}
