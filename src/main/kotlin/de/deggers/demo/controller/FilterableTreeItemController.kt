package de.deggers.demo.controller

import de.deggers.demo.FilterableTreeItem
import de.deggers.demo.model.Actor
import tornadofx.Controller
import tornadofx.observableListOf
import java.util.function.Consumer

class FilterableTreeItemController : Controller() {
    val root = FilterableTreeItem(Actor("root", "root"))
    val firstFolder = FilterableTreeItem(Actor("First", "Folder"))
    private val secondFolder = FilterableTreeItem(Actor("Second", "Folder"))
    private val thirdFolder = FilterableTreeItem(Actor("Third", "Folder"))

    private val firstFolderActors = observableListOf(
        Actor("Achim", "Müller"),
        Actor("Hans", "Stein"),
        Actor("Michael", "Kamar")
    )

    private val secondFolderActors = observableListOf(
        Actor("Laura", "Müller"),
        Actor("Achmed", "Bogdan")
    )

    private val thirdFolderActors = observableListOf(
        Actor("Achim", "Müller"),
        Actor("Laura", "Müller")
    )

    init {
        root.internalChildren.addAll(firstFolder, secondFolder, thirdFolder)
        firstFolderActors.forEach(Consumer { firstFolder.internalChildren += FilterableTreeItem(it) })
        secondFolderActors.forEach(Consumer { secondFolder.internalChildren += FilterableTreeItem(it) })
        thirdFolderActors.forEach(Consumer { thirdFolder.internalChildren += FilterableTreeItem(it) })
    }
}