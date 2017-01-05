import com.intellij.openapi.ui.SimpleToolWindowPanel
import java.awt.BorderLayout
import java.awt.GridLayout
import javax.swing.BoxLayout
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

/**
 * Created by tommy on 2017/01/05.
 */
class ToolWindowPanel : SimpleToolWindowPanel(true) {
    companion object {
        protected val LONG = 0
        protected val INT = 1
        protected val STRING = 2
        protected val BOOLARR = 3
        protected val DOUBLE = 4

        private var suspendEvent = false
        private var alog = LONG
    }

    init {
        val container = JPanel(BorderLayout())
        val mainPanel = JPanel()
        mainPanel.layout = BoxLayout(mainPanel, BoxLayout.PAGE_AXIS)
        val line1 = JPanel().apply {
            layout = GridLayout(1, 2)
            add(JLabel("xxxlabel"))
            add(JTextField(10))
        }

        val line2 = JPanel().apply {
            layout = GridLayout(1, 2)
            add(JLabel("xxxlabel2"))
            add(JTextField(10))
        }

    }
}