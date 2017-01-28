package utils

import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiReference
import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtPsiFactory
import org.jetbrains.kotlin.resolve.ImportPath

class PsiUtils {
    companion object {
        fun isKtFile(file: PsiFile?): Boolean {
            val ext = file?.fileType?.defaultExtension ?: return false
            return ext == "kt"
        }

        fun currentPsiReference(editor: Editor?, file: PsiFile?): PsiReference? {
            val offset = editor?.caretModel?.offset ?: return null
            return file?.findReferenceAt(offset)
        }

        fun parentClass(editor: Editor?, file: PsiFile?): KtClass? {
            val psiReference = currentPsiReference(editor, file) ?: return null
            val ktElement = psiReference.element as KtElement
            return PsiTreeUtil.getParentOfType(ktElement, KtClass::class.java) as KtClass
        }

        fun addImport(importPath: String, factory: KtPsiFactory, file: KtFile?) {
            file ?: return
            val importDirective = factory.createImportDirective(ImportPath(importPath))
            if (file.importDirectives.none { it.importPath == importDirective.importPath } ) {
                file.importList?.add(importDirective)
            }
        }
    }
}