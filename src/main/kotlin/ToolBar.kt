import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.ButtonGroup
import javax.swing.JLabel
import javax.swing.JToggleButton
import javax.swing.JToolBar

/**
 * Created by tommy on 2017/01/05.
 */
class ToolBar : JToolBar, ActionListener {
    private val mainPanel: ToolWindowPanel

    constructor(mainPanel: ToolWindowPanel) {
        this.mainPanel = mainPanel
        val modeLabel = JLabel("Mode")
        add(modeLabel)

        val group = ButtonGroup()
        val longMode = JToggleButton("Long")
        longMode.isSelected = true
        longMode.addActionListener {  }
        longMode.actionCommand = "Long"
        group.add(longMode)
        add(longMode)

        val doubleMode = JToggleButton("Double")
        doubleMode.addActionListener {  }
        doubleMode.actionCommand = "Double"
        group.add(doubleMode)
        add(doubleMode)

        val intMode = JToggleButton("Int")
        intMode.addActionListener {  }
        intMode.actionCommand = "Int"
        add(intMode)

        val stringMode = JToggleButton("String")
        stringMode.addActionListener {  }
        stringMode.actionCommand = "Strint"
        add(stringMode)

        val boolMode = JToggleButton("BoolArray")
        boolMode.addActionListener {  }
        boolMode.actionCommand = "Bool"
        boolMode.add(boolMode)
        add(boolMode)
        isFloatable = false
    }

    override fun actionPerformed(e: ActionEvent?) {
        e?.let {
            when (it.actionCommand) {
                "long" -> mainPanel.setAlgo(ToolWindowPanel.LONG)
                "int" -> mainPanel.setAlgo(ToolWindowPanel.INT)
                "string" -> mainPanel.setAlgo(ToolWindowPanel.STRING)
                "bool" -> mainPanel.setAlgo(ToolWindowPanel.BOOLARR)
                "double" -> mainPanel.setAlgo(ToolWindowPanel.DOUBLE)
            }
        }
    }
}