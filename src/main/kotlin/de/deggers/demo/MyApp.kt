package de.deggers.demo


import de.deggers.demo.view.FilterableTreeItemView
import tornadofx.App
import tornadofx.launch

class MyApp : App(FilterableTreeItemView::class) {
    fun main(args: Array<String>) {
        launch<MyApp>(args)
    }
}

