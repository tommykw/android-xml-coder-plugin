import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import com.intellij.psi.xml.XmlFile

/**
 * WIP
 */
class ReplaceHardcodeToResourceIntention : IntentionAction {
    override fun getFamilyName() = text
    override fun getText() = "Replace resource"
    override fun startInWriteAction() = true

    override fun isAvailable(project: Project, editor: Editor?, file: PsiFile?): Boolean {
        return file is XmlFile
    }

    override fun invoke(project: Project, editor: Editor?, file: PsiFile?) {
    }
}