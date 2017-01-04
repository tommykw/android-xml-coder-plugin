import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory

import javax.swing.*

class HelloWorldWindow : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val parent = toolWindow.component
        val panel = JPanel()
        val label = JLabel("Test")
        panel.add(label)
        parent.add(panel)
    }
}
