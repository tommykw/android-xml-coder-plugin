import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.*
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.getSuperNames
import utils.PsiUtils

class ConvertToSimpleConstructor : IntentionAction {
    private val CONTENT_FULL_QUALIFIED_NAME = "android.content.Context"
    private val ATTRIBUTE_SET_FULL_QUALIFIED_NAME = "android.util.AttributeSet"
    private val CONSTRUCTOR_FORMAT = """@JvmOverloads constructor(
    context:Context,
    attrs:AttributeSet? = null,
    defStyle:Int = 0): %s(context, attrs, defStyle)
"""

    override fun getFamilyName() = text
    override fun getText() = "Convert to simple constructor"
    override fun startInWriteAction() = true

    override fun isAvailable(project: Project, editor: Editor?, file: PsiFile?) = PsiUtils.isKtFile(file)

    override fun invoke(project: Project, editor: Editor?, file: PsiFile?) {
        val ktFile = file as KtFile
        val psiReference = PsiUtils.currentPsiReference(editor, file) ?: return
        val viewName = psiReference.canonicalText
        val parentClass = PsiUtils.parentClass(editor, file) ?: return
        var isViewClass = false
        parentClass.getSuperNames().forEach {
            if (it == "View") {
                isViewClass = true
                return@forEach
            }
        }

        if (isViewClass.not()) return

        val factory = KtPsiFactory(project)
        parentClass.getColon()?.delete()
        parentClass.getPrimaryConstructor()?.delete()
        parentClass.getSecondaryConstructors().map { it.delete() }

        psiReference.element.addAfter(
            factory.createTypeCodeFragment(
                String.format(CONSTRUCTOR_FORMAT, psiReference.canonicalText),
                null
            )
            ,null
        )

        val optimizedName = psiReference.canonicalText.substring(0, psiReference.canonicalText.length - viewName.length - 1)
        println(optimizedName)
        //psiReference.element.replace(factory.createTypeCodeFragment(optimizedName, null))
        psiReference.element.delete()
        psiReference.element.replace(factory.createTypeCodeFragment(optimizedName, null))

        listOf(
            Triple(CONTENT_FULL_QUALIFIED_NAME, factory, ktFile),
            Triple(ATTRIBUTE_SET_FULL_QUALIFIED_NAME, factory, ktFile)
        ).map { PsiUtils.addImport(it.first, it.second, it.third) }
    }
}