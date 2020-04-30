package de.deggers.demo.model

import javafx.beans.property.ObjectProperty
import tornadofx.ItemViewModel
import tornadofx.getProperty
import tornadofx.property

class Actor(firstName: String, lastName: String) {
    var firstName by property(firstName)
    fun firstNameProperty() = getProperty(Actor::firstName)

    var lastName by property(lastName)
    fun lastNameProperty() = getProperty(Actor::lastName)

    var displayName by property("$firstName $lastName")
    fun displayNameProperty() = getProperty(Actor::displayName)
}

class ActorViewModel(property: ObjectProperty<Actor>) : ItemViewModel<Actor>(itemProperty = property) {
    val firstName = bind(Actor::firstNameProperty)
    val lastName = bind(Actor::lastNameProperty)
    val displayName = bind(Actor::displayNameProperty)
}

