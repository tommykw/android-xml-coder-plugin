import com.intellij.featureStatistics.FeatureUsageTracker
import com.intellij.ide.actions.GotoActionBase
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.DumbAware

class ChangeAction : GotoActionBase(), DumbAware {
    override fun gotoActionPerformed(e: AnActionEvent?) {
        val project = e?.getData(CommonDataKeys.PROJECT)
        if (project == null) return

        FeatureUsageTracker.getInstance().triggerFeatureUsed("navigation.popup.file")

    }
}