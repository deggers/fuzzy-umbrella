package de.deggers.demo.fragment

import de.deggers.demo.model.Actor
import de.deggers.demo.model.ActorViewModel
import tornadofx.TreeCellFragment
import tornadofx.label
import tornadofx.vbox

class FilterableTreeFragment : TreeCellFragment<Actor>() {
    private val model = ActorViewModel(itemProperty)

    override val root = vbox {
        label(model.displayName)
    }
}