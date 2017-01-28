import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import org.jetbrains.kotlin.idea.core.deleteElementAndCleanParent
import org.jetbrains.kotlin.psi.KtClass
import utils.PsiUtils
import utils.StringUtils
import utils.getSemicolons

class RemoveSemicolonsIntention : IntentionAction {
    override fun getFamilyName() = text
    override fun startInWriteAction() = true
    override fun getText() = "Remove colons"
    override fun isAvailable(project: Project, editor: Editor?, file: PsiFile?) = PsiUtils.isKtFile(file)

    override fun invoke(project: Project, editor: Editor?, file: PsiFile?) {
        val parentClass: KtClass = PsiUtils.parentClass(editor, file) ?: return
        parentClass.getProperties().map {
            it.setName(StringUtils.takeWithoutSemicolon(it.text))
        }
        parentClass.getSemicolons()?.deleteElementAndCleanParent()
    }
}