package utils

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtClass

fun KtClass.getSemicolons(): PsiElement? {
    return node.findChildByType(KtTokens.SEMICOLON)?.psi
}