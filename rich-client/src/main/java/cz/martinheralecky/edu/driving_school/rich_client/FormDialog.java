package cz.martinheralecky.edu.driving_school.rich_client;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing a dialog that contains a form.
 */
public class FormDialog extends Dialog<ButtonType> implements InvalidationListener {
    /**
     * Fields in the dialog form.
     */
    private Map<Object, FormField> fields;

    /**
     * Instantiatable only via {@link Builder}.
     */
    private FormDialog() {
        fields = new HashMap<>();
    }

    /**
     * Returns the current value of the field.
     *
     * @param id Identifier of the field.
     */
    public String getFieldValue(Object id) {
        return fields.get(id).control.getText();
    }

    @Override
    public void invalidated(Observable observable) {
        validate();
    }

    /**
     * Validates current values of all fields in the form and highlights them appropriately. If at least one fails
     * the validation, disables the OK button.
     */
    private void validate() {
        var allPass = true;

        for (var field : fields.values()) {
            if (field.required && field.control.getText().trim().isEmpty()) {
                highlightFieldFails(field.control);
                allPass = false;
            } else {
                highlightFieldPasses(field.control);
            }
        }

        getDialogPane().lookupButton(ButtonType.OK).setDisable(!allPass);
    }

    /**
     * Highlights the given field signalizing its value is valid.
     */
    private void highlightFieldPasses(TextField field) {
        field.setStyle("-fx-border-color: transparent");
    }

    /**
     * Highlights the given field signalizing its value is invalid.
     */
    private void highlightFieldFails(TextField field) {
        field.setStyle("-fx-border-color: red; -fx-border-radius: 3px; -fx-focus-color: red");
    }

    /**
     * Builder for the {@link FormDialog}.
     */
    public static class Builder {
        /**
         * The dialog we're building.
         */
        private final FormDialog dialog;

        /**
         * The grid pane in the dialog.
         */
        private final GridPane grid;

        public Builder() {
            grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            dialog = new FormDialog();
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
            dialog.getDialogPane().setContent(new VBox(grid));
        }

        /**
         * Same as {@link #addField(Object, String, String, boolean)}, {@code value} defaults to {@code ""}, {@code
         * required} defaults to {@code true}.
         */
        public Builder addField(Object id, String label) {
            addField(id, label, "", true);
            return this;
        }

        /**
         * Same as {@link #addField(Object, String, String, boolean)}, {@code required} defaults to {@code true}.
         */
        public Builder addField(Object id, String label, String value) {
            addField(id, label, value, true);
            return this;
        }

        /**
         * Same as {@link #addField(Object, String, String, boolean)}, {@code value} defaults to {@code ""}.
         */
        public Builder addField(Object id, String label, boolean required) {
            addField(id, label, "", required);
            return this;
        }

        /**
         * Adds a field to the dialog form.
         *
         * @param id       Client-provided object identifying the field.
         * @param label    Label of the field.
         * @param value    Value of the field.
         * @param required Whether this field is required.
         * @return this {@link Builder}
         */
        public Builder addField(Object id, String label, String value, boolean required) {
            var textField = new TextField(value);
            textField.setStyle("-fx-border-color: transparent");
            textField.textProperty().addListener(dialog);

            GridPane.setHgrow(textField, Priority.ALWAYS);

            var formField = new FormField(textField, required);

            dialog.fields.put(id, formField);

            var row = grid.getRowCount();
            grid.add(new Text(label + ":"), 0, row);
            grid.add(formField.control, 1, row);

            return this;
        }

        /**
         * Builds the {@link FormDialog}.
         *
         * @return The new {@link FormDialog}.
         */
        public FormDialog build() {
            dialog.validate();

            return dialog;
        }
    }

    /**
     * Box for field's control and meta-information.
     * <p>
     * This construct is immutable.
     */
    private static class FormField {
        /**
         * The text field control.
         */
        final TextField control;

        /**
         * Whether this field is required.
         */
        final boolean required;

        FormField(TextField control, boolean required) {
            this.control = control;
            this.required = required;
        }
    }
}
