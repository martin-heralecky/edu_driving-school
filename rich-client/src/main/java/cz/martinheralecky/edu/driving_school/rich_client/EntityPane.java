package cz.martinheralecky.edu.driving_school.rich_client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Pane containing records of an entity.
 *
 * @param <T> The entity type.
 */
class EntityPane<T> extends TitledPane {
    /**
     * Records in the pane.
     */
    private ObservableList<T> records;

    /**
     * Supplies the records of the entity.
     */
    private Callable<List<T>> recordsSupplier;

    /**
     * Table as the content of the pane.
     */
    private TableView<T> tableView;

    /**
     * Instantiatable only via {@link Builder}.
     */
    private EntityPane() {
    }

    /**
     * Refreshes the data in the pane using the provided records-supplier.
     */
    public void refresh() {
        List<T> newRecords = null;
        try {
            newRecords = recordsSupplier.call();
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
            newRecords = new ArrayList<>();
        }

        records.setAll(newRecords);
    }

    /**
     * Returns the currently selected entities.
     */
    public List<T> getSelected() {
        return tableView.getSelectionModel().getSelectedItems();
    }

    /**
     * Builder for the {@link EntityPane}.
     *
     * @param <T> The entity type.
     */
    static class Builder<T> {
        /**
         * The pane we're building.
         */
        private final EntityPane<T> pane;

        Builder() {
            pane = new EntityPane<>();
            pane.records = FXCollections.observableArrayList();
            pane.tableView = new TableView<>();

            pane.setContent(pane.tableView);

            pane.tableView.setItems(pane.records);
            pane.tableView.setRowFactory(new RowFactory<>());
            pane.tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        }

        /**
         * Sets the title of the pane.
         *
         * @return this {@link Builder}
         */
        Builder<T> setTitle(String title) {
            pane.setText(title);

            return this;
        }

        /**
         * Adds a column to the table in the pane.
         *
         * @param header   Column's header.
         * @param property Name of the property of the entity.
         * @param <E>      Type of the property.
         * @return this {@link Builder}
         */
        <E> Builder<T> addColumn(String header, String property) {
            TableColumn<T, E> col = new TableColumn<>(header);
            col.setCellValueFactory(new PropertyValueFactory<>(property));

            pane.tableView.getColumns().add(col);

            return this;
        }

        /**
         * Sets the records supplier. This supplier is used to retrieve records of the entity when the pane refreshes.
         *
         * @return this {@link Builder}
         */
        Builder<T> setRecordsSupplier(Callable<List<T>> supplierCallback) {
            pane.recordsSupplier = supplierCallback;

            return this;
        }

        /**
         * Sets the action that is called when user requests to delete a record.
         */
        Builder<T> setOnDeleteRequest(Runnable action) {
            pane.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.DELETE)) {
                    action.run();
                }
            });

            return this;
        }

        /**
         * Builds the {@link EntityPane}.
         * <p>
         * {@link #setRecordsSupplier(Callable)} must be called on this {@link Builder} prior to calling this method.
         *
         * @return The new {@link EntityPane}.
         */
        EntityPane<T> build() {
            validate();
            pane.refresh();

            return pane;
        }

        /**
         * Checks, whether the pane is ready to be used.
         */
        void validate() {
            if (pane.recordsSupplier == null) {
                throw new IllegalStateException();
            }
        }

        static class RowFactory<T> implements Callback<TableView<T>, TableRow<T>> {
            @Override
            public TableRow<T> call(TableView<T> tTableView) {
                return new TableRow<>();
            }
        }
    }
}
