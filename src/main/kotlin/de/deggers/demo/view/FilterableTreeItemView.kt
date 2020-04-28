package de.deggers.demo.view

import de.deggers.demo.FilterableTreeItem
import de.deggers.demo.controller.FilterableTreeItemController
import de.deggers.demo.model.Actor
import javafx.beans.binding.Bindings
import tornadofx.*
import java.util.concurrent.Callable
import java.util.function.Predicate

class FilterableTreeItemView : View() {
    private val ctrl: FilterableTreeItemController by inject()

    override val root = vbox {
        val filterField = textfield() {
            promptText = "Enter filter text ..."
        }

        form {
            fieldset {
                field("Filter") {
                    add(filterField)
                }
            }

            fieldset {
                hbox(spacing = 10) {
                    val firstName = textfield { promptText = "Enter first name .." }
                    val lastName = textfield { promptText = "Enter last name ..." }
                    val addBtn = button("Add Actor to Folder1").apply {
                        setOnAction {
                            val treeItem = FilterableTreeItem(
                                Actor(
                                    firstName = firstName.text,
                                    lastName = lastName.text
                                )
                            )
                            ctrl.firstFolder.internalChildren.add(treeItem)
                        }
                    }
                    addBtn.disableProperty().bind(Bindings.isEmpty(lastName.textProperty()))
                }
            }

            treeview<Actor> {
                root = ctrl.root
                isShowRoot = false
                root.expandAll()

                cellFormat {
                    text = it.displayName
                }

                populate { item ->
                    if (item == root) item.value
                    item.children.map { it.value }
                }

                // Important to set the Predicate after first populate - otherwise tree is initially empty
                (root as FilterableTreeItem<Actor>).predicateProperty().bind(Bindings.createObjectBinding(Callable {
                    if (filterField.text == null || filterField.text.isEmpty()) return@Callable null
                    else Predicate { actor: Actor ->
                        actor.displayName.contains(
                            filterField.text.trim(),
                            ignoreCase = true
                        )
                    }
                }, filterField.textProperty()))

            }
        }
    }
}