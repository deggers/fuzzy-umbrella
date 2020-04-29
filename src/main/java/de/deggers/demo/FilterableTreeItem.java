package de.deggers.demo;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.CheckBoxTreeCell;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;
/*
Source https://github.com/eclipse-efx/efxclipse-rt/blob/3.x/modules/ui/org.eclipse.fx.ui.controls/src/main/java/org/eclipse/fx/ui/controls/tree/FilterableTreeItem.java
All credit goes to Christoph Keiml from http://www.kware.net/
 */

/**
 * An extension of {@link TreeItem} with the possibility to filter its children. To enable filtering
 * it is necessary to set the {@link Predicate}. If a predicate is set, then the tree item
 * will also use this predicate to filter its children (if they are of the type FilterableTreeItem).
 * <p>
 * A tree item that has children will not be filtered. The predicate will only be evaluated, if the
 * tree item is a leaf. Since the predicate is also set for the child tree items, the tree item in question
 * can turn into a leaf if all its children are filtered.
 * <p>
 * This class extends {@link CheckBoxTreeItem} so it can, but does not need to be, used in conjunction
 * with {@link CheckBoxTreeCell} cells.
 *
 * @param <T> The type of the {@link #getValue() value} property within {@link TreeItem}.
 */
public final class FilterableTreeItem<T> extends CheckBoxTreeItem<T> {
    private final ObservableList<TreeItem<T>> sourceList = FXCollections.observableArrayList();
    private final FilteredList<TreeItem<T>> filteredList = new FilteredList<>(this.sourceList);

    private final ObjectProperty<Predicate<T>> predicate = new SimpleObjectProperty<>();

    /**
     * Creates a new {@link TreeItem} with sorted children. To enable sorting it is
     * necessary to set the {@link TreeItemComparator}. If no comparator is set, then
     * the tree item will attempt so bind itself to the comparator of its parent.
     *
     * @param value the value of the {@link TreeItem}
     */
    public FilterableTreeItem(T value) {
        super(value);
        this.filteredList.predicateProperty().bind(Bindings.createObjectBinding(() -> child -> {
            // Set the predicate of child items to force filtering
            if (child instanceof FilterableTreeItem) {
                FilterableTreeItem<T> filterableChild = (FilterableTreeItem<T>) child;
                filterableChild.setPredicate(this.predicate.get());
            }
            // If there is no predicate, keep this tree item
            if (this.predicate.get() == null)
                return true;
            // If there are children, keep this tree item
            if (child.getChildren().size() > 0)
                return true;
            // Otherwise ask the TreeItemPredicate
            return this.predicate.get().test(child.getValue());
        }, this.predicate));

        Bindings.bindContent(getChildren(), filteredList);
    }

    /**
     * @return the predicate property
     */
    public ObjectProperty<Predicate<T>> predicateProperty() {
        return this.predicate;
    }

    /**
     * Set the predicate
     *
     * @param predicate the predicate
     */
    public void setPredicate(Predicate<T> predicate) {
        this.predicate.set(predicate);
    }

    /**
     * Adds a node to the internal list which backs the filteredList
     * @param node
     */
    public void add(@NotNull FilterableTreeItem<T> node) {
        this.sourceList.add(node); 
    }

    /**
     * Adds multiple nodes to the internal list which backs the filteredList
     * @param nodes
     */
    @SafeVarargs
    public final void addAll(@NotNull FilterableTreeItem<T>... nodes) {
        this.sourceList.addAll(nodes);
    }
}
