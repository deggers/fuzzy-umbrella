# fuzzy-umbrella
A demo application showing TornadoFX 2.0 with a filterable / searchable TreeView 

# How does it look ? 
![](FilterableTreeView.gif)

# Credit 
I got the whole FilterableTreeItem from this Blogpost http://www.kware.net/?p=204 

A very similar implementation is as well [here](https://github.com/eclipse-efx/efxclipse-rt/blob/3.x/modules/ui/org.eclipse.fx.ui.controls/src/main/java/org/eclipse/fx/ui/controls/tree/FilterableTreeItem.java)

I just changed that the predicate may be a normal java predicate instead of a specialised TreeItemPredicate - i did not see the need for it.

I followed this [example](https://git.eclipse.org/c/efxclipse/org.eclipse.efxclipse.git/tree/demos/org.eclipse.fx.ui.controls.sample/src/org/eclipse/fx/ui/controls/sample/FilterableTreeItemSample.java) but refactored it to work with [TornadoFX](https://github.com/edvin/tornadofx) which is a nice framework for JavaFX written in Kotlin
