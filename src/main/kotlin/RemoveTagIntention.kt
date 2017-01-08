import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import com.intellij.psi.xml.XmlFile

class RemoveTagIntention : IntentionAction {
    override fun getFamilyName() = text
    override fun getText() = "Remove current tag"
    override fun startInWriteAction() = true

    override fun isAvailable(project: Project, editor: Editor?, file: PsiFile?): Boolean {
        return file is XmlFile
    }

    override fun invoke(project: Project, editor: Editor?, file: PsiFile?) {
        val offset = editor?.caretModel?.offset ?: return
        val psiElement = file?.findReferenceAt(offset) ?: return
        psiElement.element.delete()
    }
}