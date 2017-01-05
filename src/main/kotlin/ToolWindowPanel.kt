import com.intellij.openapi.ui.SimpleToolWindowPanel
import java.awt.BorderLayout
import java.awt.GridLayout
import javax.swing.BoxLayout
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

/**
 * Created by tommy on 2017/01/05.
 */
class ToolWindowPanel : SimpleToolWindowPanel(true) {
    companion object {
        val LONG: Short = 0
        val INT: Short = 1
        val STRING: Short = 2
        val BOOLARR: Short = 3
        val DOUBLE: Short = 4

        private var suspendEvent = false
        private var algo = LONG
    }

    init {
        val container = JPanel(BorderLayout())
        val mainPanel = JPanel()
        mainPanel.layout = BoxLayout(mainPanel, BoxLayout.PAGE_AXIS)
        val encodedField = JTextField(10)
        val line1 = JPanel().apply {
            layout = GridLayout(1, 2)
            add(JLabel("xxxlabel"))
            add(encodedField)
        }

        val decodedField = JTextField(10)
        val line2 = JPanel().apply {
            layout = GridLayout(1, 2)
            add(JLabel("xxxlabel2"))
            add(decodedField)
        }

        encodedField.document.addDocumentListener(object : DocumentListener {
            private fun update() {
                if (!suspendEvent) {
                    suspendEvent = true
                    try {
                        when (algo) {
                            STRING -> decodedField.text = "xxx1"
                            LONG -> decodedField.text = "xxx2"
                            INT -> decodedField.text = "xxx3"
                            DOUBLE -> decodedField.text = "xxx4"
                            else -> {}
                        }
                    } catch (e: Exception) {
                    }
                    suspendEvent = false
                }
            }

            override fun changedUpdate(e: DocumentEvent?) {
            }

            override fun insertUpdate(e: DocumentEvent?) {
                update()
            }

            override fun removeUpdate(e: DocumentEvent?) {
                update()
            }
        })

        decodedField.document.addDocumentListener(object : DocumentListener {
            private fun update() {
                if (!suspendEvent) {
                    suspendEvent = true
                    try {
                        when (algo) {
                            STRING -> encodedField.text = "xxx1"
                            LONG -> encodedField.text = "xxx2"
                            INT -> encodedField.text = "xxx3"
                            DOUBLE -> encodedField.text = "xxx4"
                            else -> {}
                        }
                    } catch (e: Exception) {
                    }
                    suspendEvent = false
                }
            }

            override fun changedUpdate(e: DocumentEvent?) {
            }

            override fun insertUpdate(e: DocumentEvent?) {
                update()
            }

            override fun removeUpdate(e: DocumentEvent?) {
                update()
            }
        })

        mainPanel.add(line1)
        mainPanel.add(line2)
        container.add(mainPanel, BorderLayout.NORTH)
        component.add(container)
    }

    fun setAlgo(newAlgo: Short) {
        algo = newAlgo
    }
}
