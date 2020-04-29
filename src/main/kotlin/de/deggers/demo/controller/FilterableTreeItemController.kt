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

    private val firstFolderSecondLevel = FilterableTreeItem(Actor("Second", "Level"))

    private val firstFolderActors = observableListOf(
        Actor("Achim", "Müller"),
        Actor("Hans", "Stein"),
        Actor("Michael", "Kamar")
    )

    private val firstFolderSecondLevelActors = observableListOf(
        Actor("Deep", "AI"),
        Actor("Max", "Müller")
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
        root.addAll(firstFolder, secondFolder, thirdFolder)
        firstFolder.add(firstFolderSecondLevel)

        firstFolderActors.forEach(Consumer { firstFolder.add(FilterableTreeItem(it)) })
        firstFolderSecondLevelActors.forEach(Consumer { firstFolderSecondLevel.add(FilterableTreeItem(it)) })
        secondFolderActors.forEach(Consumer { secondFolder.add(FilterableTreeItem(it)) })
        thirdFolderActors.forEach(Consumer { thirdFolder.add(FilterableTreeItem(it)) })

    }
}