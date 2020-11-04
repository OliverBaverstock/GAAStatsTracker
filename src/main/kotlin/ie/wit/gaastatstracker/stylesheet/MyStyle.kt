package ie.wit.gaastatstracker.stylesheet

import javafx.scene.paint.Color
import tornadofx.*

class MyStyle: Stylesheet() {

    companion object {
        val critical by cssclass()
    }

    init {
        critical {
            borderColor += box(Color.RED)
            padding = box(5.px)
        }
    }
}